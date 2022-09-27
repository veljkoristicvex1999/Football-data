//package com.example.ConsumerApp.components;
//
//import com.example.ConsumerApp.config.Config;
//import com.example.ConsumerApp.model.Area;
//import com.example.ConsumerApp.repository.AreaRepository;
//import com.example.ConsumerApp.repository.TeamsRepository;
//import io.confluent.kafka.serializers.KafkaAvroDeserializer;
//import org.apache.avro.generic.GenericRecord;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Collections;
//import java.util.Properties;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Service
//public class AreaComponent {
//
//    final AreaRepository areaRepository;
//    private Config config;
//    final TeamsRepository teamsRepository;
//
//    @Autowired
//    public AreaComponent(AreaRepository areaRepository, TeamsRepository teamsRepository, Config config) {
//        this.areaRepository = areaRepository;
//        this.teamsRepository = teamsRepository;
//        this.config=config;
//       // saveAreaToDatabase();
//    }
//
//    public void saveAreaToDatabase() {
//        final KafkaConsumer<String, GenericRecord> consumer = new KafkaConsumer<String, GenericRecord>(this.getProperties());
//        final String topic = "secondProject";
//
//                try {
//                    consumer.subscribe(Collections.singletonList(topic));
//                    while (true) {
//                        ConsumerRecords<String, GenericRecord> records = consumer.poll(Duration.ofMillis(100));
//                        for (ConsumerRecord<String, GenericRecord> record : records) {
//                            Area area = setAreaFields(record);
//                            areaRepository.save(area);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    consumer.close();
//                }
//    }
//
//    private static Area setAreaFields(ConsumerRecord<String, GenericRecord> record) {
//
//        Area area = new Area();
//        area.setAreaId((Integer) record.value().get("id"));
//        area.setName((String) record.value().get("name").toString());
//        area.setParentAreaId((Integer) record.value().get("parentAreaId"));
//        area.setParentArea((String) record.value().get("parentArea").toString());
//        area.setFlag((String) record.value().get("flag").toString());
//        area.setDeleted_flag(false);
//        area.setCountryCode((String) record.value().get("countryCode").toString());
//        return area;
//    }
//
//    public  Properties getProperties() {
//
//        Properties properties = new Properties();
//        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, config.getKeyDeserializer());
//        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, config.getValueDeserializer());
//        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getBootstrapServer());
//        properties.setProperty("schema.registry.url", config.getSchemaUrl());
//        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, config.getAutoOfsetReset());
//        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, config.getGroupId());
//        return properties;
//    }
//}