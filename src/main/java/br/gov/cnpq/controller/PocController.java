package br.gov.cnpq.controller;


import br.gov.cnpq.json.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/poc")
public class PocController {

    @GetMapping(value = "/teste")
    public ResponseEntity<Response> realizarTeste(){
        Response response = new Response();
        try {
            response.setModeloRetorno(null);
            response.setMensagensRetorno("Micorservice esta no ar" );

        }catch (Exception e){
            log.error("Erro ao testar a poc: " + e.getMessage());
            response.setMensagensRetorno(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }


}