package com.spark.process

import java.io.{BufferedReader, InputStreamReader}

import com.amazonaws.services.s3.AmazonS3
import com.spark.conf.AppProperties
import com.spark.model.{S3EventTriggerModel, StructCrimesModel}
import com.spark.service.{HDFSService, KinesisFirehoseService, SQSService}
import org.apache.spark.sql.{Row, SparkSession}

import scala.io.Source
import scala.util.parsing.json.JSONObject
import scala.collection.mutable.ArrayBuffer

object CrimesProcess {

  def execute(property: AppProperties,
              sparkSession: SparkSession,
              sqsService: SQSService,
              s3Client: AmazonS3,
              hdfsService: HDFSService,
              firehoseService: KinesisFirehoseService): Unit = {

    //READ MESSAGE AND PARSE S3 INFORMATIONS
    println("reading messages from SQS and parse if exists")
    val event: S3EventTriggerModel = sqsService.read()
    if(event == null){
      return
    }

    //GET S3 FILE
    println("getting s3 file")
    val obj = s3Client.getObject(event.bucketName, event.fileName)

    //DEFINE DESTINATION HDFS PATH AND SAVE FILE INTO HDFS
    println("writing file")
    val finalPath = property.get("hdfs.folder.upload")+event.uuidFileName
    val reader = new BufferedReader(new InputStreamReader(obj.getObjectContent(), "ISO-8859-1"))
    hdfsService.writeStream(reader, hdfsService.createPath(finalPath))

    //DEFINE SQL TEMPLATE
    println("defining sql template")
    val customSchema = StructCrimesModel.getStruct()

    //LOAD FILE FROM HDFS
    println("loading file")
    //val finalPath = property.get("hdfs.folder.upload")+"82d440b452f7d37bbdef9a98792a557b-dados-bo-2019-1-furto.csv"
    val crimes = sparkSession.read.schema(customSchema).option("encoding", "UTF-8").option("delimiter", ";").option("mode","DROPMALFORMED").format("org.apache.spark.sql.execution.datasources.csv.CSVFileFormat").load(property.get("hdfs.api")+finalPath).toDF()

    //CREATE VIEW
    println("creating view")
    crimes.createOrReplaceTempView("crimes");

    //LOAD SQL FILE WITH QUERY
    println("loading query file")
    val query = Source.fromFile(property.get("spark.sql.path.crimes")).mkString
    println(query)

    //QUERY (MINING)
    println("doing query")
    val sql = sparkSession.sql(query);

    //REMOVE NULL ITENS
    println("treating null")
    val sqlfill = sql.na.fill("", Seq("DATAOCORRENCIA")).na.fill("", Seq("HORAOCORRENCIA")).na.fill("", Seq("PERIDOOCORRENCIA")).na.fill("", Seq("LOGRADOURO")).na.fill("", Seq("NUMERO")).na.fill("", Seq("BAIRRO")).na.fill("", Seq("UF")).na.fill("", Seq("DESCR_COR_VEICULO")).na.fill("", Seq("ANO_FABRICACAO"))

    //SEND TO KINESIS
    println("sending to kinesis")
    for (row <- sqlfill.rdd.collect) {
      val map = row.getValuesMap(row.schema.fieldNames)
      val output = JSONObject(map)
      firehoseService.send(output.toString())
    }

    //REMOVE SQS
    println("deleting sqs message")
    sqsService.delete(event.receiptHandle)

    println("Done!")

  }

}
