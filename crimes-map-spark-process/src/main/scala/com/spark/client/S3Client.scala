package com.spark.client

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.spark.conf.{AppProperties, AppPropertiesSingleton, BasicAWSCredentials}

//OBJECT TO CREATE A CONNECTION CLIENT INTO AWS S3 SERVICE
object S3Client {

  val property: AppProperties = AppPropertiesSingleton.getInstance()

  def get() = {
    AmazonS3ClientBuilder.standard()
      .withCredentials(new AWSStaticCredentialsProvider(BasicAWSCredentials.get()))
      .withRegion(property.get("aws.region")).build()
  }

}
