package api.service;

import api.entity.ProdutoEntity;
import api.exception.ModelException;
import api.model.ProdutoModel;
import api.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarLacamentoPorId() {
        Long id = 1L;
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(id);
        produtoEntity.setNome("Produto 1");
        produtoEntity.setDescricao("Descrição do Produto 1");

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoEntity));

        ProdutoModel produtoModel = produtoService.listarLacamentoPorId(id);

        Assertions.assertEquals(produtoEntity.getId(), produtoModel.getId());
        Assertions.assertEquals(produtoEntity.getNome(), produtoModel.getNome());
        Assertions.assertEquals(produtoEntity.getDescricao(), produtoModel.getDescricao());
    }

    @Test
    public void testListarLacamentoPorIdNaoEncontrado() {
        Long id = 1L;

        when(produtoRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ModelException.class, () -> {
            produtoService.listarLacamentoPorId(id);
        });
    }

    @Test
    public void testListarProdutos() {
        List<ProdutoEntity> produtosEntity = new ArrayList<>();
        ProdutoEntity produtoEntity1 = new ProdutoEntity();
        produtoEntity1.setId(1L);
        produtoEntity1.setNome("Produto 1");
        produtoEntity1.setDescricao("Descrição do Produto 1");
        produtosEntity.add(produtoEntity1);

        ProdutoEntity produtoEntity2 = new ProdutoEntity();
        produtoEntity2.setId(2L);
        produtoEntity2.setNome("Produto 2");
        produtoEntity2.setDescricao("Descrição do Produto 2");
        produtosEntity.add(produtoEntity2);

        when(produtoRepository.findAll()).thenReturn(produtosEntity);

        List<ProdutoModel> produtosModel = produtoService.listarProdutos();

        Assertions.assertEquals(produtosEntity.size(), produtosModel.size());
        Assertions.assertEquals(produtosEntity.get(0).getId(), produtosModel.get(0).getId());
        Assertions.assertEquals(produtosEntity.get(0).getNome(), produtosModel.get(0).getNome());
        Assertions.assertEquals(produtosEntity.get(0).getDescricao(), produtosModel.get(0).getDescricao());
        Assertions.assertEquals(produtosEntity.get(1).getId(), produtosModel.get(1).getId());
        Assertions.assertEquals(produtosEntity.get(1).getNome(), produtosModel.get(1).getNome());
        Assertions.assertEquals(produtosEntity.get(1).getDescricao(), produtosModel.get(1).getDescricao());
    }

    @Test
    public void testListarProdutosNaoEncontrado() {
        List<ProdutoEntity> produtosEntity = new ArrayList<>();

        when(produtoRepository.findAll()).thenReturn(produtosEntity);

        Assertions.assertThrows(ModelException.class, () -> {
            produtoService.listarProdutos();
        });
    }

    @Test
    public void testCriarProduto() {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNome("Produto 1");
        produtoModel.setDescricao("Descrição do Produto 1");

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);
        produtoEntity.setNome("Produto 1");
        produtoEntity.setDescricao("Descrição do Produto 1");

        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntity);

        ProdutoModel produtoModelCriado = produtoService.criarProduto(produtoModel);

        Assertions.assertEquals(produtoEntity.getId(), produtoModelCriado.getId());
        Assertions.assertEquals(produtoEntity.getNome(), produtoModelCriado.getNome());
        Assertions.assertEquals(produtoEntity.getDescricao(), produtoModelCriado.getDescricao());
    }

    @Test
    public void testCriarProdutoErro() {
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNome("Produto 1");
        produtoModel.setDescricao("Descrição do Produto 1");

        when(produtoRepository.save(any(ProdutoEntity.class))).thenThrow(new RuntimeException());

        Assertions.assertThrows(ModelException.class, () -> {
            produtoService.criarProduto(produtoModel);
        });
    }

    @Test
    public void testAlterarProduto() {
        Long id = 1L;
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNome("Produto 1");
        produtoModel.setDescricao("Descrição do Produto 1");

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(id);
        produtoEntity.setNome("Produto 1");
        produtoEntity.setDescricao("Descrição do Produto 1");

        when(produtoRepository.existsById(id)).thenReturn(true);
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoEntity));
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntity);

        ProdutoModel produtoModelAlterado = produtoService.alterarProduto(id, produtoModel);

        Assertions.assertEquals(produtoEntity.getId(), produtoModelAlterado.getId());
        Assertions.assertEquals(produtoModel.getNome(), produtoModelAlterado.getNome());
        Assertions.assertEquals(produtoModel.getDescricao(), produtoModelAlterado.getDescricao());
    }

    @Test
    public void testAlterarProdutoNaoEncontrado() {
        Long id = 1L;
        ProdutoModel produtoModel = new ProdutoModel();
        produtoModel.setNome("Produto 1");
        produtoModel.setDescricao("Descrição do Produto 1");

        when(produtoRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(ModelException.class, () -> {
            produtoService.alterarProduto(id, produtoModel);
        });
    }

    @Test
    public void testExcluirProduto() {
        Long id = 1L;

        when(produtoRepository.existsById(id)).thenReturn(true);

        String mensagem = produtoService.excluirProduto(id);

        Assertions.assertEquals("Produto excluído com sucesso.", mensagem);
    }

    @Test
    public void testExcluirProdutoNaoEncontrado() {
        Long id = 1L;

        when(produtoRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(ModelException.class, () -> {
            produtoService.excluirProduto(id);
        });
    }
}