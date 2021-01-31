package com.spark.conf

import java.util.Properties

import scala.io.Source

// CLASS TO ACCESS PROPERTIES FROM APPLICATION.PROPERTIES FILE
class AppProperties {

  private val source = Source.fromFile("src/main/resources/application.properties")
  private val properties: Properties = new Properties()
  properties.load(source.bufferedReader())

  def get(name: String): String = {
    val property = properties.getProperty(name)
    if(property == null){
      throw new Exception("Propery "+name+" does not exists")
    }else{
      property
    }
  }

}
