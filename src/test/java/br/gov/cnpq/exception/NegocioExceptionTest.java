package br.gov.cnpq.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NegocioExceptionTest {

    @Test
    void testNegocioExceptionWithMessage() {
        String message = "Uma mensagem de erro";
        NegocioException exception = new NegocioException(message);

        // Verifique se a mensagem da exceção é a esperada
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testNegocioExceptionWithMessageAndCause() {
        String message = "Uma mensagem de erro";
        Throwable cause = new RuntimeException("Causa da exceção");
        NegocioException exception = new NegocioException(message, cause);

        // Verifique se a mensagem da exceção é a esperada
        assertEquals(message, exception.getMessage());
        // Verifique se a causa da exceção é a esperada
        assertEquals(cause, exception.getCause());
    }


}