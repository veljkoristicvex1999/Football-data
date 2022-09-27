package sendData;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

public class AvroFootball {
    public static void main(String[] args) throws IOException, FileNotFoundException {
        URL url = new URL("https://api.football-data.org/v4/areas");
        Schema schemaCountry = new Schema.Parser().parse(parseAvroCountry());
        Properties properties = getProperties();
        FootballData sendFootbalData = storeData(url);
        KafkaProducer<String, Object> producer = new KafkaProducer<>(properties);
        String topic = "secondProject";
        ArrayList<CountryFields> countryList = sendFootbalData.getAreas();
        try {
            for (CountryFields country : countryList) {
                GenericRecord avroRecordCountry = new GenericData.Record(schemaCountry);
                avroRecordCountry.put("id", country.getId());
                avroRecordCountry.put("name", country.getName());
                avroRecordCountry.put("countryCode", country.getCountryCode());
                avroRecordCountry.put("flag", "");
                avroRecordCountry.put("parentAreaId", country.getParentAreaId());
                avroRecordCountry.put("parentArea", country.getParentArea());
                ProducerRecord<String, Object> recordCountry = new ProducerRecord<>(topic, String.valueOf(country.getId()), avroRecordCountry);
                producer.send(recordCountry, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (e == null) {
                            System.out.println("Exception");
                        } else {
                            System.out.println(recordMetadata.topic());
                            System.out.println(recordMetadata.partition());
                        }
                    }
                });
            }
        } finally {
            producer.flush();
            producer.close();
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty("schema.registry.url", "http://localhost:8081");


        return properties;
    }

    public static String parseAvroCountry() {
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


    public static FootballData storeData(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Auth-Token", "0148d0534d34417a8435572f1ab7f9ef");
        conn.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        ObjectMapper mapper = new ObjectMapper();
        FootballData data = mapper.readValue(in.readLine(), FootballData.class);
        return data;
    }


}
