package br.unitins.topicos1.teclado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static io
.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.teclado.dto.MunicipioDTO;
import br.unitins.topicos1.teclado.dto.MunicipioDTOResponse;
import br.unitins.topicos1.teclado.service.MunicipioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class MunicipioResourceTest {

    @Inject
    MunicipioService municipioService;

    @Test
    public void buscarTodosTest() {
        given()
          .when()
            .get("/municipios")
          .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(5)); 
    }

    @Test
    public void buscarPorNomeTest() {
        given()
          .when()
            .get("/municipios/find/Palmas")
          .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("Palmas"));
    }

    @Test
    public void incluirTest() {
        MunicipioDTO dto = new MunicipioDTO(
            "Paraíso do Tocantins", 
            1L 
        );

        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when()
            .post("/municipios")
          .then()
            .statusCode(201) 
            .body(
                "id", notNullValue(),
                "nome", is("Paraíso do Tocantins"),
                "estado.id", is(1), 
                "estado.sigla", is("TO")
            );
    }

    @Test
    public void alterarTest() {
        MunicipioDTO dtoOriginal = new MunicipioDTO("Município Original", 1L);
        MunicipioDTOResponse municipioCriado = municipioService.create(dtoOriginal);
        Long id = municipioCriado.id();

        MunicipioDTO dtoUpdate = new MunicipioDTO("Município Alterado", 2L); 

        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/municipios/" + id) 
          .then()
            .statusCode(204); 
        MunicipioDTOResponse municipioAtualizado = municipioService.findById(id);
        assertNotNull(municipioAtualizado); 
        assertEquals(dtoUpdate.nome(), municipioAtualizado.nome());
        assertEquals(dtoUpdate.idEstado(), municipioAtualizado.estado().id());
    }

    @Test
    public void apagarTest() {
        MunicipioDTO dto = new MunicipioDTO("Município Para Apagar", 1L);
        MunicipioDTOResponse municipioCriado = municipioService.create(dto);
        Long id = municipioCriado.id();

        given()
          .when()
            .delete("/municipios/" + id)
          .then()
            .statusCode(204);
            
        MunicipioDTOResponse municipioApagado = municipioService.findById(id);
        assertNull(municipioApagado);
    }
}