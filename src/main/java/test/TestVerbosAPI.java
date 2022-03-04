package test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import base.TestBase;
import io.restassured.http.ContentType;
import org.junit.Test;

public class TestVerbosAPI extends TestBase {

    @Test
    public void CT01_request_response_specification(){

        //general structure for testing
        given()
                .log().all()
                .when()
                .get("/usersXML/3")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void CT02_inserir_recursos_com_POST(){
        // {"name" : "Mateus", "age" : 19}

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\" : \"Mateus\", \"age\" : 19}")
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name",is("Mateus"))
                .body("age", is(19));
    }

    @Test
    public void CT03_validacao_campo_obrigatorio_nome(){
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"age\" : 19}")
                .when()
                .post("https://restapi.wcaquino.me/users")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", is("Name é um atributo obrigatório"));
    }

    @Test
    public void CT04_alterar_recursos_com_PUT(){
        // {"name" : "Mateus Franco", "age" : 25}

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body("{\"name\" : \"Mateus Franco\", \"age\" : 25}")
                .when()
                .put("/users/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Mateus Franco"))
                .body("age", is(25))
                .body("id", is(1));
    }

    @Test
    public void CT05_apagar_recurso_existente_com_DELETE(){
        given()
                .log().all()
                .when()
                .delete("/users/1")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void CT06_validacao_delecao_usuario_inexistente(){
        given()
                .log().all()
                .when()
                .delete("/users/1000")
                .then()
                .log().all()
                .statusCode(400);
    }
}