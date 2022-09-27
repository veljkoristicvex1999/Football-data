//package com.example.ConsumerApp.components;
//
//import com.example.ConsumerApp.config.Config;
//import com.example.ConsumerApp.model.Area;
//import com.example.ConsumerApp.model.Team;
//import com.example.ConsumerApp.repository.AreaRepository;
//import com.example.ConsumerApp.repository.TeamsRepository;
//import io.confluent.kafka.serializers.KafkaAvroDeserializer;
//import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
//import org.apache.avro.generic.GenericRecord;
////import org.apache.catalina.core.ApplicationContext;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.time.Duration;
//import java.util.Collections;
//import java.util.Properties;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@Service
//public class TeamComponent {
//
//    final Config config;
//    final AreaRepository areaRepository;
//
//    final TeamsRepository teamsRepository;
//
//    @Autowired
//    public TeamComponent(AreaRepository areaRepository, TeamsRepository teamsRepository, Config config) {
//        this.areaRepository = areaRepository;
//        this.teamsRepository = teamsRepository;
//        this.config=config;
////
//    }
//
//    public void saveTeamsToDatabase()  {
//
//        final KafkaConsumer<String, GenericRecord> consumer = new KafkaConsumer<String, GenericRecord>(this.getProperties());
//        final String topicName = "teamsTopic";
//                try {
//
//                    consumer.subscribe(Collections.singletonList(topicName));
//                    while (true) {
//                        ConsumerRecords<String, GenericRecord> records = consumer.poll(Duration.ofMillis(100));
//                        for (ConsumerRecord<String, GenericRecord> record : records) {
//                            Team team = setTeamFields(record);
//                            teamsRepository.save(team);
//
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    consumer.close();
//                }
//            }
//
//    private Team setTeamFields(ConsumerRecord<String, GenericRecord> record) {
//
//        Team team = new Team();
//        team.setId((Integer) record.value().get("id"));
//        team.setName((String) record.value().get("name").toString());
//        team.setClubColors((String) record.value().get("clubColors").toString());
//        team.setAddress((String) record.value().get("address").toString());
//        team.setDeleted_flag(false);
//        Area area = areaRepository.findByAreaId((Integer) record.value().get("areaId"));
//        team.setArea(area);
//        return team;
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
//        properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, config.getSpecificAvroReader());
//        return properties;
//    }
//
//
//}
