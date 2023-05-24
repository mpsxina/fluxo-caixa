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
public class LancamentoControllerTest extends ApplicationTests {

    private static final String URL_API = "/lancamentos";

    private MockMvc mockMvc;

    @Autowired
    private ProdutoController produtoController;

    @Autowired
    private LancamentoController lancamentoController;

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
    }

    @AfterAll
    public void tearDown() throws Exception {
        this.testDELETELancamento();
    }

    @Test
    @DisplayName("Cria o Lançamento")
    public void testPOSTLancamento() throws Exception {
        String lancamento = "{\"idProduto\": \"1\", \"tipoLancamento\": \"CREDITO\", \"valor\": \"12\", \"dataLancamento\": \"2023-05-23\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(lancamento)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Pega todos os Lançamentos")
    public void testGETLancamentos() throws Exception {
        String lancamento = "{\"idProduto\": \"1\", \"tipoLancamento\": \"CREDITO\", \"valor\": \"12\", \"dataLancamento\": \"2023-05-23\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(lancamento)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL_API))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Pega o Lançamento por DataLancamento")
    public void testGETNumberLancamento() throws Exception {
        String lancamento = "{\"idProduto\": \"1\", \"tipoLancamento\": \"CREDITO\", \"valor\": \"12\", \"dataLancamento\": \"2023-05-23\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(lancamento)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL_API+"/data/{data}", "2023-05-23"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Pega o Lançamento por ID")
    public void testGETIdLancamento() throws Exception {
        String lancamento = "{\"idProduto\": \"1\", \"tipoLancamento\": \"CREDITO\", \"valor\": \"12\", \"dataLancamento\": \"2023-05-23\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(lancamento)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL_API+"/{id}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Atualiza o Lançamento por ID")
    public void testPUTLancamento() throws Exception {
        String lancamento = "{\"idProduto\": \"1\", \"tipoLancamento\": \"DEBITO\", \"valor\": \"12\", \"dataLancamento\": \"2023-05-23\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(lancamento)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String updated = "{\"idProduto\": \"1\", \"tipoLancamento\": \"CREDITO\", \"valor\": \"15\", \"dataLancamento\": \"2023-05-23\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.put(URL_API+"/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updated)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("Exclui o Lançamento por ID")
    public void testDELETELancamento() throws Exception {
        String lancamento = "{\"idProduto\": \"1\", \"tipoLancamento\": \"CREDITO\", \"valor\": \"15\", \"dataLancamento\": \"2023-05-23\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(lancamento)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders.delete(URL_API+"/{id}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}