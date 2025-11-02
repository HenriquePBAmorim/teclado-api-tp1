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
        // O import.sql insere 5 municípios
        given()
          .when()
            .get("/municipios")
          .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(5)); // Verifica os dados do import
    }

    @Test
    public void buscarPorNomeTest() {
        // O import.sql insere "Palmas"
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
        // Teste de POST (Black box)
        // 1L = Estado 'Tocantins' (do import.sql)
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
            .statusCode(201) // 201 Created
            .body(
                "id", notNullValue(),
                "nome", is("Paraíso do Tocantins"),
                "estado.id", is(1), // Verifica o objeto aninhado
                "estado.sigla", is("TO")
            );
    }

    @Test
    public void alterarTest() {
        // 1. Setup (Gray Box) - Criando um município
        // 1L = Tocantins
        MunicipioDTO dtoOriginal = new MunicipioDTO("Município Original", 1L);
        MunicipioDTOResponse municipioCriado = municipioService.create(dtoOriginal);
        Long id = municipioCriado.id();

        // 2. Teste (API)
        // 2L = Goiás
        MunicipioDTO dtoUpdate = new MunicipioDTO("Município Alterado", 2L); 

        given()
          .contentType(ContentType.JSON)
          .body(dtoUpdate)
          .when()
            .put("/municipios/" + id) // Usando o ID criado
          .then()
            .statusCode(204); // 204 No Content

        // 3. Verificação (Gray Box) - Verificando no banco
        MunicipioDTOResponse municipioAtualizado = municipioService.findById(id);
        assertNotNull(municipioAtualizado); // Garante que não foi apagado
        assertEquals(dtoUpdate.nome(), municipioAtualizado.nome());
        assertEquals(dtoUpdate.idEstado(), municipioAtualizado.estado().id());
    }

    @Test
    public void apagarTest() {
        // 1. Setup (Gray Box)
        MunicipioDTO dto = new MunicipioDTO("Município Para Apagar", 1L);
        MunicipioDTOResponse municipioCriado = municipioService.create(dto);
        Long id = municipioCriado.id();

        // 2. Teste (API)
        given()
          .when()
            .delete("/municipios/" + id)
          .then()
            .statusCode(204); // 204 No Content

        // 3. Verificação (Gray Box)
        // (Usando o padrão do professor, que espera 'null' do findById)
        MunicipioDTOResponse municipioApagado = municipioService.findById(id);
        assertNull(municipioApagado);
    }
}