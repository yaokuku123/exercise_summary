package com.yqj.api;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;
import scala.collection.immutable.Stream;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaTestDemo {

    private static final String topic = "ooxx";

    // kafka producer
    @Test
    public void producer() throws ExecutionException, InterruptedException {
        // 配置kafka producer参数
        Properties p = new Properties();
        p.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "node:9092");
        p.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        p.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(p);

        while (true) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ProducerRecord<String, String> record = new ProducerRecord<>(topic, "item" + j, "val" + i);
                    Future<RecordMetadata> metaData = producer.send(record);
                    RecordMetadata result = metaData.get();
                    System.out.println(String.format("key:%s,value:%s,partition:%s,offset:%s",
                            record.key(), record.value(), result.partition(), result.offset()));
                }
            }
            Thread.sleep(2000);
        }
    }

    // kafka consumer
    @Test
    public void consumer() throws InterruptedException {
        // 配置kafka consumer参数
        Properties p = new Properties();
        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "node:9092");
        p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        p.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true"); // 自动提交采用异步的方式，容易丢数据or重复消费数据
        p.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000"); // 自动提交的时间间隔
        p.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10"); // 每批次最大拉取数据条数
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); // 首次offset未存在时，从何处消费数据
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group_1"); // consumer group

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(p);
        // 订阅topic，并添加监听器用于监听consumer的rebalance
        consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
                System.out.println("consumer分区销毁监听器 start...");
                Iterator<TopicPartition> iter = collection.iterator();
                while (iter.hasNext()) {
                    TopicPartition partition = iter.next();
                    System.out.println(partition);
                }
            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
                System.out.println("consumer分区建立监听器 start...");
                Iterator<TopicPartition> iter = collection.iterator();
                while (iter.hasNext()) {
                    TopicPartition partition = iter.next();
                    System.out.println(partition);
                }
            }
        });

        // 方式2：consumer按各个分区分别消费
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(0L));
            if (records.count() == 0) {
                Thread.sleep(1000);
                continue;
            }
            // 每次poll取多个分区的数据，且分区内的数据是有序的
            Set<TopicPartition> partitions = records.partitions();
            // 遍历每个分区
            for (TopicPartition partition : partitions) {
                // 对每个分区可以采用多线程的方式并行处理
                new Thread(()->{
                    System.out.println("------" + "partition:" + partition +
                            " count: " + records.records(partition).size() + "------");
                    // 获取当前批次中每个分区的数据集合
                    List<ConsumerRecord<String, String>> pRecords = records.records(partition);
                    Iterator<ConsumerRecord<String, String>> iter = pRecords.iterator();
                    while (iter.hasNext()) {
                        ConsumerRecord<String, String> record = iter.next();
                        System.out.println(String.format("thread:%s,key:%s,value:%s,partition:%s,offset:%s",
                                Thread.currentThread().getName(),record.key(), record.value(), record.partition(), record.offset()));
                    }
                }).start();
            }
        }


        // 方式1：consumer直接拉取全量数据消费，不区分各自分区
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(0L));
//            if (records.count() == 0) {
//                Thread.sleep(1000);
//                continue;
//            }
//            System.out.println("-----------" + records.count() + "-----------");
//            Iterator<ConsumerRecord<String, String>> iter = records.iterator();
//            while (iter.hasNext()) {
//                ConsumerRecord<String, String> record = iter.next();
//                System.out.println(String.format("key:%s,value:%s,partition:%s,offset:%s",
//                        record.key(), record.value(), record.partition(), record.offset()));
//            }
//        }
    }
}
