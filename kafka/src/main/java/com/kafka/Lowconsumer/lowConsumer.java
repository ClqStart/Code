package com.kafka.Lowconsumer;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.cluster.BrokerEndPoint;
import kafka.javaapi.*;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.Message;
import kafka.message.MessageAndOffset;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class lowConsumer {
    public static void main(String[] args) {

        //定义相关属性
        ArrayList<String> broker = new ArrayList<>();
        broker.add("node102");
        broker.add("node103");
        broker.add("node104");
        //端口号
        int port = 9092;

        //主题
        String topic = "second";

        //分区
        int partition = 0;

        //偏移量offset
        long offsize = 2;
        lowConsumer lowConsumer = new lowConsumer();
        lowConsumer.getData(broker,port,topic,partition,offsize);
    }

    //找分区
    private BrokerEndPoint findLeader(List<String> brokers, int port, String topic, int partition) {

        for (String broker : brokers) {
            //获取分区lead的消费对象
            SimpleConsumer getLead = new SimpleConsumer(broker, port, 1000, 1024 * 4,
                    "getLead");
            //创建一个获取元数据请求
            TopicMetadataRequest topicMetadataRequest = new TopicMetadataRequest(Collections.singletonList(topic));

            //发送请求获取主题元数据返回值
            TopicMetadataResponse metadataResponse = getLead.send(topicMetadataRequest);
            List<TopicMetadata> metadataList = metadataResponse.topicsMetadata();

            //遍历主题元数据
            for (TopicMetadata topicMetadata : metadataList) {
                //获取多个分区元数据
                List<PartitionMetadata> partitionMetadatas = topicMetadata.partitionsMetadata();
                //遍历分区
                for (PartitionMetadata partitionMetadata : partitionMetadatas) {
                    if (partition == partitionMetadata.partitionId()) {
                        return partitionMetadata.leader();
                    }
                }
            }

        }
        return null;
    }


    //拉取数据
    private void getData(List<String> brokers, int port, String topic, int partition, Long offsize) {

        //获取分区lead
        BrokerEndPoint leader = findLeader(brokers, port, topic, partition);

        if (leader == null) {
            return;
        }
        String host = leader.host();

        //获取数据的消费对象
        SimpleConsumer getData = new SimpleConsumer(host, port, 1000, 1024 * 4, "getData");

        //抓取数据的请求
        FetchRequest fetchRequest = new FetchRequestBuilder().addFetch(topic, partition, offsize, 5000).build();

        //获取数据
        FetchResponse fetchResponse = getData.fetch(fetchRequest);

        //解析数据
        ByteBufferMessageSet messageAndOffsets = fetchResponse.messageSet(topic, partition);

        for (MessageAndOffset messageAndOffset : messageAndOffsets) {
            long offset1 = messageAndOffset.offset();
            ByteBuffer byteBuffer = messageAndOffset.message().payload();
            byte[] bytes = new byte[byteBuffer.limit()];
            byteBuffer.get(bytes);

            System.out.println(offset1+"-----"+new String(bytes));
        }


    }
}
