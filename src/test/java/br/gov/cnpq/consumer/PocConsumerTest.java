package br.gov.cnpq.consumer;

import br.gov.cnpq.service.PocService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageHeaderAccessor;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

class PocConsumerTest {

    @InjectMocks
    private PocConsumer pocConsumer;

    @Mock
    private PocService pocService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testConsume() {
        // Cenário: Criar um ConsumerRecord simulado
        String topicName = "meu-topico";
        String key = "minha-chave";
        String value = "minha-mensagem";

        // Criar um ConsumerRecord simulado
        ConsumerRecord<String, String> record = new ConsumerRecord<>(topicName, 0, 0L, key, value);

        // Chamar o método a ser testado
        pocConsumer.consume(record);

        // Verificar se o método send do PocService foi chamado com a chave correta
        verify(pocService).send(eq(key));
    }
}