package com.spark.conf

// OBJECT TO ACCESS PROPERTIES CLASS WITH ONE INSTANCE (SINGLETON)
object AppPropertiesSingleton {

  private var appProperties = new AppProperties()

  def getInstance(): AppProperties = {
    if(appProperties == null){
      appProperties = new AppProperties()
    }
    appProperties
  }

}
