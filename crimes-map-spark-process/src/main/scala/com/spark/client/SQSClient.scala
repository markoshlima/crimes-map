package com.spark.client

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import com.spark.conf.{AppProperties, AppPropertiesSingleton, BasicAWSCredentials}

//OBJECT TO CREATE A CONNECTION CLIENT INTO AWS SQS SERVICE
object SQSClient {

  val property: AppProperties = AppPropertiesSingleton.getInstance()

  def get() = {
    AmazonSQSClientBuilder.standard()
      .withCredentials(new AWSStaticCredentialsProvider(BasicAWSCredentials.get()))
      .withRegion(property.get("aws.region")).build()
  }

}
