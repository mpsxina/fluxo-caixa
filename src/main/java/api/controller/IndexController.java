package api.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@OpenAPIDefinition(info = @Info(title = "Fluxo de Caixa", description = "API REST Controle de Fluxo de Caixa"))
@Controller
public class IndexController {

    @GetMapping("/")
    public ResponseEntity<?> index() {
        return new ResponseEntity<>("API REST Controle de Fluxo de Caixa", HttpStatus.OK);
    }

}
