package apiTests;

import apiObjects.DeleteBookingService;
import apiObjects.UpdateBookingService;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class DeleteBookingTests extends APIBaseTest{
    public DeleteBookingTests() {
    }

    DeleteBookingService deleteBookingService = new DeleteBookingService(requestSpec);

    @Test
    public void deleteBookingWithInvalidBookingIdTest()
    {
        Response response =deleteBookingService.deleteBookingwithinvalidBookingIdResponse();
        //check status code is 404
        response.then().assertThat().statusCode(404);
        //Check the booking was updates and response contains "Not Found"
        response.then().assertThat().toString().contains("Not Found");
    }

    @Test
    public void deleteBookingwithoutTokenTest()
    {
        Response response =deleteBookingService.deleteBookingwithoutTokenResponse();
        //check status code is 403
        response.then().assertThat().statusCode(403);
        //Check the booking was updates and response contains "Forbidden"
        response.then().assertThat().toString().contains("Forbidden");

    }
}
