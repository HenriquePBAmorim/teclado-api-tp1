package br.unitins.topicos1.teclado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.teclado.dto.SwitchDTO;
import br.unitins.topicos1.teclado.dto.SwitchDTOResponse;
import br.unitins.topicos1.teclado.service.SwitchService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class SwitchResourceTest {

    @Inject
    SwitchService switchService; 

    @Test
    public void buscarTodosTest() {
        given()
          .when()
            .get("/switches")
          .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(4));
    }

    @Test
    public void buscarPorNomeTest() {
        given()
          .when()
            .get("/switches/search/nome/Cherry MX Red") 
          .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("Cherry MX Red"));
    }

    @Test
    public void incluirTest() {
        SwitchDTO dto = new SwitchDTO(
            "Gateron Yellow", 
            "Gateron",
            1, 
            50.0
        );

        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when()
            .post("/switches")
          .then()
            .statusCode(201) 
            .body(
                "id", notNullValue(),
                "nome", is("Gateron Yellow"),
                "fabricante", is("Gateron"),
                "tipo.id", is(1), 
                "tipo.label", is("Linear"),
                "forcaAtuacao", is(50.0f) 
            );
    }

    @Test
    public void alterarTest() {
        SwitchDTO dtoOriginal = new SwitchDTO("Switch Original", "Original", 1, 50.0);
        SwitchDTOResponse switchCriado = switchService.create(dtoOriginal);
        Long id = switchCriado.id();

        SwitchDTO dtoUpdate = new SwitchDTO("Switch Alterado", "Alterado", 2, 60.0); 

        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/switches/" + id) 
          .then()
            .statusCode(204);
        SwitchDTOResponse switchAtualizado = switchService.findById(id);
        assertNotNull(switchAtualizado);
        assertEquals(dtoUpdate.nome(), switchAtualizado.nome());
        assertEquals(dtoUpdate.fabricante(), switchAtualizado.fabricante());
        assertEquals(dtoUpdate.idTipoSwitch(), switchAtualizado.tipo().ID);
    }

    @Test
    public void apagarTest() {
        SwitchDTO dto = new SwitchDTO("Switch Para Apagar", "Apagar", 1, 50.0);
        SwitchDTOResponse switchCriado = switchService.create(dto);
        Long id = switchCriado.id();
        given()
          .when()
            .delete("/switches/" + id)
          .then()
            .statusCode(204);
        SwitchDTOResponse switchApagado = switchService.findById(id);
        assertNull(switchApagado);
    }
}