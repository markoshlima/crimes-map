package com.spark.service

import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose
import com.spark.client.KinesisFirehoseClient
import com.spark.conf.{AppProperties, AppPropertiesSingleton}
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest
import com.amazonaws.services.kinesisfirehose.model.Record
import java.nio.ByteBuffer

//SERVICE TO INTERACT WITH KINESISFIREHOSE
class KinesisFirehoseService {

  val property: AppProperties = AppPropertiesSingleton.getInstance()
  private val firehoseClient: AmazonKinesisFirehose = KinesisFirehoseClient.get()
  val putRecordRequest: PutRecordRequest = new PutRecordRequest()
  putRecordRequest.setDeliveryStreamName(property.get("aws.kinesis.firehose.delivery.name"))

  // SEND TO DATA DELIVERY
  def send(data: String): Unit = {
    val record: Record = new Record().withData(ByteBuffer.wrap(data.getBytes()))
    putRecordRequest.setRecord(record)
    firehoseClient.putRecord(putRecordRequest)
  }

}
