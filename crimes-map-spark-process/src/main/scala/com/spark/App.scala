package com.spark

import com.spark.process.CrimesProcess
import com.spark.client.S3Client
import com.spark.conf.{AppProperties, AppPropertiesSingleton}
import com.spark.service.{HDFSService, SQSService, KinesisFirehoseService}
import org.apache.spark.sql.SparkSession

//spark-submit --class com.spark.App ./crimes-bigdata-spark-process-0.1-SNAPSHOT.jar

object App {

  def main(args: Array[String]) {

    // APPLICATION PROPERTY CLASS
    val property: AppProperties = AppPropertiesSingleton.getInstance()

    //DEFINE SPARK SESSION
    println("configuring spark session")
    val sparkSession = SparkSession.builder().appName(property.get("spark.application.name")).master("local").getOrCreate()

    //CREATE SQS SERVICE
    println("setting sqs service")
    val sqsService = new SQSService()

    //CREATE KINESISFIREHOSE SERVICE
    println("setting kinesis firehose service")
    val firehoseService = new KinesisFirehoseService()

    //CREATE S3 CLIENT
    println("setting s3 client")
    val s3Client = S3Client.get()

    //HADOOP SERVICE
    println("setting hadoop service")
    val hdfsService = new HDFSService()

    //THREAD
    new Thread("Executor"){
      override def run() {
        while(true) {
          CrimesProcess.execute(property, sparkSession, sqsService, s3Client, hdfsService, firehoseService)
          Thread.sleep(5000)
        }
      }
    }.start()

  }

}
