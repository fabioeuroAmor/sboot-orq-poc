package br.gov.cnpq.consumer;

import br.gov.cnpq.service.PocService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PocConsumer {

    @Autowired
    private PocService pocService;

    @Value("${topic1.name.consumer}")
    private String topicName;

    @KafkaListener(topics = "${topic1.name.consumer}", groupId = "group_id")
    public void consume(ConsumerRecord<String, String> payload){
        log.info("Tópico: {}", topicName);
        log.info("key: {}", payload.key());
        log.info("Headers: {}", payload.headers());
        log.info("Partion: {}", payload.partition());
        log.info("Order: {}", payload.value());

        pocService.send(payload.key());

    }

}