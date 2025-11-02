package br.unitins.topicos1.teclado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.teclado.dto.CategoriaDTO;
import br.unitins.topicos1.teclado.dto.CategoriaDTOResponse;
import br.unitins.topicos1.teclado.service.CategoriaService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class CategoriaResourceTest {

    @Inject
    CategoriaService categoriaService; 

    @Test
    public void buscarTodosTest() {
        given()
          .when()
            .get("/categorias")
          .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(3));
    }

    @Test
    public void buscarPorNomeTest() {
        given()
          .when()
            .get("/categorias/search/nome/Gamer") 
          .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("Gamer"));
    }

    @Test
    public void incluirTest() {
        CategoriaDTO dto = new CategoriaDTO(
            "Categoria Teste", 
            "Descrição da Categoria Teste"
        );

        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when()
            .post("/categorias")
          .then()
            .statusCode(201) 
            .body(
                "id", notNullValue(),
                "nome", is("Categoria Teste"),
                "descricao", is("Descrição da Categoria Teste")
            );
    }

    @Test
    public void alterarTest() {

        CategoriaDTO dtoOriginal = new CategoriaDTO("Categoria Original", "Desc Original");
        CategoriaDTOResponse categoriaCriada = categoriaService.create(dtoOriginal);
        Long id = categoriaCriada.id();

        CategoriaDTO dtoUpdate = new CategoriaDTO("Categoria Alterada", "Desc Alterada"); 
        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/categorias/" + id) 
          .then()
            .statusCode(204);
        CategoriaDTOResponse categoriaAtualizada = categoriaService.findById(id);
        assertNotNull(categoriaAtualizada);
        assertEquals(dtoUpdate.nome(), categoriaAtualizada.nome());
        assertEquals(dtoUpdate.descricao(), categoriaAtualizada.descricao());
    }

    @Test
    public void apagarTest() {
        CategoriaDTO dto = new CategoriaDTO("Categoria Para Apagar", "Desc Apagar");
        CategoriaDTOResponse categoriaCriada = categoriaService.create(dto);
        Long id = categoriaCriada.id();
        given()
          .when()
            .delete("/categorias/" + id)
          .then()
            .statusCode(204);
        CategoriaDTOResponse categoriaApagada = categoriaService.findById(id);
        assertNull(categoriaApagada);
    }
}