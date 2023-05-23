package api.controller;

import api.model.SaldoDiarioModel;
import api.service.SaldoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/saldo")
@Tag(name = "Saldo", description = "Saldo Diário dos Lançamentos")
public class SaldoController {

    @Autowired
    private SaldoService saldoService;

    @GetMapping("/{data}")
    public ResponseEntity<SaldoDiarioModel> calcularSaldoDiario(@PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return new ResponseEntity<>(saldoService.calcularSaldo(data), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<SaldoDiarioModel> calcularSaldoCompleto() {
        return new ResponseEntity<>(saldoService.calcularSaldo(null), HttpStatus.OK);
    }

}