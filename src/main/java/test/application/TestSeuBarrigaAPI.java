package test.application;

import base.TestBase;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;

public class TestSeuBarrigaAPI extends TestBase {

    private String token;

    //Login na API via token JWT para os casos de teste
    @Before
    public void login() {
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
    public void CT01_nao_deve_acessar_api_sem_token() {
        given()
                .when()
                .get("/contas")
                .then()
                .statusCode(401);
    }

    @Test
    public void CT02_deve_incluir_conta_com_sucesso() {
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
    public void CT03_deve_alterar_conta_com_sucesso() {
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
    public void CT04_nao_deve_incluir_conta_com_numero_repetido() {
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
    public void CT05_deve_inserir_movimentacao_com_sucesso() {

        MovimentacaoObjeto mov = new MovimentacaoObjeto(1108518, "Receita", "09/03/2020", "09/03/2020", "teste", "single", 1050, "conta alterada via api", true);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(mov)
                .when()
                .post("/transacoes")
                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    public void CT06_deve_validar_campos_obrigatorios_na_movimentacao() {
        given()
                .log().all()
                .header("Authorization", "JWT " + token)
                .body("{}")
                .when()
                .post("/transacoes")
                .then()
                .log().all()
                .statusCode(400)
                .body("$", hasSize(8))
                .body("msg", hasItems(
                        "Data da Movimentação é obrigatório",
                        "Data do pagamento é obrigatório",
                        "Descrição é obrigatório",
                        "Interessado é obrigatório",
                        "Valor é obrigatório",
                        "Valor deve ser um número",
                        "Conta é obrigatório",
                        "Situação é obrigatório"));
    }

    @Test
    public void CT07_nao_deve_cadastrar_movimentacao_futura() {
        MovimentacaoObjeto movFutura = new MovimentacaoObjeto(1108124, "Receita", "09/03/2023", "09/03/2023", "teste", "single", 1050, "conta alterada via api", true);
        // ANO 2023

        given()
                .log().all()
                .header("Authorization", "JWT " + token)
                .contentType(ContentType.JSON)
                .body(movFutura)
                .when()
                .post("/transacoes")
                .then()
                .log().all()
                .statusCode(400)
                .body("msg", is("Data da Movimentação deve ser menor ou igual à data atual"));

    }
}
