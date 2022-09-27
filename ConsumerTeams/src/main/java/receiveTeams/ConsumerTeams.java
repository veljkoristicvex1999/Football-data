package receiveTeams;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

@SpringBootApplication
public class ConsumerTeams {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ConsumerTeams.class, args);
        KafkaConsumer<String, GenericRecord> consumer = new KafkaConsumer<String, GenericRecord>(getProperties());
        FileWriter fileWriter = new FileWriter("teams.txt ");
        PrintWriter printWriter =  new PrintWriter(fileWriter);
        String topic = "teamsTopic";
        Thread mainThread  = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                consumer.wakeup();
                try {
                    mainThread.join();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        try {
            consumer.subscribe(Collections.singletonList(topic));
            while (true){
                ConsumerRecords<String, GenericRecord> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, GenericRecord> record:records){
                    printWriter.print(new StringBuilder().append("{ id: ").append(record.value().get("id")).append("areasId ").append(record.value().get("areasId")).append("name").append(record.value().get("name")).append("address: ").append(record.value().get("address")).append("clubColors:").
                            append(record.value().get("clubColors")).append("}").toString());
                    System.out.println("Message received " + "partition: " +  record.partition() + "offset: " + record.offset());
                }
            }
        }
        catch (WakeupException e){
            System.out.println("Expected exception");
        }
        catch (Exception e){
            System.out.println(e.toString() + e.getLocalizedMessage());

        }
        finally {
            consumer.close();
        }
    }

    public static Properties getProperties(){
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty("schema.registry.url", "http://localhost:8081");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "consumerGroupTeams");
        return properties;
    }

}

