package test;

import base.TestBase;
import static  io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import java.io.File;

public class TestArquivos extends TestBase {

    @Test
    public void CT01_valida_upload_arquivos(){
        given()
                .log().all()
                .when()
                .post("/upload")
                .then()
                .log().all()
                .statusCode(404)
                .body("error", is("Arquivo não enviado"));
    }

    @Test
    public void CT02_realzia_upload_arquivos(){
        given()
                .log().all()
                .multiPart("arquivo", new File("src/main/resources/users.pdf"))   //realiza o envio de arquivos
                .when()
                .post("/upload")
                .then()
                .log().all()
                .statusCode(201);
    }
}
