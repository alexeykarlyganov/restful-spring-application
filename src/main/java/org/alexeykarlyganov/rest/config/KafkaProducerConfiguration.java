package org.alexeykarlyganov.rest.config;

import org.alexeykarlyganov.rest.models.Entry;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfiguration {
    @Value("${kafka.bootstrap-servers}")
    private String kafkaServer;

    //@Value("${kafka.producer.id}")
   // private String kafkaProducerId;

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.kafkaServer);
        //props.put(ProducerConfig.CLIENT_ID_CONFIG, this.kafkaProducerId);

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<String, Entry> producerFactory() {
        return new DefaultKafkaProducerFactory<>(this.producerConfig());
    }

    @Bean
    public KafkaTemplate<String, Entry> kafkaTemplate() {
        KafkaTemplate<String, Entry> template = new KafkaTemplate<>(this.producerFactory());

        template.setMessageConverter(new StringJsonMessageConverter());

        return template;
    }

}
