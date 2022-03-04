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
        RestAssured.baseURI = "https://restapi.wcaquino.me";

    //Request
//        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
//
//        requestBuilder.log(LogDetail.ALL);
//        requestSpecification = requestBuilder.build();
//
//        RestAssured.requestSpecification = requestSpecification;
//
    //Response
//        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
//
//        responseBuilder.log(LogDetail.ALL);
//        responseSpecification = responseBuilder.build();
//
//        RestAssured.responseSpecification = responseSpecification;

    }
}
