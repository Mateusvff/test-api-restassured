package test.application;

import base.TestBase;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestSeuBarrigaAPI extends TestBase {

    private String token;

    //Login na API via token JWT para os casos de teste
    @Before
    public void login(){
        Map<String, String> login = new HashMap<>();
        login.put("email", "mateusfrancoviniciuss@gmail.com");
        login.put("senha", "senhaSB");

        token = given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/signin")
                .then()
                .statusCode(200)
                .extract().path("token");
    }

    @Test
    public void CT01_nao_deve_acessar_api_sem_token(){
        given()
                .when()
                .get("/contas")
                .then()
                .statusCode(401);
    }

    @Test
    public void CT02_deve_incluir_conta_com_sucesso(){
        given()
                .header("Authorization", "JWT " + token)
                .contentType(ContentType.JSON)
                .body(" {\"nome\" : \"conta adicionada via api\"}")
                .when()
                .post("/contas")
                .then()
                .statusCode(201);
    }

    @Test
    public void CT03_deve_alterar_conta_com_sucesso(){
        given()
                .header("Authorization", "JWT " + token)
                .body("{\"nome\" : \"conta alterada via api\"}")
                .contentType(ContentType.JSON)
                .when()
                .put("/contas/1108518")
                .then()
                .statusCode(200);
    }

    @Test
    public void CT04_nao_deve_incluir_conta_com_numero_repetido(){
        given()
                .header("Authorization", "JWT " + token)
                .contentType(ContentType.JSON)
                .body("{\"nome\" : \"Conta de teste\"}")
                .when()
                .post("/contas")
                .then()
                .statusCode(400)
                .body("error", is("Já existe uma conta com esse nome!"));
    }

    @Test
    public void CT05_deve_inserir_movimentacao_com_sucesso(){
        given()
                .log().all()
                .header("Authorization", "JWT " + token)
//              .body()
                .when()
                .post("/transacoes")
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void CT06_deve_validar_campos_obrigatorios_na_movimentacao(){
        given()
                .when()
                .then()

        ;
    }

    @Test
    public void CT07_nao_deve_cadastrar_movimentacao_futura(){
        given()
                .when()
                .then()

        ;
    }

    @Test
    public void CT08_nao_deve_remover_conta_com_movimentacao(){
        given()
                .when()
                .then()

        ;
    }

    @Test
    public void CT09_deve_calcular_saldo_das_contas(){

    }

    @Test
    public void CT10_deve_remover_uma_movimentacao(){

    }
}
