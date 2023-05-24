package api.repository;

import api.entity.LancamentoEntity;
import api.enums.TipoLancamento;
import api.model.LancamentoModel;
import api.service.LancamentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LancamentoRepositoryTest {

    @Mock
    private LancamentoRepository lancamentoRepositoryMock;

    @InjectMocks
    private LancamentoService lancamentoService;

    @Test
    public void testFindAll() {
        LancamentoEntity lancamentoEntity = new LancamentoEntity();
        lancamentoEntity.setId(1L);
        lancamentoEntity.setDataLancamento(LocalDate.now());
        lancamentoEntity.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoEntity.setValor(BigDecimal.ONE);

        List<LancamentoEntity> lancamentos = new ArrayList<>();
        lancamentos.add(lancamentoEntity);

        LancamentoEntity lancamentoEntity2 = new LancamentoEntity();
        lancamentoEntity2.setId(2L);
        lancamentoEntity2.setDataLancamento(LocalDate.now());
        lancamentoEntity2.setTipoLancamento(TipoLancamento.DEBITO);
        lancamentoEntity2.setValor(BigDecimal.TEN);

        lancamentos.add(lancamentoEntity2);
        when(lancamentoRepositoryMock.findAll()).thenReturn(lancamentos);

        List<LancamentoModel> listarLancamentos = lancamentoService.listarLancamentos();

        verify(lancamentoRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testFindByDataLancamento() {
        LocalDate data = LocalDate.now();
        LancamentoEntity lancamentoEntity = new LancamentoEntity();
        lancamentoEntity.setId(1L);
        lancamentoEntity.setDataLancamento(LocalDate.now());
        lancamentoEntity.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoEntity.setValor(BigDecimal.ONE);

        List<LancamentoEntity> lancamentos = new ArrayList<>();
        lancamentos.add(lancamentoEntity);

        LancamentoEntity lancamentoEntity2 = new LancamentoEntity();
        lancamentoEntity2.setId(2L);
        lancamentoEntity2.setDataLancamento(LocalDate.now());
        lancamentoEntity2.setTipoLancamento(TipoLancamento.DEBITO);
        lancamentoEntity2.setValor(BigDecimal.TEN);

        lancamentos.add(lancamentoEntity2);

        when(lancamentoRepositoryMock.findByDataLancamento(data)).thenReturn(lancamentos);

        List<LancamentoModel> listarLancamentos = lancamentoService.listarLancamentoPorDataLancamento(data);

        verify(lancamentoRepositoryMock, times(1)).findByDataLancamento(data);
    }

    @Test
    public void testFindByTipoLancamento() {
        TipoLancamento tipoLancamento = TipoLancamento.CREDITO;

        LancamentoEntity lancamentoEntity = new LancamentoEntity();
        lancamentoEntity.setId(1L);
        lancamentoEntity.setDataLancamento(LocalDate.now());
        lancamentoEntity.setTipoLancamento(TipoLancamento.CREDITO);
        lancamentoEntity.setValor(BigDecimal.ONE);

        List<LancamentoEntity> lancamentos = new ArrayList<>();
        lancamentos.add(lancamentoEntity);

        LancamentoEntity lancamentoEntity2 = new LancamentoEntity();
        lancamentoEntity2.setId(2L);
        lancamentoEntity2.setDataLancamento(LocalDate.now());
        lancamentoEntity2.setTipoLancamento(TipoLancamento.DEBITO);
        lancamentoEntity2.setValor(BigDecimal.TEN);

        lancamentos.add(lancamentoEntity2);
        when(lancamentoRepositoryMock.findByTipoLancamento(tipoLancamento)).thenReturn(lancamentos);

        List<LancamentoModel> listarLancamento = lancamentoService.listarLancamentoPorTipo(tipoLancamento);

        verify(lancamentoRepositoryMock, times(1)).findByTipoLancamento(tipoLancamento);
    }
}