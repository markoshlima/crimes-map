package com.spark.client

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder
import com.spark.conf.{AppProperties, AppPropertiesSingleton, BasicAWSCredentials}

//OBJECT TO CREATE A CONNECTION CLIENT INTO AWS KINESISFIREHOSE SERVICE
object KinesisFirehoseClient {

  val property: AppProperties = AppPropertiesSingleton.getInstance()

  def get() = {
    AmazonKinesisFirehoseClientBuilder.standard()
      .withCredentials(new AWSStaticCredentialsProvider(BasicAWSCredentials.get()))
      .withRegion(property.get("aws.region")).build()
  }

}
