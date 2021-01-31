package com.spark.service

import java.io.{BufferedReader, PrintWriter}

import com.spark.conf.{AppProperties, AppPropertiesSingleton}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataOutputStream, FileSystem, Path}

//SERVICE TO ACCESS HDFS
class HDFSService {

  val property: AppProperties = AppPropertiesSingleton.getInstance()
  var conf = new Configuration()
  conf.set("fs.defaultFS", property.get("hdfs.api"))
  val fs = FileSystem.get(conf)

  //CREATE A PATH INTO HDFS
  def createPath(path: String): FSDataOutputStream ={
    fs.create(new Path(path))
  }

  //WRITE A STREAM INTO HDFS PATH
  def writeStream(reader: BufferedReader, output: FSDataOutputStream ) {
    val writer = new PrintWriter(output)
    var line = reader.readLine()
    do{
      line = reader.readLine()
      writer.print(line)
      writer.print("\n")
    }while(line != null)
    writer.close()
  }

}
