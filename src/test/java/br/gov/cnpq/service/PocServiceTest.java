package br.gov.cnpq.service;

import br.gov.cnpq.exception.NegocioException;
import br.gov.cnpq.produce.PocProduce;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class PocServiceTest {

    @InjectMocks
    private PocService pocService;

    @Mock
    private PocProduce pocProduce;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendSuccess() throws NegocioException {
        // Cenário: a mensagem é enviada com sucesso
        String mensagem = "Teste de mensagem";

        // Chamar o método a ser testado
        pocService.send(mensagem);

        // Verificar se o método send() do PocProduce foi chamado
        verify(pocProduce).send(mensagem);
    }

    @Test
    void testSendThrowsNegocioException() {
        // Cenário: exceção lançada ao produzir a mensagem
        String mensagem = "Teste de mensagem";
        String errorMessage = "Erro ao enviar mensagem";

        // Simule uma exceção ao chamar o método send() de PocProduce
        doThrow(new RuntimeException(errorMessage)).when(pocProduce).send(mensagem);

        // Verifique se uma NegocioException é lançada
        try {
            pocService.send(mensagem);
        } catch (NegocioException e) {
            // Verifique a mensagem de erro
            assert e.getMessage().equals(errorMessage);
            return; // o teste passou
        }

        // Se não lançar, falha o teste.
        throw new AssertionError("NegocioException não foi lançada.");
    }
}