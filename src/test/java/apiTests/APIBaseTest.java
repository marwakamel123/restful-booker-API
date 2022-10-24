package apiTests;



import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;

public class APIBaseTest {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpecification;

    public APIBaseTest() {
        this.requestSpec = given();
        this.responseSpecification =this.requestSpec.then();
    }


}
