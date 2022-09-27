package receiveData;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

public class ConsumerAvroFootball {
    public static void main(String[] args)  throws  Exception{
        String topic = "secondProject";
        FileWriter fileWriter = new FileWriter("filename.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        Schema schemaCountry = new Schema.Parser().parse(schemaString());
        KafkaConsumer<String, CountryFields> consumer = new KafkaConsumer<String, CountryFields>(properties());
        Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void  run(){
                System.out.println("Detected a shutdown");
                consumer.wakeup();
              try{
                  mainThread.join();
              }
              catch (Exception e){
                    e.printStackTrace();
              }
            }
        });
       try {
           consumer.subscribe(Collections.singletonList(topic));
           ArrayList<CountryFields> list = new ArrayList<>();
           while (true) {
               ConsumerRecords<String, CountryFields> countries = consumer.poll(Duration.ofMillis(1000));
                   for (ConsumerRecord<String, CountryFields> country : countries) {
                       printWriter.print(new StringBuilder().append("{ areasId: ").append(country.value().getId()).append("name ").append(country.value().getName()).append("countryCode").append(country.value().getCountryCode()).append("flag: ").append(country.value().getFlag()).append("parentAreaId:").append(country.value().getParentAreaId()).append("parentArea: ").append(country.value().getParentArea()).append("}").toString());
                       System.out.println("Message received " + country.partition() + country.offset());
                   }
           }
       }
       catch (WakeupException e){
           System.out.println("Expected excepton, Wake up ");
       }
       catch (Exception d){
           System.out.println("Unexptected exception");
       }
       finally {
           consumer.close();
       }
    }
        public static Properties properties() {
            Properties prop = new Properties();
            prop.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            prop.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
            prop.setProperty("schema.registry.url", "http://localhost:8081");
            prop.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
            prop.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "cosumersGroup1");
            prop.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            return prop;
        }

        public static String schemaString() {
            String country = "{\n" +
                    "            \"name\": \"Country\",\n" +
                    "            \"type\": \"record\",\n" +
                    "            \"fields\": [\n" +
                    "              {\n" +
                    "                \"name\": \"id\",\n" +
                    "                \"type\": \"int\"\n" +
                    "              },\n" +
                    "              {\n" +
                    "                \"name\": \"name\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              {\n" +
                    "                \"name\": \"countryCode\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              {\n" +
                    "                \"name\": \"flag\",\n" +
                    "                \"type\": \"string\"\n" +
                    "              },\n" +
                    "              {\n" +
                    "                \"name\": \"parentAreaId\",\n" +
                    "                \"type\": [\"int\",\"null\"]\n" +
                    "              },\n" +
                    "              {\n" +
                    "                \"name\": \"parentArea\",\n" +
                    "                \"type\": [\"string\", \"null\"]\n" +
                    "              }\n" +
                    "            ]\n" +
                    "          }";
            return country;
        }
}
