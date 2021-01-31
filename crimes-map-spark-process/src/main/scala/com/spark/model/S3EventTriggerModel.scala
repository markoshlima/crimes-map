package com.spark.model

//MODEL FROM S3 TRIGGER TO SQS EVENT
class S3EventTriggerModel {

  var fileName: String = null
  var uuidFileName: String = null
  var bucketName: String = null
  var receiptHandle: String = null

}
