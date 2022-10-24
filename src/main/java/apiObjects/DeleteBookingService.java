package apiObjects;

import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteBookingService extends APIObject{
        public DeleteBookingService(RequestSpecification requestSpec)
        {
            super(requestSpec);
            deleteBookingPreConditions();
        }

        private String token;
        private String bookingId;
        AuthService authService = new AuthService(requestSpec);
        CreateBookingService createBookingService = new CreateBookingService(requestSpec);


        public void deleteBookingPreConditions()
        {
            //Setup token , bookingId, Authorisation
            token = authService.getAuthToken();
            Response response = createBookingService.createValidBookingServiceResponse();
            bookingId = response.jsonPath().getString("bookingid");
            requestSpec.cookie("token", token).header("Authorisation" , "Basic").contentType("application/json");
        }

    public Response deleteBookingwithinvalidBookingIdResponse(){
        String bookingId = "??";
        return this.requestSpec.basePath("/booking/"+bookingId).cookie("token", token).when().delete(); }

    public Response deleteBookingwithoutTokenResponse(){
        ((RequestSpecificationImpl) requestSpec).removeCookies();
        return this.requestSpec.basePath("/booking/"+bookingId).when().delete(); }

}
