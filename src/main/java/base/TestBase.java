package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;

public class TestBase {

    public static RequestSpecification requestSpecification;
    public static ResponseSpecification responseSpecification;

    @BeforeClass
    public static void setup(){
        RestAssured.baseURI = "https://barrigarest.wcaquino.me";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
}
