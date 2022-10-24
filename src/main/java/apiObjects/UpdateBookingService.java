package apiObjects;

import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



    public class UpdateBookingService extends APIObject{
    public UpdateBookingService(RequestSpecification requestSpec)
    {
        super(requestSpec);
        updateBookingPreConditions();
    }

        private String token;
        private String bookingId;
        AuthService authService = new AuthService(requestSpec);
        CreateBookingService createBookingService = new CreateBookingService(requestSpec);


    public void updateBookingPreConditions()
    {
        //Setup token , bookingId, Authorisation
        token = authService.getAuthToken();
        Response response = createBookingService.createValidBookingServiceResponse();
        bookingId = response.jsonPath().getString("bookingid");
        requestSpec.cookie("token", token).header("Authorisation" , "Basic").contentType("application/json");
        //System.out.println(((RequestSpecificationImpl) requestSpec).getHeaders().toString());
        //System.out.println(((RequestSpecificationImpl) requestSpec).getCookies().toString());
        //System.out.println(  bookingId +" "+token)  ;
    }

        public Response updateBookingWithoutTokenResponse(){
            String body = """
                {
                    "firstname" : "Sam",
                    "lastname" : "Brown",
                    "totalprice" : 12 ,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-04-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }
                """;
            ((RequestSpecificationImpl) requestSpec).removeCookies();
            return  this.requestSpec.basePath("/booking/"+bookingId).body(body)
                    .when().put();

            }

        public Response updateBookingWithoutBasicAuthResponse(){
            String body = """
                {
                    "firstname" : "Sam",
                    "lastname" : "Brown",
                    "totalprice" : 12 ,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-04-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }
                """;
            ((RequestSpecificationImpl) requestSpec).removeHeader("Authorisation");
            return
                    this.requestSpec.basePath("/booking/"+bookingId).body(body).cookie("token", token)
                    .when().put(); }

        public Response updateBookingWithInvalidFirstnameServiceResponse(){
            String body = """
                {
                    "firstname" : 123,
                    "lastname" : "Brown",
                    "totalprice" : 12 ,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-04-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }
                """;

            return this.requestSpec.basePath("/booking/"+bookingId).body(body).cookie("token", token).when().put();
        }

        public Response updateBookingWithInvalidDepositpaidServiceResponse(){
            String body = """
                {
                    "firstname" : "Sam",
                    "lastname" : "Brown",
                    "totalprice" : 12 ,
                    "depositpaid" : "12",
                    "bookingdates" : {
                        "checkin" : "2018-04-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }
                """;

            return this.requestSpec.basePath("/booking/"+bookingId).body(body).cookie("token", token)
                    .when().put(); }





}
