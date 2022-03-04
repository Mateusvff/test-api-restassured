package test;

import base.TestBase;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TestSerializacao extends TestBase {

    @Test
    public void CT01_inserir_recurso_serializacao_MAP(){

        Map<String, Object> params = new HashMap<>();

        params.put("name", "Usuario via map");
        params.put("age", "25");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(params)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void CT02_inserir_recurso_serializacao_objeto(){
        TestSerializacaoObjeto user = new TestSerializacaoObjeto("Usuario via objeto", 25, 1050.00);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201);

    }

    @Test
    public void CT03_deserializando_objeto_da_response(){
        TestSerializacaoObjeto user = new TestSerializacaoObjeto("Usuario via objeto", 25, 1050.00);

       TestSerializacaoObjeto usuarioRetornado =  given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract().body().as(TestSerializacaoObjeto.class);

        Assert.assertEquals("Usuario via objeto", user.getName());
        Assert.assertEquals(25, user.getAge());

    }
}
