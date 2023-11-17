package com.nju.cloud.demo;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class FlinkKafkaConsumerExample {
    public static void main(String[] args) throws Exception {
        // 设置 Flink 执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 设置 Kafka 连接属性
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "your.kafka.bootstrap.servers");
        properties.setProperty("group.id", "your.consumer.group.id");

        // 创建 Flink Kafka Consumer
        FlinkKafkaConsumer<String> kafkaConsumer = new FlinkKafkaConsumer<>(
                "your.kafka.topic", // Kafka 主题
                new SimpleStringSchema(), // 序列化模式，这里使用简单字符串模式
                properties
        );

        // 添加 Kafka Consumer 到执行环境
        DataStream<String> kafkaStream = env.addSource(kafkaConsumer);

        // 在这里可以对 kafkaStream 进行其他的转换和操作
        // 例如，打印每个接收到的消息
        kafkaStream.print();

        // 执行任务
        env.execute("Flink Kafka Consumer Example");
    }
}
