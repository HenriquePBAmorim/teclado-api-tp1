package br.unitins.topicos1.teclado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.teclado.dto.AcessorioDTO;
import br.unitins.topicos1.teclado.dto.AcessorioDTOResponse;
import br.unitins.topicos1.teclado.service.AcessorioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class AcessorioResourceTest {

    @Inject
    AcessorioService acessorioService; 

    @Test
    public void buscarTodosTest() {
        given()
          .when()
            .get("/acessorios")
          .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(2)); 
    }

    @Test
    public void buscarPorNomeTest() {
        given()
          .when()
            .get("/acessorios/search/nome/Cabo USB-C Enrolado") 
          .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("Cabo USB-C Enrolado"));
    }

    @Test
    public void incluirTest() {

        AcessorioDTO dto = new AcessorioDTO(
            "Kit Limpeza Teste", 
            75.00,
            "Universal",
            6, 
            "Plástico/Pano",
            "Azul"
        );

        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when()
            .post("/acessorios")
          .then()
            .statusCode(201) 
            .body(
                "id", notNullValue(),
                "nome", is("Kit Limpeza Teste"),
                "preco", is(75.00f),
                "tipo.id", is(6), 
                "tipo.label", is("Kit de Limpeza")
            );
    }

    @Test
    public void alterarTest() {
        AcessorioDTO dtoOriginal = new AcessorioDTO("Suporte Original", 50.0, "Qualquer", 1, "Metal", "Preto");
        AcessorioDTOResponse acessorioCriado = acessorioService.create(dtoOriginal);
        Long id = acessorioCriado.id();
        AcessorioDTO dtoUpdate = new AcessorioDTO("Suporte Alterado", 60.0, "Alterado", 2, "Plástico", "Branco"); 
        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/acessorios/" + id)
          .then()
            .statusCode(204);
        AcessorioDTOResponse acessorioAtualizado = acessorioService.findById(id);
        assertNotNull(acessorioAtualizado);
        assertEquals(dtoUpdate.nome(), acessorioAtualizado.nome());
        assertEquals(dtoUpdate.preco(), acessorioAtualizado.preco());
        assertEquals(dtoUpdate.idTipoAcessorio(), acessorioAtualizado.tipo().ID);
    }

    @Test
    public void apagarTest() {
        AcessorioDTO dto = new AcessorioDTO("Acessorio Para Apagar", 10.0, "N/A", 1, "N/A", "N/A");
        AcessorioDTOResponse acessorioCriado = acessorioService.create(dto);
        Long id = acessorioCriado.id();
        given()
          .when()
            .delete("/acessorios/" + id)
          .then()
            .statusCode(204);
        AcessorioDTOResponse acessorioApagado = acessorioService.findById(id);
        assertNull(acessorioApagado);
    }
}