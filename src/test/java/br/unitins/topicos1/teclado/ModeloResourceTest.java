package br.unitins.topicos1.teclado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.teclado.dto.ModeloDTO;
import br.unitins.topicos1.teclado.dto.ModeloDTOResponse;
import br.unitins.topicos1.teclado.service.ModeloService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class ModeloResourceTest {

    @Inject
    ModeloService modeloService;

    @Test
    public void buscarTodosTest() {
        // O import.sql insere 4 modelos
        given()
          .when()
            .get("/modelos")
          .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(4)); // Verifica os dados do import
    }

    @Test
    public void buscarPorNomeTest() {
        // O import.sql insere "G Pro Series"
        given()
          .when()
            .get("/modelos/search/nome/G Pro Series") // Usando o path do Resource
          .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("G Pro Series"));
    }

    @Test
    public void incluirTest() {
        // Teste de POST (Black box)
        // 1L = Marca 'Logitech' (do import.sql)
        ModeloDTO dto = new ModeloDTO(
            "Modelo de Teste", 
            1L 
        );

        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when()
            .post("/modelos")
          .then()
            .statusCode(201) // 201 Created
            .body(
                "id", notNullValue(),
                "nome", is("Modelo de Teste"),
                "marca.id", is(1), // Verifica o objeto aninhado
                "marca.nome", is("Logitech")
            );
    }

    @Test
    public void alterarTest() {
        // 1. Setup (Gray Box) - Criando um modelo
        // 1L = Logitech
        ModeloDTO dtoOriginal = new ModeloDTO("Modelo Original", 1L);
        ModeloDTOResponse modeloCriado = modeloService.create(dtoOriginal);
        Long id = modeloCriado.id();

        // 2. Teste (API)
        // 2L = Razer
        ModeloDTO dtoUpdate = new ModeloDTO("Modelo Alterado", 2L); 

        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/modelos/" + id) // Usando o ID criado
          .then()
            .statusCode(204); // 204 No Content

        // 3. Verificação (Gray Box) - Verificando no banco
        ModeloDTOResponse modeloAtualizado = modeloService.findById(id);
        assertNotNull(modeloAtualizado);
        assertEquals(dtoUpdate.nome(), modeloAtualizado.nome());
        assertEquals(dtoUpdate.idMarca(), modeloAtualizado.marca().id());
    }

    @Test
    public void apagarTest() {
        // 1. Setup (Gray Box)
        ModeloDTO dto = new ModeloDTO("Modelo Para Apagar", 1L);
        ModeloDTOResponse modeloCriado = modeloService.create(dto);
        Long id = modeloCriado.id();

        // 2. Teste (API)
        given()
          .when()
            .delete("/modelos/" + id)
          .then()
            .statusCode(204); // 204 No Content

        // 3. Verificação (Gray Box)
        // (Usando o padrão do professor, que espera 'null' do findById)
        ModeloDTOResponse modeloApagado = modeloService.findById(id);
        assertNull(modeloApagado);
    }
}