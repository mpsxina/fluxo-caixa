package api.service;

import api.ApplicationTests;
import api.entity.LancamentoEntity;
import api.entity.ProdutoEntity;
import api.enums.TipoLancamento;
import api.exception.ModelException;
import api.model.LancamentoModel;
import api.repository.LancamentoRepository;
import api.repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class LancamentoServiceTest extends ApplicationTests {

    @Mock
    private LancamentoRepository lancamentoRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private LancamentoService lancamentoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarLancamentoPorId() {
        LancamentoEntity lancamentoEntity = new LancamentoEntity();
        lancamentoEntity.setId(1L);
        lancamentoEntity.setDataLancamento(LocalDate.now());
        lancamentoEntity.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoEntity.setValor(BigDecimal.TEN);

        when(lancamentoRepository.findById(1L)).thenReturn(Optional.of(lancamentoEntity));

        LancamentoModel lancamentoModel = lancamentoService.listarLancamentoPorId(1L);

        Assertions.assertEquals(lancamentoEntity.getId(), lancamentoModel.getId());
        Assertions.assertEquals(lancamentoEntity.getDataLancamento(), lancamentoModel.getDataLancamento());
        Assertions.assertEquals(lancamentoEntity.getTipoLancamento(), lancamentoModel.getTipoLancamento());
        Assertions.assertEquals(lancamentoEntity.getValor(), lancamentoModel.getValor());
    }

    @Test
    public void testListarLancamentoPorDataLancamento() {
        List<LancamentoEntity> lancamentosEntity = new ArrayList<>();
        LancamentoEntity lancamentoEntity = new LancamentoEntity();
        lancamentoEntity.setId(1L);
        lancamentoEntity.setDataLancamento(LocalDate.now());
        lancamentoEntity.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoEntity.setValor(BigDecimal.TEN);
        lancamentosEntity.add(lancamentoEntity);

        when(lancamentoRepository.findByDataLancamento(LocalDate.now())).thenReturn(lancamentosEntity);

        List<LancamentoModel> lancamentosModel = lancamentoService.listarLancamentoPorDataLancamento(LocalDate.now());

        Assertions.assertEquals(lancamentosEntity.size(), lancamentosModel.size());
        Assertions.assertEquals(lancamentosEntity.get(0).getId(), lancamentosModel.get(0).getId());
        Assertions.assertEquals(lancamentosEntity.get(0).getDataLancamento(), lancamentosModel.get(0).getDataLancamento());
        Assertions.assertEquals(lancamentosEntity.get(0).getTipoLancamento(), lancamentosModel.get(0).getTipoLancamento());
        Assertions.assertEquals(lancamentosEntity.get(0).getValor(), lancamentosModel.get(0).getValor());
    }

    @Test
    public void testListarLancamentoPorTipo() {
        List<LancamentoEntity> lancamentosEntity = new ArrayList<>();
        LancamentoEntity lancamentoEntity = new LancamentoEntity();
        lancamentoEntity.setId(1L);
        lancamentoEntity.setDataLancamento(LocalDate.now());
        lancamentoEntity.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoEntity.setValor(BigDecimal.TEN);
        lancamentosEntity.add(lancamentoEntity);

        when(lancamentoRepository.findByTipoLancamento(TipoLancamento.CREDITO)).thenReturn(lancamentosEntity);

        List<LancamentoModel> lancamentosModel = lancamentoService.listarLancamentoPorTipo(TipoLancamento.CREDITO);

        Assertions.assertEquals(lancamentosEntity.size(), lancamentosModel.size());
        Assertions.assertEquals(lancamentosEntity.get(0).getId(), lancamentosModel.get(0).getId());
        Assertions.assertEquals(lancamentosEntity.get(0).getDataLancamento(), lancamentosModel.get(0).getDataLancamento());
        Assertions.assertEquals(lancamentosEntity.get(0).getTipoLancamento(), lancamentosModel.get(0).getTipoLancamento());
        Assertions.assertEquals(lancamentosEntity.get(0).getValor(), lancamentosModel.get(0).getValor());
    }

    @Test
    public void testListarLancamentos() {
        List<LancamentoEntity> lancamentosEntity = new ArrayList<>();
        LancamentoEntity lancamentoEntity = new LancamentoEntity();
        lancamentoEntity.setId(1L);
        lancamentoEntity.setDataLancamento(LocalDate.now());
        lancamentoEntity.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoEntity.setValor(BigDecimal.TEN);
        lancamentosEntity.add(lancamentoEntity);

        when(lancamentoRepository.findAll()).thenReturn(lancamentosEntity);

        List<LancamentoModel> lancamentosModel = lancamentoService.listarLancamentos();

        Assertions.assertEquals(lancamentosEntity.size(), lancamentosModel.size());
        Assertions.assertEquals(lancamentosEntity.get(0).getId(), lancamentosModel.get(0).getId());
        Assertions.assertEquals(lancamentosEntity.get(0).getDataLancamento(), lancamentosModel.get(0).getDataLancamento());
        Assertions.assertEquals(lancamentosEntity.get(0).getTipoLancamento(), lancamentosModel.get(0).getTipoLancamento());
        Assertions.assertEquals(lancamentosEntity.get(0).getValor(), lancamentosModel.get(0).getValor());
    }

    @Test
    public void testCriarLancamento() {
        LancamentoModel lancamentoModel = new LancamentoModel();
        lancamentoModel.setIdProduto(1L);
        lancamentoModel.setDataLancamento(LocalDate.now());
        lancamentoModel.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoModel.setValor(BigDecimal.TEN);

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoEntity));

        LancamentoEntity lancamentoEntity = new LancamentoEntity();
        lancamentoEntity.setId(1L);
        lancamentoEntity.setDataLancamento(lancamentoModel.getDataLancamento());
        lancamentoEntity.setTipoLancamento(lancamentoModel.getTipoLancamento());
        lancamentoEntity.setValor(lancamentoModel.getValor());
        lancamentoEntity.setProduto(produtoEntity);

        when(lancamentoRepository.save(any(LancamentoEntity.class))).thenReturn(lancamentoEntity);

        LancamentoModel lancamentoCriado = lancamentoService.criarLancamento(lancamentoModel);

        Assertions.assertEquals(lancamentoEntity.getId(), lancamentoCriado.getId());
        Assertions.assertEquals(lancamentoEntity.getDataLancamento(), lancamentoCriado.getDataLancamento());
        Assertions.assertEquals(lancamentoEntity.getTipoLancamento(), lancamentoCriado.getTipoLancamento());
        Assertions.assertEquals(lancamentoEntity.getValor(), lancamentoCriado.getValor());
        Assertions.assertEquals(lancamentoEntity.getProduto().getId(), lancamentoCriado.getIdProduto());
    }

    @Test
    public void testCriarLancamentoProdutoNaoEncontrado() {
        LancamentoModel lancamentoModel = new LancamentoModel();
        lancamentoModel.setIdProduto(1L);
        lancamentoModel.setDataLancamento(LocalDate.now());
        lancamentoModel.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoModel.setValor(BigDecimal.TEN);

        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ModelException.class, () -> {
            lancamentoService.criarLancamento(lancamentoModel);
        });
    }

    @Test
    public void testCriarLancamentoErroAoSalvar() {
        LancamentoModel lancamentoModel = new LancamentoModel();
        lancamentoModel.setIdProduto(1L);
        lancamentoModel.setDataLancamento(LocalDate.now());
        lancamentoModel.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoModel.setValor(BigDecimal.TEN);

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produtoEntity));

        when(lancamentoRepository.save(any(LancamentoEntity.class))).thenThrow(new RuntimeException());

        Assertions.assertThrows(ModelException.class, () -> {
            lancamentoService.criarLancamento(lancamentoModel);
        });
    }

    @Test
    public void testAlterarLancamento() {
        Long id = 1L;
        LancamentoModel lancamentoModel = new LancamentoModel();
        lancamentoModel.setId(id);
        lancamentoModel.setIdProduto(1L);
        lancamentoModel.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoModel.setValor(BigDecimal.ONE);
        lancamentoModel.setDataLancamento(LocalDate.now());

        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(lancamentoModel.getIdProduto());

        when(produtoRepository.findById(lancamentoModel.getIdProduto())).thenReturn(Optional.of(produtoEntity));

        LancamentoEntity lancamentoEntity = new LancamentoEntity();
        lancamentoEntity.setId(id);
        lancamentoEntity.setTipoLancamento(lancamentoModel.getTipoLancamento());
        lancamentoEntity.setProduto(produtoEntity);
        lancamentoEntity.setValor(BigDecimal.TEN);
        lancamentoEntity.setDataLancamento(lancamentoModel.getDataLancamento());

        when(lancamentoRepository.existsById(id)).thenReturn(true);
        when(lancamentoRepository.findById(id)).thenReturn(Optional.of(lancamentoEntity));
        when(lancamentoRepository.save(lancamentoEntity)).thenReturn(lancamentoEntity);

        LancamentoModel lancamentoAlterado = lancamentoService.alterarLancamento(id, lancamentoModel);

        Assertions.assertEquals(lancamentoEntity.getId(), lancamentoAlterado.getId());
        Assertions.assertEquals(lancamentoEntity.getDataLancamento(), lancamentoAlterado.getDataLancamento());
        Assertions.assertEquals(lancamentoEntity.getTipoLancamento(), lancamentoAlterado.getTipoLancamento());
        Assertions.assertEquals(lancamentoEntity.getValor(), lancamentoAlterado.getValor());
        Assertions.assertEquals(lancamentoEntity.getProduto().getId(), lancamentoAlterado.getIdProduto());

        Assertions.assertEquals(lancamentoModel, lancamentoAlterado);
    }

    @Test
    public void testAlterarLancamentoNaoEncontrado() {
        Long id = 1L;
        LancamentoModel lancamentoModel = new LancamentoModel();

        when(lancamentoRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(ModelException.class, () -> {
            lancamentoService.alterarLancamento(id, lancamentoModel);
        });

        verify(lancamentoRepository, times(1)).existsById(id);
        verify(lancamentoRepository, times(0)).findById(id);
        verify(produtoRepository, times(0)).findById(anyLong());
        verify(lancamentoRepository, times(0)).save(any(LancamentoEntity.class));
    }

    @Test
    public void testAlterarLancamentoProdutoNaoEncontrado() {
        Long id = 1L;
        LancamentoModel lancamentoModel = new LancamentoModel();
        lancamentoModel.setIdProduto(1L);

        LancamentoEntity lancamentoEntity = new LancamentoEntity();
        lancamentoEntity.setId(id);
        lancamentoEntity.setTipoLancamento(TipoLancamento.DEBITO);
        lancamentoEntity.setProduto(new ProdutoEntity());
        lancamentoEntity.setValor(BigDecimal.TEN);
        lancamentoEntity.setDataLancamento(LocalDate.now());

        when(lancamentoRepository.existsById(id)).thenReturn(true);
        when(lancamentoRepository.findById(id)).thenReturn(Optional.of(lancamentoEntity));
        when(produtoRepository.findById(lancamentoModel.getIdProduto())).thenReturn(Optional.empty());

        Assertions.assertThrows(ModelException.class, () -> {
            lancamentoService.alterarLancamento(id, lancamentoModel);
        });

        verify(lancamentoRepository, times(1)).existsById(id);
        verify(lancamentoRepository, times(1)).findById(id);
        verify(produtoRepository, times(1)).findById(lancamentoModel.getIdProduto());
        verify(lancamentoRepository, times(0)).save(any(LancamentoEntity.class));
    }

    @Test
    public void testExcluirLancamento() {
        Long id = 1L;

        when(lancamentoRepository.existsById(id)).thenReturn(true);

        String result = lancamentoService.excluirLancamento(id);

        Assertions.assertEquals("Lançamento excluído com sucesso.", result);

        verify(lancamentoRepository, times(1)).existsById(id);
        verify(lancamentoRepository, times(1)).deleteById(id);
    }

    @Test
    public void testExcluirLancamentoNaoEncontrado() {
        Long id = 1L;

        when(lancamentoRepository.existsById(id)).thenReturn(false);

        Assertions.assertThrows(ModelException.class, () -> {
            lancamentoService.excluirLancamento(id);
        });

        verify(lancamentoRepository, times(1)).existsById(id);
        verify(lancamentoRepository, times(0)).deleteById(id);
    }

}