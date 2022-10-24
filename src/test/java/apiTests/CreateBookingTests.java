package apiTests;

import apiObjects.APIObject;
import apiObjects.AuthService;
import apiObjects.CreateBookingService;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
/*
#Happy synario is tested in EndToEnd class

#Logically we need token to create new booking but according to documentation it is not needed

#This class is made to test bad synario for create booking service
        - Create booking with empty field - ex: firstname-> expected code 400, missing username
        - Create booking with invalid data types
                + lastname = integer ->expected code 400, bad request
                + totalprice = 0 ->expected code 400, bad request
                + totalprice = empty ->expected code 400, bad request
                + depositpaid = String  ->expected code 400, bad request
                + invalid date format -> expected code 400,Invalid date
        - Create booking with no additionalneeds ->expected code 400, bad request
 */

public class CreateBookingTests extends APIBaseTest {


    public CreateBookingTests() {
        super();
    }

    CreateBookingService createBookingService= new CreateBookingService(requestSpec);

    @Test
    public void createBookingwithEmptyFirstnameTest()
    {
        Response response = createBookingService.createEmptyFirstnameBookingServiceResponse();
        //check status code is 400
        response.then().assertThat().statusCode(400);
        //Check the response is "Bad credentials"
        response.then().assertThat().toString().contains("Bad Request");


    }

    @Test
    public void createBookingWithInvalidDateTest()
    {
        Response response = createBookingService.createInvalidDateBookingServiceResponse();
        //check status code is 400
        response.then().assertThat().statusCode(400);
        //Check the response is "Invalid date"
        response.then().assertThat().toString().contains("Invalid date");

    }

    @Test
    public void createBookingWithNoAdditionalneedsTest()
    {

        Response response = createBookingService.createBookingServiceResponseWithNoAdditionalneeds();
        //check status code is 200
        response.then().assertThat().statusCode(200);
        //Check the response contains "Tom"
        response.then().assertThat().toString().contains("Tom");
    }

}
