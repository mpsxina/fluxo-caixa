package api.repository;

import api.entity.ProdutoEntity;
import api.model.ProdutoModel;
import api.service.ProdutoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoRepositoryTest {

    @Mock
    private ProdutoRepository produtoRepositoryMock;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void testFindAll() {
        List<ProdutoEntity> produtosEntity = new ArrayList<>();
        produtosEntity.add(new ProdutoEntity(1L, "Produto1", "Descrição do Produto1"));
        produtosEntity.add(new ProdutoEntity(2L, "Produto2", "Descrição do Produto2"));

        List<ProdutoModel> produtosModel = new ArrayList<>();
        produtosModel.add(new ProdutoModel(1L, "Produto1", "Descrição do Produto1"));
        produtosModel.add(new ProdutoModel(2L, "Produto2", "Descrição do Produto2"));

        when(produtoRepositoryMock.findAll()).thenReturn(produtosEntity);

        List<ProdutoModel> listarProdutos = produtoService.listarProdutos();

        verify(produtoRepositoryMock, times(1)).findAll();
        Assertions.assertEquals(produtosModel, listarProdutos);
    }

}