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
        given()
          .when()
            .get("/modelos")
          .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(4)); 
    }

    @Test
    public void buscarPorNomeTest() {
        given()
          .when()
            .get("/modelos/search/nome/G Pro Series") 
          .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("G Pro Series"));
    }

    @Test
    public void incluirTest() {
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
            .statusCode(201) 
            .body(
                "id", notNullValue(),
                "nome", is("Modelo de Teste"),
                "marca.id", is(1), 
                "marca.nome", is("Logitech")
            );
    }

    @Test
    public void alterarTest() {
        ModeloDTO dtoOriginal = new ModeloDTO("Modelo Original", 1L);
        ModeloDTOResponse modeloCriado = modeloService.create(dtoOriginal);
        Long id = modeloCriado.id();

        ModeloDTO dtoUpdate = new ModeloDTO("Modelo Alterado", 2L); 

        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/modelos/" + id) 
          .then()
            .statusCode(204); 

        ModeloDTOResponse modeloAtualizado = modeloService.findById(id);
        assertNotNull(modeloAtualizado);
        assertEquals(dtoUpdate.nome(), modeloAtualizado.nome());
        assertEquals(dtoUpdate.idMarca(), modeloAtualizado.marca().id());
    }

    @Test
    public void apagarTest() {
        ModeloDTO dto = new ModeloDTO("Modelo Para Apagar", 1L);
        ModeloDTOResponse modeloCriado = modeloService.create(dto);
        Long id = modeloCriado.id();

        given()
          .when()
            .delete("/modelos/" + id)
          .then()
            .statusCode(204); 
        ModeloDTOResponse modeloApagado = modeloService.findById(id);
        assertNull(modeloApagado);
    }
}