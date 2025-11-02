package br.unitins.topicos1.teclado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.teclado.dto.EstoqueDTO;
import br.unitins.topicos1.teclado.dto.EstoqueDTOResponse;
import br.unitins.topicos1.teclado.service.EstoqueService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class EstoqueResourceTest {

    @Inject
    EstoqueService estoqueService; 

    @Test
    public void buscarPorIdTest() {
        given()
          .when()
            .get("/estoques/1")
          .then()
            .statusCode(200)
            .body("id", is(1))
            .body("quantidade", is(100));
    }

    @Test
    public void incluirTest() {
        EstoqueDTO dto = new EstoqueDTO(
            25, 
            null 
        );
        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when()
            .post("/estoques")
          .then()
            .statusCode(201) 
            .body(
                "id", notNullValue(),
                "quantidade", is(25),
                "dataAtualizacao", notNullValue() 
            );
    }

    @Test
    public void alterarTest() {
        EstoqueDTO dtoOriginal = new EstoqueDTO(50, null);
        EstoqueDTOResponse estoqueCriado = estoqueService.createForTeclado(dtoOriginal);
        Long id = estoqueCriado.id();

        EstoqueDTO dtoUpdate = new EstoqueDTO(150, null); 

        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/estoques/" + id) 
          .then()
            .statusCode(204);
        EstoqueDTOResponse estoqueAtualizado = estoqueService.findById(id);
        assertNotNull(estoqueAtualizado);
        assertEquals(dtoUpdate.quantidade(), estoqueAtualizado.quantidade());
    }

    @Test
    public void apagarTest() {
        EstoqueDTO dto = new EstoqueDTO(10, null);
        EstoqueDTOResponse estoqueCriado = estoqueService.createForTeclado(dto);
        Long id = estoqueCriado.id();
        given()
          .when()
            .delete("/estoques/" + id)
          .then()
            .statusCode(204);
        EstoqueDTOResponse estoqueApagado = estoqueService.findById(id);
        assertNull(estoqueApagado);
    }
}