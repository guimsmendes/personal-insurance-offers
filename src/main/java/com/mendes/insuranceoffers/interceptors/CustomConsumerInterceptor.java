package com.mendes.insuranceoffers.interceptors;

import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.mendes.insuranceoffers.entrypoint.constant.KafkaConstant;

public class CustomConsumerInterceptor implements ConsumerInterceptor<String, GenericRecord>, ProducerInterceptor<String,GenericRecord>{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomConsumerInterceptor.class);
	
	@Override
	public ConsumerRecords<String,GenericRecord> onConsume(ConsumerRecords<String, GenericRecord> consumerRecords) {
		StreamSupport.stream(Spliterators.spliteratorUnknownSize(consumerRecords.iterator(), Spliterator.ORDERED), false)
		.map(consumerRecord -> {
			Headers headers = consumerRecord.headers();
			
			headers.forEach(header -> {
				if(header.key().equalsIgnoreCase(KafkaConstant.CORRELATION_ID)) {
					MDC.put("correlation.id", UUID.nameUUIDFromBytes(header.value()).toString());
				}
				else if(header.key().equalsIgnoreCase(KafkaConstant.TRANSACTION_ID)) {
					MDC.put("transaction.id", UUID.nameUUIDFromBytes(header.value()).toString());
				}
			});
			
			LOGGER.info("CONSUMER INTERCEPTOR = TOPIC {} OFFSET: {} PARTITION {} GENERATED KEY {} CORRELATION_ID {} TRANSACTION_ID {} PAYLOAD {}",
					consumerRecord.topic(), consumerRecord.offset(), consumerRecord.partition(), consumerRecord.key(), MDC.get("correlation.id"), MDC.get("transaction.id"), consumerRecord.value());
			
			return consumerRecord;
		}).collect(Collectors.toSet());
		
		return consumerRecords;
	}
	

	@Override
	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProducerRecord<String, GenericRecord> onSend(ProducerRecord<String, GenericRecord> producerRecord) {
		// TODO Auto-generated method stub
		LOGGER.info("PRODUCER INTERCEPTOR - TOPIC {} PRODUCER PAYLOAD {}", producerRecord.topic(), producerRecord.value());
		return producerRecord;
	}

	@Override
	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
		// TODO Auto-generated method stub
		MDC.clear();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	

}
