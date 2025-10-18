package TDD_CompraEntradas_tp6.demo.controller;

import TDD_CompraEntradas_tp6.demo.controllers.EntradasController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;


@WebMvcTest(EntradasController.class)
public class entradasValidator {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_recibir_detalle_compra_entradas() throws Exception {
        String json = """
    {
      "fecha": "2025-10-20",
      "entradas": [
        { "edad": 34, "tipo": "general" },
        { "edad": 7, "tipo": "menor" }
      ]
    }
    """;

        mockMvc.perform(post("/compras")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(2));
    }
}
