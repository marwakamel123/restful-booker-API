package apiObjects;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class APIObject {

     public RequestSpecification requestSpec;
     //public  ResponseSpecification responseSpecification;


    public APIObject(RequestSpecification requestSpec) {
        this.requestSpec = requestSpec.baseUri("https://restful-booker.herokuapp.com").contentType(ContentType.JSON);
    }

    //public ResponseSpecification createResponseSpecification() { return    responseSpecification.expect().contentType(ContentType.JSON).statusCode(200);}


}
