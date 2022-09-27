package sendData;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Properties;

@JsonIgnoreProperties
public class ProducerTeams {
    public static void main(String[] args) throws  IOException{
        String schemaString  = getAvro();
        Schema schema = new Schema.Parser().parse(schemaString);
        GenericRecord genericRecord = new GenericData.Record(schema);
        URL url = new URL("http://api.football-data.org/v4/teams");
        TeamsDataDto dataAvro = getData(url);
        ArrayList<TeamDataDto> list=dataAvro.getTeams();
        String topic = "teamsTopic";
        KafkaProducer<String, GenericRecord> producer = new KafkaProducer<String, GenericRecord>(getProperties());
        for (int i = 1;i<=5;i++){
            URL urlAreas = new URL("http://api.football-data.org/v4/teams/" + i);
            AreaDto area = getArea(urlAreas);
            System.out.println(area.getId());
            GenericRecord record = new GenericData.Record(schema);
            record.put("id", list.get(i).getId());
            record.put("areaId",area.getId());
            record.put("name", list.get(i).getName());
            record.put("address", list.get(i).getAddress());
            record.put("clubColors", list.get(i).getClubColors());
            ProducerRecord<String, GenericRecord> recordCountry = new ProducerRecord<>(topic, String.valueOf(list.get(i).getId()), record);
            producer.send(recordCountry, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e==null){
                        System.out.println("Success");
                    }
                    else{
                        System.out.println("I am sorry, error");
                        System.out.println(e.toString());
                    }
                }
            });
        }
        producer.flush();
        producer.close();

    }

    public static Properties getProperties(){
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://localhost:8081");
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        return properties;
    }
    private static String getAvro(){
        String avro = " {\n" +
                "            \"name\": \"Teams\",\n" +
                "            \"type\": \"record\",\n" +
                "            \"fields\": [\n" +
                "              {\n" +
                "                \"name\": \"id\",\n" +
                "                \"type\": \"int\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"areaId\",\n" +
                "                \"type\": \"int\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"name\",\n" +
                "                \"type\": \"string\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"address\",\n" +
                "                \"type\": \"string\"\n" +
                "              },\n" +
                "              {\n" +
                "                \"name\": \"clubColors\",\n" +
                "                \"type\": \"string\"\n" +
                "              }]}";
        return  avro;
    }
    public static TeamsDataDto getData(URL url ) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("X-Auth-Token", "0148d0534d34417a8435572f1ab7f9ef");
        conn.setRequestMethod("GET");
        conn.connect();
        BufferedReader in  = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));//kad ovo dodamm ne baca 403
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        TeamsDataDto data = mapper.readValue(in.readLine(), TeamsDataDto.class);
        return data;
    }
    public static AreaDto getArea(URL url) throws  IOException{
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("X-Auth-Token", "0148d0534d34417a8435572f1ab7f9ef");
        conn.setRequestMethod("GET");
        conn.connect();
        BufferedReader in  = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("UTF-8")));
        ObjectMapper mapper2 = new ObjectMapper();
        mapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        AreaDto data2 = mapper2.readValue(in.readLine(), AreaDto.class);
        return data2;
    }
}
