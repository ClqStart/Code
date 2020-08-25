package com.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collections;
import java.util.Properties;

public class CustomerConsumer {

    //        KafkaConsumer
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "node102:9092");

        //消费组设置,换组默认置为最大值
        props.put("group.id","chen");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        //是否自动提交offsize
        props.put("enable.auto.commit", "true");
        //可能存在重复消费问题，提交延迟
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        //1
//        consumer.subscribe(Arrays.asList("first", "second", "third"));
        //2 不换组重复消费
        consumer.assign(Collections.singletonList(new TopicPartition("second",0)));
        consumer.seek(new TopicPartition("second",0),2);

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("topic = %s, partition =%d, offset = %d, key = %s, value = %s%n", record.topic(), record.partition(), record.offset(), record.key(), record.value());
        }


    }
}
