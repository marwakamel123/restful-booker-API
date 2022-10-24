package apiObjects;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class AuthService extends APIObject{


    public AuthService(RequestSpecification requestSpec) {
        super(requestSpec);
    }




    public Response validUserPassAuthAPIServiceResponse()
    {
        String ValidBody = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }""";
       return this.requestSpec.basePath("/auth").contentType("application/json").body(ValidBody)
                .when().post();
                //.then();

    }

    public String getAuthToken()
    {
        return  validUserPassAuthAPIServiceResponse().jsonPath().getString("token");
    }

    public Response emptyUsernameAuthAPIServiceResponse()
    {
        String ValidBody = """
                {
                    "username" : "",
                    "password" : "password123"
                }""";
        return this.requestSpec.basePath("/auth").contentType("application/json").body(ValidBody)
                .when().post();
        //.then();

    }
    public Response invalidTypeAuthAPIServiceResponse()
    {
        String ValidBody = """
                {
                    "username" : 1,
                    "password" : "password123"
                }""";
        return this.requestSpec.basePath("/auth").contentType("application/json").body(ValidBody)
                .when().post();
        //.then();

    }
}
