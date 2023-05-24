package api.service;

import api.ApplicationTests;
import api.entity.LancamentoEntity;
import api.enums.TipoLancamento;
import api.exception.ModelException;
import api.model.SaldoDiarioModel;
import api.repository.LancamentoRepository;
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

import static org.mockito.Mockito.when;

public class SaldoServiceTest extends ApplicationTests {

    @Mock
    private LancamentoRepository lancamentoRepository;

    private LocalDate data;

    @InjectMocks
    private SaldoService saldoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        data = LocalDate.of(2021, 1, 1);
    }

    @Test
    public void testCalcularSaldo() {
        List<LancamentoEntity> lancamentos = new ArrayList<>();
        lancamentos.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("100.00"), data));
        lancamentos.add(new LancamentoEntity(TipoLancamento.DEBITO, new BigDecimal("50.00"), data));
        lancamentos.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("200.00"), data));

        BigDecimal saldo = saldoService.calcularSaldo(lancamentos, "CREDITO");

        Assertions.assertEquals(new BigDecimal("300.00"), saldo);
    }

    @Test
    public void testMostrarSaldoPorData() {
        List<LancamentoEntity> lancamentos = new ArrayList<>();
        lancamentos.add(new LancamentoEntity(TipoLancamento.DEBITO, new BigDecimal("20.00"), data));
        lancamentos.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("50.00"), data));
        lancamentos.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("100.00"), data));

        when(lancamentoRepository.findByDataLancamento(data)).thenReturn(lancamentos);

        SaldoDiarioModel saldoDiario = saldoService.mostrarSaldo(data);

        Assertions.assertEquals(data, saldoDiario.getData());
        Assertions.assertEquals(new BigDecimal("150.00"), saldoDiario.getSaldoCreditos());
        Assertions.assertEquals(new BigDecimal("20.00"), saldoDiario.getSaldoDebitos());
        Assertions.assertEquals(new BigDecimal("130.00"), saldoDiario.getSaldoTotal());
    }

    @Test
    public void testMostrarSaldoSemData() {
        List<LancamentoEntity> lancamentos = new ArrayList<>();
        lancamentos.add(new LancamentoEntity(TipoLancamento.DEBITO, new BigDecimal("20.00"), null));
        lancamentos.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("50.00"), null));
        lancamentos.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("100.00"), null));
        lancamentos.add(new LancamentoEntity(TipoLancamento.CREDITO, new BigDecimal("100.00"), null));

        when(lancamentoRepository.findAll()).thenReturn(lancamentos);

        SaldoDiarioModel saldoDiario = saldoService.mostrarSaldo(null);

        Assertions.assertEquals(null, saldoDiario.getData());
        Assertions.assertEquals(new BigDecimal("250.00"), saldoDiario.getSaldoCreditos());
        Assertions.assertEquals(new BigDecimal("20.00"), saldoDiario.getSaldoDebitos());
        Assertions.assertEquals(new BigDecimal("230.00"), saldoDiario.getSaldoTotal());
    }

    @Test
    public void testMostrarSaldoSemDados() {
        when(lancamentoRepository.findByDataLancamento(data)).thenReturn(new ArrayList<>());

        Assertions.assertThrows(ModelException.class, () -> {
            saldoService.mostrarSaldo(data);
        });
    }

}