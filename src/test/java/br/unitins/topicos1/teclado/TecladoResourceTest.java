package br.unitins.topicos1.teclado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos1.teclado.dto.EstoqueDTO;
import br.unitins.topicos1.teclado.dto.EstoqueDTOResponse;
import br.unitins.topicos1.teclado.dto.TecladoDTO;
import br.unitins.topicos1.teclado.dto.TecladoDTOResponse;
import br.unitins.topicos1.teclado.service.EstoqueService;
import br.unitins.topicos1.teclado.service.TecladoService;

import java.util.List;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

@QuarkusTest
public class TecladoResourceTest {

    @Inject
    TecladoService tecladoService; 

    @Inject
    EstoqueService estoqueService;

    @Test
    public void buscarTodosTest() {
        given()
          .when()
            .get("/teclados")
          .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(3)); 
    }

    @Test
    public void buscarPorNomeTest() {
        given()
          .when()
            .get("/teclados/find/G Pro X") 
          .then()
            .statusCode(200)
            .body("size()", is(1))
            .body("[0].nome", is("G Pro X"));
    }

    @Test
    public void incluirTest() {
        EstoqueDTO estoqueDTO = new EstoqueDTO(10, null);
        EstoqueDTOResponse estoqueNovo = estoqueService.createForTeclado(estoqueDTO);
        Long idNovoEstoque = estoqueNovo.id();

        TecladoDTO dto = new TecladoDTO(
            "Teclado Teste POST",          // nome
            199.99,                        // preco
            4L,                            // idModelo (Redragon K Series)
            1,                             // idTipo (Mecânico)
            "ABNT2",                       // idioma
            true,                          // comFio
            true,                          // iluminacaoRgb
            null,                          // dataCadastro (service preenche)
            4L,                            // idSwitch (Outemu Blue)
            1L,                            // idKeycap (ABS Padrão)
            idNovoEstoque,                 // <--- USA O NOVO ID DE ESTOQUE
            List.of(1L, 2L),               // idCategorias (Gamer, Escritório)
            List.of(100L)                  // idAcessorios (Cabo USB-C)
        );

        given()
          .contentType(ContentType.JSON)
          .body(dto)
          .when()
            .post("/teclados")
          .then()
            .statusCode(201)
            .body(
                "id", notNullValue(),
                "nome", is("Teclado Teste POST"),
                "estoque.id", is(idNovoEstoque.intValue()) 
            );
    }

    @Test
    public void alterarTest() {
        EstoqueDTO estoqueDTO_Original = new EstoqueDTO(10, null);
        Long idEstoqueOriginal = estoqueService.createForTeclado(estoqueDTO_Original).id();

        TecladoDTO dtoOriginal = new TecladoDTO(
            "Teclado Original", 100.0, 1L, 1, "ANSI", 
            true, true, null, 1L, 1L, idEstoqueOriginal, List.of(1L), List.of(100L)
        );
        TecladoDTOResponse tecladoCriado = tecladoService.create(dtoOriginal);
        Long id = tecladoCriado.id();

        EstoqueDTO estoqueDTO_Update = new EstoqueDTO(20, null);
        Long idEstoqueUpdate = estoqueService.createForTeclado(estoqueDTO_Update).id();

        TecladoDTO dtoUpdate = new TecladoDTO(
            "Teclado Alterado", 200.0, 2L, 2, "ABNT2", 
            false, false, null, 2L, 2L, idEstoqueUpdate, List.of(2L), List.of(101L)
        ); 

        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/teclados/" + id)
          .then()
            .statusCode(204); 

        TecladoDTOResponse tecladoAtualizado = tecladoService.findById(id);
        assertNotNull(tecladoAtualizado);
        assertEquals(dtoUpdate.nome(), tecladoAtualizado.nome());
        assertEquals(dtoUpdate.idEstoque(), tecladoAtualizado.estoque().id());
    }

    @Test
    public void apagarTest() {
        EstoqueDTO estoqueDTO = new EstoqueDTO(5, null);
        Long idEstoque = estoqueService.createForTeclado(estoqueDTO).id();

        TecladoDTO dto = new TecladoDTO(
            "Teclado Para Apagar", 10.0, 1L, 1, "ANSI", 
            true, true, null, 1L, 1L, idEstoque, List.of(1L), List.of(100L)
        );
        TecladoDTOResponse tecladoCriado = tecladoService.create(dto);
        Long id = tecladoCriado.id();

        given()
          .when()
            .delete("/teclados/" + id)
          .then()
            .statusCode(204); 
        
        TecladoDTOResponse tecladoApagado = tecladoService.findById(id);
        assertNull(tecladoApagado);
        
    }
}