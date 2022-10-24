package apiTests;

import apiObjects.AuthService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class AuthServiceTests extends APIBaseTest{

    //RequestSpecification requestSpec;
    //ResponseSpecification responseSpecification;
    AuthService authService =new AuthService(requestSpec);

    public AuthServiceTests() {
        super();
    }



    @Test
    public void testAuthTokenGenerationCorrectly()
    {
        Response response;
        response = authService.validUserPassAuthAPIServiceResponse();
        System.out.println(authService.getAuthToken());

    }
    @Test

    public void emptyUsernameAuthTest()
    {
        Response response= authService.emptyUsernameAuthAPIServiceResponse();
        //Check the response is "Bad credentials"
        response.jsonPath().getString("reason").equals("Bad credentials");
        response.then().assertThat().statusCode(400);
        //Check status code = 400

    }

    @Test
    public void invalidTypeAuthTest()
    {
        Response response= authService.invalidTypeAuthAPIServiceResponse();
        //Check the response is "Bad credentials"
        //
        // +System.out.println(response.body().prettyPrint());
        response.jsonPath().getString("reason").equals("Bad credentials");
        response.then().assertThat().statusCode(400);
        //Check status code = 400

    }
}
