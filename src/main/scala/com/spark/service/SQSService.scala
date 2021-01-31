package com.spark.service

import com.amazonaws.services.sqs.AmazonSQS
import com.spark.client.SQSClient
import com.spark.model.S3EventTriggerModel
import play.api.libs.json.Json
import com.amazonaws.services.sqs.model.Message
import com.spark.conf.{AppProperties, AppPropertiesSingleton}

//SERVICE TO ACCESS SQS RESOURCE FROM AWS
class SQSService {

  val property: AppProperties = AppPropertiesSingleton.getInstance()
  private val sqsClient: AmazonSQS = SQSClient.get()

  // READ MESSAGE FROM SQS AND SET MAIN INFO ABOUT EVENT
  def read(): S3EventTriggerModel = {
    //READ MESSAGE FROM QUEUE
    val messagesSQS = sqsClient.receiveMessage(property.get("aws.sqs.queue.url")).getMessages

    if(messagesSQS.size == 0){
      println("There is no event to read")
      return null
    }

    //INSTANTIATE MODEL WITH MAIN INFORMATION ABOUT S3 EVENT
    val model: S3EventTriggerModel = new S3EventTriggerModel()

    //GET INFORMATION FROM JSON
    val message:Message = messagesSQS.get(0)
    val parsed = Json.parse(message.getBody())
    val records = parsed \ "Records"
    val record = records.get(0)
    val s3object = record \ "s3"
    val s3Bucket = s3object \ "bucket"
    val s3 = s3object \ "object"
    val s3BucketName = s3Bucket \ "name"
    val s3Key = s3 \ "key"
    val s3Sequencer = s3 \ "sequencer"
    val UUID = s3Sequencer.as[String]

    //SET INTO MODEL OBJECT
    model.bucketName = s3BucketName.as[String]
    model.fileName = s3Key.as[String]
    model.uuidFileName = UUID + "-" + model.fileName
    model.receiptHandle = message.getReceiptHandle

    //RETURN
    model
  }

  // DELETE MESSAGE FROM QUEUE
  def delete(receiptHandle: String): Unit ={
    sqsClient.deleteMessage(property.get("aws.sqs.queue.url"), receiptHandle)
  }

}
