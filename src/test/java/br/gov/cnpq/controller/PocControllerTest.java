package br.gov.cnpq.controller;

import br.gov.cnpq.json.Response;
import br.gov.cnpq.service.PocService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PocControllerTest {

    @InjectMocks
    private PocController pocController;

    @Mock
    private PocService pocService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRealizarTesteSuccess() {
        // Cenário: Mensagem é enviada com sucesso

        String mensagem = "Teste de mensagem";

        // Chamar o método a ser testado
        ResponseEntity<Response> responseEntity = pocController.realizarTeste(mensagem);

        // Verificar se o método send do PocService foi chamado
        verify(pocService).send(mensagem);

        // Verificar a resposta
        Response response = responseEntity.getBody();
        assertEquals("Micorservice esta no ar", response.getMensagensRetorno());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void testRealizarTesteException() {
        // Cenário: Exceção lançada ao enviar a mensagem
        String mensagem = "Teste de mensagem";

        // Simular exceção ao chamar o método send do PocService
        doThrow(new RuntimeException("Erro ao enviar mensagem")).when(pocService).send(mensagem);

        // Chamar o método a ser testado
        ResponseEntity<Response> responseEntity = pocController.realizarTeste(mensagem);

        // Verificar se o método send do PocService foi chamado
        verify(pocService).send(mensagem);

        // Verificar a resposta
        Response response = responseEntity.getBody();
        assertEquals("Erro ao enviar mensagem", response.getMensagensRetorno());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}