package br.gov.cnpq.controller;


import br.gov.cnpq.json.Response;
import br.gov.cnpq.service.PocService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/poc")
public class PocController {

    @Autowired
    private PocService pocService;

    @GetMapping(value = "/teste")
    public ResponseEntity<Response> realizarTeste(@RequestParam(value = "mensagem", required = true) String mensagem){
        Response response = new Response();
        try {
            response.setModeloRetorno(null);
            response.setMensagensRetorno("Micorservice esta no ar" );
            pocService.send(mensagem);

        }catch (Exception e){
            log.error("Erro ao testar a poc: " + e.getMessage());
            response.setMensagensRetorno(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }


}