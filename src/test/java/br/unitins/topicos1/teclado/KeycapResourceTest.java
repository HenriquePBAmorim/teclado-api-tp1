package br.unitins.topicos1.teclado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.teclado.dto.KeycapDTO;
import br.unitins.topicos1.teclado.dto.KeycapDTOResponse;
import br.unitins.topicos1.teclado.service.KeycapService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class KeycapResourceTest {

    @Inject
    KeycapService keycapService; 

    @Test
    public void buscarTodosTest() {
        given()
          .when()
            .get("/keycaps")
          .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(2)); 
    }

    @Test
    public void buscarPorNomeTest() {
        given()
          .when()
            .get("/keycaps/search/nome/Keycap ABS Padrão") 
          .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("Keycap ABS Padrão"));
    }

    @Test
    public void incluirTest() {
        KeycapDTO dto = new KeycapDTO(
            "Keycap Teste DSA", 
            "PBT",
            3, 
            "Rosa"
        );

        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when()
            .post("/keycaps")
          .then()
            .statusCode(201) 
            .body(
                "id", notNullValue(),
                "nome", is("Keycap Teste DSA"),
                "material", is("PBT"),
                "perfil.id", is(3), 
                "perfil.label", is("DSA"),
                "cor", is("Rosa")
            );
    }

    @Test
    public void alterarTest() {
        KeycapDTO dtoOriginal = new KeycapDTO("Keycap Original", "ABS", 1, "Preto");
        KeycapDTOResponse keycapCriada = keycapService.create(dtoOriginal);
        Long id = keycapCriada.id();

        KeycapDTO dtoUpdate = new KeycapDTO("Keycap Alterada", "PBT", 2, "Branco"); 

        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/keycaps/" + id) 
          .then()
            .statusCode(204);
        KeycapDTOResponse keycapAtualizada = keycapService.findById(id);
        assertNotNull(keycapAtualizada);
        assertEquals(dtoUpdate.nome(), keycapAtualizada.nome());
        assertEquals(dtoUpdate.material(), keycapAtualizada.material());
        assertEquals(dtoUpdate.idPerfilKeycap(), keycapAtualizada.perfil().ID);
    }

   @Test
    public void apagarTest() {
        KeycapDTO dto = new KeycapDTO("Keycap Para Apagar", "ABS", 1, "Azul");
        KeycapDTOResponse keycapCriada = keycapService.create(dto);
        Long id = keycapCriada.id();
        given()
          .when()
            .delete("/keycaps/" + id)
          .then()
            .statusCode(204);
        KeycapDTOResponse keycapApagada = keycapService.findById(id);
        assertNull(keycapApagada);
    }
}
