package br.unitins.topicos1.teclado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.teclado.dto.MarcaDTO;
import br.unitins.topicos1.teclado.dto.MarcaDTOResponse;
import br.unitins.topicos1.teclado.service.MarcaService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class MarcaResourceTest {

    @Inject
    MarcaService marcaService; 

    @Test
    public void buscarTodosTest() {
        
        given()
          .when()
            .get("/marcas")
          .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(4)); 
    }

    @Test
    public void buscarPorNomeTest() {
        given()
          .when()
            .get("/marcas/find/Logitech")
          .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("Logitech"));
    }

    @Test
    public void incluirTest() {
        MarcaDTO dto = new MarcaDTO(
            "Razer (Teste)", 
            "Descrição da Razer (Teste)"
        );

        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when()
            .post("/marcas")
          .then()
            .statusCode(201) 
            .body(
                "id", notNullValue(),
                "nome", is("Razer (Teste)"),
                "descricao", is("Descrição da Razer (Teste)")
            );
    }

    @Test
    public void alterarTest() {
        MarcaDTO dtoOriginal = new MarcaDTO("Marca Original", "Desc Original");
        MarcaDTOResponse marcaCriada = marcaService.create(dtoOriginal);
        Long id = marcaCriada.id();

        MarcaDTO dtoUpdate = new MarcaDTO("Marca Alterada", "Desc Alterada");

        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/marcas/" + id) 
          .then()
            .statusCode(204); 

        MarcaDTOResponse marcaAtualizada = marcaService.findById(id);
        assertEquals(dtoUpdate.nome(), marcaAtualizada.nome());
        assertEquals(dtoUpdate.descricao(), marcaAtualizada.descricao());
    }

    @Test
    public void apagarTest() {

        MarcaDTO dto = new MarcaDTO("Marca Para Apagar", "Desc Apagar");
        MarcaDTOResponse marcaCriada = marcaService.create(dto);
        Long id = marcaCriada.id();

        given()
          .when()
            .delete("/marcas/" + id)
          .then()
            .statusCode(204); 
            
        MarcaDTOResponse marcaApagada = marcaService.findById(id);
        assertNull(marcaApagada);
    }
}