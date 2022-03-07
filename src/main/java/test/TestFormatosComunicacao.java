package test;

import base.TestBase;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import org.junit.Test;

public class TestFormatosComunicacao extends TestBase {

    @Test
    public void CT01_get_recursos_via_query(){

        //query - parâmetro de consulta para personalizar respostas da api
        // ?atributo=valor&atributo2=valor2

        given()
                .log().all()
                .when()
                .get("/v2/users?format=xml")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML);
    }


    @Test
    public void CT02_get_recursos_via_queryParams(){
        given()
                .log().all()
                .queryParam("format", "json")
                .when()
                .get("/users")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }
}
