package api.controller;

import api.enums.TipoLancamento;
import api.model.LancamentoModel;
import api.service.LancamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
@Tag(name = "Lançamentos", description = "Cadastro dos Lançamentos")
@Validated
public class LancamentoController {

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<LancamentoModel> listarLancamentoPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(lancamentoService.listarLancamentoPorId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/data/{data}")
    public ResponseEntity<List<LancamentoModel>> listarLancamentoPorData(@PathVariable("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return new ResponseEntity<>(lancamentoService.listarLancamentoPorData(data), HttpStatus.OK);
    }

    @GetMapping(value = "/tipo/{tipoLancamento}")
    public ResponseEntity<List<LancamentoModel>> listarLancamentoPorTipo(@PathVariable("tipoLancamento") String tipoLancamento) {
        return new ResponseEntity<>(lancamentoService.listarLancamentoPorTipo(TipoLancamento.fromValue(tipoLancamento)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LancamentoModel>> listarLancamentos() {
        return new ResponseEntity<>(lancamentoService.listarLancamentos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LancamentoModel> criarLancamento(@Valid @RequestBody LancamentoModel lancamentoModel) {
        return new ResponseEntity<>(lancamentoService.criarLancamento(lancamentoModel), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LancamentoModel> alterarLancamento(@PathVariable("id") Long id, @Valid @RequestBody LancamentoModel lancamentoModel) {
        return new ResponseEntity<>(lancamentoService.alterarLancamento(id, lancamentoModel), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> excluirLancamento(@PathVariable("id") Long id) {
        return new ResponseEntity<>(lancamentoService.excluirLancamento(id), HttpStatus.OK);
    }

}