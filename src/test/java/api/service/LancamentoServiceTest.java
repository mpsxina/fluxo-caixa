package api.service;

import api.ApplicationTests;
import api.entity.LancamentoEntity;
import api.enums.TipoLancamento;
import api.model.LancamentoModel;
import api.repository.LancamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class LancamentoServiceTest extends ApplicationTests {

    @Autowired
    private LancamentoService lancamentoService;

    @MockBean
    private LancamentoRepository lancamentoRepository;

    private ModelMapper mapper = new ModelMapper();

    @Test
    @DisplayName("Cria o Lançamento com sucesso")
    void testCriarLancamento() {
        LancamentoModel mockLancamentoModel = LancamentoModel.builder()
                .tipoLancamento(TipoLancamento.CREDITO)
                .produto("xxxxxxxxx")
                .valor(BigDecimal.valueOf(8.60))
                .data(LocalDate.now())
                .build();

        LancamentoEntity lancamentoEntity = mapper.map(mockLancamentoModel, LancamentoEntity.class);

        when(lancamentoRepository.save(any(LancamentoEntity.class))).thenReturn(lancamentoEntity);

        LancamentoModel saveLancamentoModel = lancamentoService.criarLancamento(mockLancamentoModel);
        LancamentoModel lancamentoModel = mapper.map(saveLancamentoModel, LancamentoModel.class);

        Assertions.assertNotNull(lancamentoEntity);
        assertEquals(mockLancamentoModel, lancamentoModel);
    }

    @Test
    @DisplayName("Lista todos os Lançamentos por Tipo de Crédito")
    void testListarPorTipo() {
        List<LancamentoEntity> mockListLancamentosEntities = Stream.of(
                        LancamentoEntity.builder()
                                .id(1L)
                                .tipoLancamento(TipoLancamento.CREDITO)
                                .produto("aaaaaaaaaaa")
                                .valor(BigDecimal.valueOf(10.50))
                                .build(),
                        LancamentoEntity.builder()
                                .id(2L)
                                .tipoLancamento(TipoLancamento.DEBITO)
                                .produto("bbbbbbbbbbb")
                                .valor(BigDecimal.valueOf(7.55))
                                .build())
                .collect(Collectors.toList());

        when(lancamentoRepository.findByTipoLancamento(TipoLancamento.CREDITO))
                .thenReturn(mockListLancamentosEntities);

        List<LancamentoModel> lancamentos = lancamentoService.listarLancamentoPorTipo(TipoLancamento.CREDITO);
        List<LancamentoEntity> listaLancamentos = lancamentos.stream().map(entity -> mapper.map(entity, LancamentoEntity.class)).collect(Collectors.toList());

        Assertions.assertNotNull(listaLancamentos);
        assertEquals(mockListLancamentosEntities, listaLancamentos);
    }

    @Test
    @DisplayName("Lista todos os Lançamentos por Tipo inválido")
    void testListarPorTipoInvalido() {
        List<LancamentoEntity> mockListLancamentosEntities = Stream.of(
                        LancamentoEntity.builder()
                                .id(1L)
                                .tipoLancamento(TipoLancamento.CREDITO)
                                .produto("aaaaaaaaaaa")
                                .valor(BigDecimal.valueOf(2.00))
                                .build(),
                        LancamentoEntity.builder()
                                .id(2L)
                                .tipoLancamento(TipoLancamento.CREDITO)
                                .produto("bbbbbbbbbbb")
                                .valor(BigDecimal.valueOf(5.00))
                                .build())
                .collect(Collectors.toList());

        when(lancamentoRepository.findByTipoLancamento(TipoLancamento.CREDITO))
                .thenReturn(mockListLancamentosEntities);

        try {
            List<LancamentoModel> lancamentos = lancamentoService.listarLancamentoPorTipo(TipoLancamento.DEBITO);
            List<LancamentoEntity> listaLancamentos = lancamentos.stream().map(entity -> mapper.map(entity, LancamentoEntity.class)).collect(Collectors.toList());
            assertNotEquals(mockListLancamentosEntities, listaLancamentos);
        } catch (Exception e) {
            assertEquals("Nenhum lançamento encontrado.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Lista o Lançamento por ID com sucesso")
    void testListarPorId() {
        LancamentoEntity mockLancamentoEntity = LancamentoEntity.builder()
                .id(1L)
                .tipoLancamento(TipoLancamento.CREDITO)
                .produto("wwwwwwwww")
                .valor(BigDecimal.valueOf(9,90))
                .build();

        when(lancamentoRepository.findById(1L)).thenReturn(Optional.of(mockLancamentoEntity));

        LancamentoModel findLancamento = lancamentoService.listarLacamentoPorId(1L);
        LancamentoEntity lancamentoEntity = mapper.map(findLancamento, LancamentoEntity.class);

        Assertions.assertNotNull(lancamentoEntity);
        assertEquals(mockLancamentoEntity, lancamentoEntity);
    }

    @Test
    @DisplayName("Lista o Lançamento por ID inválido")
    void testListarPorIdInvalido() {
        LancamentoEntity mockLancamentoEntity = LancamentoEntity.builder()
                .id(1L)
                .tipoLancamento(TipoLancamento.CREDITO)
                .produto("wwwwwwwww")
                .valor(BigDecimal.valueOf(9,90))
                .build();

        when(lancamentoRepository.findById(1L)).thenReturn(Optional.of(mockLancamentoEntity));

        try {
            lancamentoService.listarLacamentoPorId(2L);
        } catch (Exception e) {
            assertEquals("Nenhum lançamento encontrado.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Lista o Lançamento por Data com sucesso")
    void testListarLancamentoPorData() {
        LancamentoEntity mockLancamentoEntity = LancamentoEntity.builder()
                .id(1L)
                .tipoLancamento(TipoLancamento.CREDITO)
                .produto("zzzzzzzzzzzz")
                .valor(BigDecimal.valueOf(9,90))
                .data(LocalDate.now())
                .build();

        when(lancamentoRepository.findByData(LocalDate.now())).thenReturn(List.of(mockLancamentoEntity));

        List<LancamentoModel> findLancamento = lancamentoService.listarLancamentoPorData(LocalDate.now());

        Assertions.assertNotNull(findLancamento);
        assertEquals(mockLancamentoEntity.getData(), findLancamento);
    }

    @Test
    @DisplayName("Lista o Lançamento por Data inválida")
    void testListarLancamentoPorDataInvalido() {
        LancamentoEntity mockLancamentoEntity = LancamentoEntity.builder()
                .id(1L)
                .tipoLancamento(TipoLancamento.CREDITO)
                .produto("zzzzzzzzzzzz")
                .valor(BigDecimal.valueOf(9,90))
                .data(LocalDate.now())
                .build();

        when(lancamentoRepository.findByData(LocalDate.now())).thenReturn(List.of(mockLancamentoEntity));

        try {
            lancamentoService.listarLancamentoPorData(LocalDate.of(2023, 05, 20));
        } catch (Exception e) {
            assertEquals("", e.getMessage());
        }
    }

    @Test
    @DisplayName("Altera o Lançamento por ID com sucesso")
    void testAlterarLancamentoPorId() {
        LancamentoEntity mockLancamentoEntity = LancamentoEntity.builder()
                .tipoLancamento(TipoLancamento.CREDITO)
                .produto("yyyyyyyyyy")
                .valor(BigDecimal.valueOf(8.50))
                .data(LocalDate.now())
                .build();

        when(lancamentoRepository.existsById(1L)).thenReturn(true);

        mockLancamentoEntity.setValor(BigDecimal.valueOf(11.20));
        when(lancamentoRepository.save(any(LancamentoEntity.class))).thenReturn(mockLancamentoEntity);

        LancamentoModel updateLancamentoModel = lancamentoService.alterarLancamento(1L, mockLancamentoEntity);
        LancamentoEntity lancamentoEntity = mapper.map(updateLancamentoModel, LancamentoEntity.class);
        lancamentoEntity.setValor(mockLancamentoEntity.getValor());

        Assertions.assertNotNull(lancamentoEntity);
        assertEquals(mockLancamentoEntity, lancamentoEntity);
    }

    @Test
    @DisplayName("Altera o Lançamento por ID inválido")
    void testAlterarLancamentoPorIdInvalido() {
        LancamentoEntity mockLancamentoEntity = LancamentoEntity.builder()
                .tipoLancamento(TipoLancamento.CREDITO)
                .produto("yyyyyyyyyy")
                .valor(BigDecimal.valueOf(8.50))
                .data(LocalDate.now())
                .build();

        when(lancamentoRepository.existsById(1L)).thenReturn(true);

        mockLancamentoEntity.setValor(BigDecimal.valueOf(15.25));
        when(lancamentoRepository.save(any(LancamentoEntity.class))).thenReturn(mockLancamentoEntity);

        try {
            lancamentoService.alterarLancamento(2L, mockLancamentoEntity);
        } catch (Exception e) {
            assertEquals("Nenhum lançamento encontrado.", e.getMessage());
        }
    }

    @Test
    @DisplayName("Exclui o Lançamento por ID com sucesso")
    void testExcluirLancamentoPorId() {
        LancamentoEntity mockLancamentoEntity = LancamentoEntity.builder()
                .tipoLancamento(TipoLancamento.DEBITO)
                .produto("dddddddddddddd")
                .valor(BigDecimal.valueOf(8.50))
                .data(LocalDate.now())
                .build();

        when(lancamentoRepository.existsById(1L)).thenReturn(true);

        String deleteLancamento = lancamentoService.excluirLancamento(1L);

        Assertions.assertNotNull(deleteLancamento);
        assertEquals("Lançamento excluído com sucesso.", deleteLancamento);
    }

    @Test
    @DisplayName("Exclui o Lançamento por ID inválido")
    void testExcluirLancamentoPorIdInvalido() {
        LancamentoEntity mockLancamentoEntity = LancamentoEntity.builder()
                .tipoLancamento(TipoLancamento.DEBITO)
                .produto("dddddddddddddd")
                .valor(BigDecimal.valueOf(8.50))
                .data(LocalDate.now())
                .build();

        when(lancamentoRepository.existsById(1L)).thenReturn(true);

        try {
            lancamentoService.excluirLancamento(2L);
        } catch (Exception e) {
            assertEquals("Nenhum lançamento encontrado.", e.getMessage());
        }
    }

}
