package api.controller;

import api.model.ProdutoModel;
import api.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Cadastro dos Produtos")
@Validated
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoModel> listarProdutoPorId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(produtoService.listarProdutoPorId(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        return new ResponseEntity<>(produtoService.listarProdutos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> criarProduto(@Valid @RequestBody ProdutoModel produtoModel) {
        return new ResponseEntity<>(produtoService.criarProduto(produtoModel), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdutoModel> alterarProduto(@PathVariable("id") Long id, @Valid @RequestBody ProdutoModel produtoModel) {
        return new ResponseEntity<>(produtoService.alterarProduto(id, produtoModel), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> excluirProduto(@PathVariable("id") Long id) {
        return new ResponseEntity<>(produtoService.excluirProduto(id), HttpStatus.OK);
    }

}