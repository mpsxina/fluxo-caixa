package api.repository;

import api.entity.ProdutoEntity;
import api.model.ProdutoModel;
import api.service.ProdutoService;
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
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);
        produtoEntity.setNome("Produto 1");
        produtoEntity.setDescricao("Descrição do Produto 1");

        List<ProdutoEntity> produtos = new ArrayList<>();
        produtos.add(produtoEntity);

        ProdutoEntity produtoEntity2 = new ProdutoEntity();
        produtoEntity2.setId(2L);
        produtoEntity2.setNome("Produto 2");
        produtoEntity2.setDescricao("Descrição do Produto 2");

        produtos.add(produtoEntity2);
        when(produtoRepositoryMock.findAll()).thenReturn(produtos);

        List<ProdutoModel> listarProdutos = produtoService.listarProdutos();

        verify(produtoRepositoryMock, times(1)).findAll();
    }

}