package api.repository;

import api.entity.LancamentoEntity;
import api.enums.TipoLancamento;
import api.model.LancamentoModel;
import api.service.LancamentoService;
import org.junit.jupiter.api.Assertions;
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
        LocalDate data = LocalDate.now();

        List<LancamentoEntity> lancamentosEntity = new ArrayList<>();
        lancamentosEntity.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("30.00"), data));
        lancamentosEntity.add(new LancamentoEntity(TipoLancamento.DEBITO, new BigDecimal("10.00"), data));
        lancamentosEntity.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("50.00"), data));

        List<LancamentoModel> lancamentosModel = new ArrayList<>();
        lancamentosModel.add(new LancamentoModel(TipoLancamento.CREDITO, new BigDecimal("30.00"), data));
        lancamentosModel.add(new LancamentoModel(TipoLancamento.DEBITO, new BigDecimal("10.00"), data));
        lancamentosModel.add(new LancamentoModel(TipoLancamento.CREDITO, new BigDecimal("50.00"), data));

        when(lancamentoRepositoryMock.findAll()).thenReturn(lancamentosEntity);

        List<LancamentoModel> listarLancamentos = lancamentoService.listarLancamentos();

        verify(lancamentoRepositoryMock, times(1)).findAll();
        Assertions.assertEquals(lancamentosModel, listarLancamentos);
    }

    @Test
    public void testFindByDataLancamento() {
        LocalDate data = LocalDate.now();

        List<LancamentoEntity> lancamentosEntity = new ArrayList<>();
        lancamentosEntity.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("30.00"), data));
        lancamentosEntity.add(new LancamentoEntity(TipoLancamento.DEBITO, new BigDecimal("10.00"), data));
        lancamentosEntity.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("50.00"), data));

        List<LancamentoModel> lancamentosModel = new ArrayList<>();
        lancamentosModel.add(new LancamentoModel(TipoLancamento.CREDITO, new BigDecimal("30.00"), data));
        lancamentosModel.add(new LancamentoModel(TipoLancamento.DEBITO, new BigDecimal("10.00"), data));
        lancamentosModel.add(new LancamentoModel(TipoLancamento.CREDITO, new BigDecimal("50.00"), data));

        when(lancamentoRepositoryMock.findByDataLancamento(data)).thenReturn(lancamentosEntity);

        List<LancamentoModel> listarLancamentos = lancamentoService.listarLancamentoPorDataLancamento(data);

        verify(lancamentoRepositoryMock, times(1)).findByDataLancamento(data);
        Assertions.assertEquals(lancamentosModel, listarLancamentos);
    }

    @Test
    public void testFindByTipoLancamento() {
        TipoLancamento tipoLancamento = TipoLancamento.CREDITO;
        LocalDate data = LocalDate.now();

        List<LancamentoEntity> lancamentosEntity = new ArrayList<>();
        lancamentosEntity.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("30.00"), data));
        lancamentosEntity.add(new LancamentoEntity(TipoLancamento.DEBITO, new BigDecimal("10.00"), data));
        lancamentosEntity.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("50.00"), data));

        List<LancamentoModel> lancamentosModel = new ArrayList<>();
        lancamentosModel.add(new LancamentoModel(TipoLancamento.CREDITO, new BigDecimal("30.00"), data));
        lancamentosModel.add(new LancamentoModel(TipoLancamento.DEBITO, new BigDecimal("10.00"), data));
        lancamentosModel.add(new LancamentoModel(TipoLancamento.CREDITO, new BigDecimal("50.00"), data));

        when(lancamentoRepositoryMock.findByTipoLancamento(tipoLancamento)).thenReturn(lancamentosEntity);

        List<LancamentoModel> listarLancamentos = lancamentoService.listarLancamentoPorTipo(tipoLancamento);

        verify(lancamentoRepositoryMock, times(1)).findByTipoLancamento(tipoLancamento);
        Assertions.assertEquals(lancamentosModel, listarLancamentos);
    }
}