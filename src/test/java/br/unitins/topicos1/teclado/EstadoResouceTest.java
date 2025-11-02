package br.unitins.topicos1.teclado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import br.unitins.topicos1.teclado.dto.EstadoDTO;
import br.unitins.topicos1.teclado.dto.EstadoDTOResponse;
import br.unitins.topicos1.teclado.service.EstadoService;
import io.quarkus.test.junit.QuarkusTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
class EstadoResourceTest {

    @Inject
    EstadoService estadoService;

    @Test
    void buscarTodosTest() {
        RestAssured.given()
                .when()
                    .get("/estados")
                .then()
                    .statusCode(200);
    }

    @Test
    void incluirTest() {
        EstadoDTO dto = new EstadoDTO("Teste", "TT", 1l);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                    .post("/estados")
                .then()
                    .statusCode(201)
                    .body("id", CoreMatchers.notNullValue(),
                            "nome", CoreMatchers.is("Teste"),
                            "sigla", CoreMatchers.is("TT"),
                            "regiao.id", CoreMatchers.is(1),
                            "regiao.nome", CoreMatchers.is("Centro-Oeste"));
    }

    @Test
    void alterarTest() {
        EstadoDTO dto = new EstadoDTO("Teste2", "T2", 1l);

        EstadoDTOResponse response = estadoService.create(dto);

        EstadoDTO dtoUpdate = new EstadoDTO("Teste33", "T3", 2l);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dtoUpdate)
                .when()
                    .put("/estados/"+response.id())
                .then()
                    .statusCode(204);

        response = estadoService.findById(response.id());
        assertEquals(dtoUpdate.nome(), response.nome());
        assertEquals(dtoUpdate.sigla(), response.sigla());
        assertEquals(dtoUpdate.idRegiao(), response.regiao().ID);
    }

    @Test
    void apagarTest() {
        EstadoDTO dto = new EstadoDTO("Teste44", "T4", 1l);

        EstadoDTOResponse response = estadoService.create(dto);

        RestAssured.given()
                .when()
                    .delete("/estados/"+response.id())
                .then()
                    .statusCode(204);

        response = estadoService.findById(response.id());

        assertNull(response);
        
    }

}