package com.kafka.stream;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.processor.TopologyBuilder;

import java.util.Properties;

public class myKafkaStream {
    public static void main(String[] args) {

        //创建拓扑对象
        TopologyBuilder builder = new TopologyBuilder();

        //创建配置文件
        Properties properties = new Properties();

        properties.put("application.id","kafkaStream");
        properties.put("bootstrap.servers","node102:9092");

        //构建拓扑结构
        builder.addSource("SOURCE","first")
                .addProcessor("PROCESSOR", new ProcessorSupplier() {
                    @Override
                    public Processor get() {
                        return new LogProcessor();
                    }
                }, "SOURCE")
                .addSink("SINK","second","PROCESSOR");



        KafkaStreams kafkaStreams = new KafkaStreams(builder, properties);
        kafkaStreams.start();
    }

}
