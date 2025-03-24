package br.gov.cnpq.produce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

public class PocProduceTest {

    @InjectMocks
    private PocProduce pocProduce;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Defina o valor de topicName aqui
        pocProduce = new PocProduce(kafkaTemplate);
        pocProduce.setTopicName("meu-topico"); // Defina o nome do tópico aqui
    }

    @Test
    public void testSendSuccess() {
        // Cenário: Mensagem enviada com sucesso
        String message = "Teste de mensagem";

        // Chamar o método a ser testado
        pocProduce.send(message);

        // Verificar se o método send foi chamado no KafkaTemplate com os parâmetros corretos
        verify(kafkaTemplate).send(eq("meu-topico"), eq(message));
    }

    @Test
    public void testSendException() {
        // Cenário: exceção lançada ao enviar a mensagem
        String message = "Teste de mensagem";

        // Simulando uma exceção ao chamar o método send do KafkaTemplate
        doThrow(new RuntimeException("Erro ao enviar mensagem")).when(kafkaTemplate).send(eq("meu-topico"), eq(message));

        // Chamar o método a ser testado, potencialmente capturando exceções logadas
        pocProduce.send(message);

        // Verificar se o método send foi chamado
        verify(kafkaTemplate).send(eq("meu-topico"), eq(message));
    }
}