package br.gov.cnpq.service;

import br.gov.cnpq.exception.NegocioException;
import br.gov.cnpq.produce.PocProduce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PocService {


    @Autowired
    private PocProduce pocProduce;


    public void send(String mensagem) throws NegocioException {
        try {
            pocProduce.send(mensagem);
        }catch (Exception e) {
            log.error("Erro na camda de servico ao produzir a mensagem no topico: " + e.getMessage());
            throw new NegocioException(e.getMessage());
        }

    }
}
