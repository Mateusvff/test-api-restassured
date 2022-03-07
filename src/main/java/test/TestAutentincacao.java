package test;

import base.TestBase;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.junit.Test;

import javax.security.auth.login.LoginContext;
import java.util.HashMap;
import java.util.Map;

public class TestAutentincacao extends TestBase {

    //Key auth
    //API KEY - allows access to the API
    //API key example = 9051123e-9e37-11ec-b2a7-0242ac130002-905112c0-9e37-11ec-b2a7-0242ac130002

    //Token auth
    //JWT - JSON Web Token

    @Test
    public void autenticacao_com_token_JWT() {

        Map<String, String > login = new HashMap<String, String>();
        login.put("email" , "mateusfrancoviniciuss@gmail.com");
        login.put("senha" , "senhaSB");


        // login na api

       String token = given()
                .log().all()
                .body(login)   // informações que desejo enviar com o método POST
                .contentType(ContentType.JSON)    // Tipo de informação que desejo enviar com o método POST
                .when()
                .post("https://barrigarest.wcaquino.me/signin")
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("token");


       // Obter as contas - enviar o Token através do header()

        given()
                .log().all()
                .header("Authorization", "JWT " + token)
                .when()
                .get("https://barrigarest.wcaquino.me/contas")
                .then()
                .log().all()
                .statusCode(200)
                .body("nome", is("Conta de teste"));

    }
}
