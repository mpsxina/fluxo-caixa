package api.controller;

import api.ApplicationTests;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SaldoControllerTest extends ApplicationTests {

    private static final String URL_API = "/saldo";

    private MockMvc mockMvc;

    @Autowired
    private ProdutoController produtoController;

    @Autowired
    private LancamentoController lancamentoController;

    @Autowired
    private SaldoController saldoController;

    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();

        String produto = "{\"nome\": \"Produto Teste\", \"descricao\": \"Descrição Teste\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produto)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc = MockMvcBuilders.standaloneSetup(lancamentoController).build();

        String lancamento = "{\"idProduto\": \"1\", \"tipoLancamento\": \"CREDITO\", \"valor\": \"55.00\", \"dataLancamento\": \"2023-05-24\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/lancamentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(lancamento)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc = MockMvcBuilders.standaloneSetup(saldoController).build();
    }

    @Test
    @DisplayName("Pega todos os Saldos")
    public void testGETSaldos() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL_API))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Pega o Saldo por Data")
    public void testGETIdSaldo() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(URL_API+"/{data}", "2023-05-24"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}