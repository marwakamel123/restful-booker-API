package apiTests;

/*
#Happy synario is tested in EndToEnd class
#Preconditions before class:
            +token
            +set request "Authorisation" , "Basic"
            + create new booking and save the booking ID
#This class is made to test bad synario for update booking service
        - update without token
        -update with invalid Authorisation
        - update booking with empty field - ex: firstname-> expected code 400, missing username
        - update booking with invalid data types
                + "lastname" = integer ->expected code 400, bad request
                + "totalprice" = " " ->expected code 400, bad request
*/


import apiObjects.AuthService;
import apiObjects.UpdateBookingService;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdateBookingTests extends APIBaseTest{
    public UpdateBookingTests() {super();}

UpdateBookingService updateBookingService = new UpdateBookingService(requestSpec);
    @Test
    public void updateBookingWithInvalidTokenTest()
    {
        //System.out.println(requestSpec.cookie("token").toString());
        Response response =updateBookingService.updateBookingWithoutTokenResponse();

        //check status code is 403
        response.then().assertThat().statusCode(403);
        //Check the response is "Forbidden"
        response.then().assertThat().toString().contains("Forbidden");
    }
    @Test
    public void updateBookingWithoutBasicAuthTest()
    {
        Response response =updateBookingService.updateBookingWithoutBasicAuthResponse();
        //check status code is 200
        response.then().assertThat().statusCode(200);
        //Check the booking was updates and response contains "Sam"
        response.then().assertThat().toString().contains("Sam");
    }

    @Test
    public void updateBookingWithInvalidFirstnameTest()
    {//System response is : 500 , Internal Server Error. this is not descriptive should be to 400 or 403, with descriptive error message

        Response response =updateBookingService.updateBookingWithInvalidFirstnameServiceResponse();
        //check status code is 400
        response.then().assertThat().statusCode(400);
        //Check the response is "Invalid datatype"
        response.then().assertThat().toString().contains("Invalid Firstname");
    }

    @Test
    public void updateBookingWithInvalidInvalidDepositpaidTest()
    {//System response is : 200 , and update the booking. response should be 400 or 403, with descriptive error message

        Response response =updateBookingService.updateBookingWithInvalidDepositpaidServiceResponse();
        //check status code is 403
        response.then().assertThat().statusCode(403);
        //Check the response is "Forbidden"
        response.then().assertThat().toString().contains("Invalid Total Deposit");
    }

}
