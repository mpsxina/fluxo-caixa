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
public class ProdutoControllerTest extends ApplicationTests {

    private static final String URL_API = "/produtos";

    private MockMvc mockMvc;

    @Autowired
    private ProdutoController produtoController;

    @BeforeEach
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }

    @Test
    @DisplayName("Cria o Produto")
    public void testPOSTProduto() throws Exception {
        String produto = "{\"nome\": \"Produto 1\", \"descricao\": \"Descrição do Produto 1\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produto)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @DisplayName("Pega todos os Produtos")
    public void testGETProdutos() throws Exception {
        String produto = "{\"nome\": \"Produto 1\", \"descricao\": \"Descrição do Produto 1\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produto)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL_API))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Pega o Produto por ID")
    public void testGETIdProduto() throws Exception {
        String produto = "{\"nome\": \"Produto 1\", \"descricao\": \"Descrição do Produto 1\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produto)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        this.mockMvc.perform(MockMvcRequestBuilders.get(URL_API+"/{id}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Atualiza o Produto por ID")
    public void testPUTProduto() throws Exception {
        String produto = "{\"nome\": \"Produto 1\", \"descricao\": \"Descrição do Produto 1\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post(URL_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produto)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        String produto2 = "{\"nome\": \"Produto 2\", \"descricao\": \"Descrição do Produto 2\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.put(URL_API+"/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produto2)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}