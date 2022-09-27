package com.example.ConsumerApp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "kafka.consumer")
@PropertySource("application.properties")
@Component
public class Config {

    private String schemaUrl;
    private String bootstrapServer;
    private String keyDeserializer;
    private String valueDeserializer;
    private String autoOfsetReset;

    private String groupId;

    private String specificAvroReader;

    public String getSpecificAvroReader() {
        return specificAvroReader;
    }

    public void setSpecificAvroReader(String specificAvroReader) {
        this.specificAvroReader = specificAvroReader;
    }

    public String getBootstrapServer() {
        return bootstrapServer;
    }


    public String getKeyDeserializer() {
        return keyDeserializer;
    }


    public String getValueDeserializer() {
        return valueDeserializer;
    }


    public String getAutoOfsetReset() {
        return autoOfsetReset;
    }

    public String getGroupId() {
        return groupId;
    }


    public String getSchemaUrl() {
        return schemaUrl;
    }

    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }

    public void setBootstrapServer(String bootstrapServer) {
        this.bootstrapServer = bootstrapServer;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }

    public void setAutoOfsetReset(String autoOfsetReset) {
        this.autoOfsetReset = autoOfsetReset;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }


}
