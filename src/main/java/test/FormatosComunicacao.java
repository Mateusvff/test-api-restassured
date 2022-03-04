package test;

import base.TestBase;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import org.junit.Test;

public class FormatosComunicacao extends TestBase {

    @Test
    public void CT01_enviar_recursos_via_query(){
        given()
                .log().all()
                .when()
                .get("/v2/users?format=xml")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.XML);
    }

}
