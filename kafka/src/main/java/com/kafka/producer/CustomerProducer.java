package com.kafka.producer;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.*;

import java.util.ArrayList;
import java.util.Properties;

public class CustomerProducer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "node102:9092");
        //应答级别0，1，all
        props.put("acks", "all");
        props.put("retries", 0);
        //批量大小
        props.put("batch.size", 16384);
        //提交延迟
        props.put("linger.ms", 1);
        //缓存
        props.put("buffer.memory", 33554432);

        ArrayList<String> list = new ArrayList<>();
        list.add("com.kafka.intercetor.TimeIntercetor");
        list.add("com.kafka.intercetor.CountIntercetor");

        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG,list);


        //kv序列化
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        ProducerConfig
        props.put("partitioner.class","com.kafka.producer.CustomerPartitions");
        KafkaProducer<String, String> producer = new KafkaProducer<String,String>(props);
        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<String, String>("second", String.valueOf(i)), new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if(exception ==null){
                        System.out.println(metadata.partition()+"__"+metadata.offset());
                    }else {
                        System.out.println("发送失败");
                    }
                }
            });

        producer.close();


    }
}
