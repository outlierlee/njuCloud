package com.nju.cloud.demo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.bson.Document;

import java.util.Properties;

/**
 * @description:
 * @author: lzm
 * @create: 2023-11-15 00:42
 **/
public class MongoDBToKafka {
    public static void main(String[] args) {
        // MongoDB 连接配置
        String mongoConnectionString = "mongodb://njuer:njuer@39.105.123.254:27017";
        String dbName = "njuCloud";
        String collectionName = "Bill";

        // Kafka 连接配置
        String kafkaBootstrapServers = "192.168.244.10:9092";
        String kafkaTopic = "TestoriginData";

        // MongoDB 连接
        try (var mongoClient = MongoClients.create(mongoConnectionString)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // Kafka 生产者配置
            Properties props = new Properties();
            props.put("bootstrap.servers", kafkaBootstrapServers);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            Producer<String, String> producer = new KafkaProducer<>(props);

            // 查询 MongoDB 数据并发送到 Kafka
            try (MongoCursor<Document> cursor = collection.find().iterator()) {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    String jsonString = doc.toJson();

                    // 将数据发送到 Kafka 主题
                    producer.send(new ProducerRecord<>(kafkaTopic, jsonString));
                }
            }

            // 关闭 Kafka 生产者
            producer.close();
        }
    }
}
