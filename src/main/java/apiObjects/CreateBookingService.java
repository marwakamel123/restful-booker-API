package apiObjects;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class CreateBookingService extends APIObject {


    public CreateBookingService(RequestSpecification requestSpec) {
        super(requestSpec);


    }


    public Response createValidBookingServiceResponse() {
        //Create valid booking method to use it in update and delete bookings
        String body = """
                {
                    "firstname" : "Tom",
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
        return this.requestSpec.basePath("/booking").contentType("application/json").body(body)
                .when().post();
    }

    public Response createEmptyFirstnameBookingServiceResponse(){
        String body = """
                {
                    "firstname" : "",
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
       return this.requestSpec.basePath("/booking").contentType("application/json").body(body)
                .when().post(); }

    public Response createInvalidDateBookingServiceResponse(){
        String body = """
                {
                    "firstname" : "Tom",
                    "lastname" : "Brown",
                    "totalprice" : 12 ,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-40-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }
                """;
        return this.requestSpec.basePath("/booking").contentType("application/json").body(body)
                .when().post(); }

    public Response createBookingServiceResponseWithNoAdditionalneeds(){
        String body = """
                {
                    "firstname" : "Tom",
                    "lastname" : "Brown",
                    "totalprice" : 12 ,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-04-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : ""
                }
                """;
        return this.requestSpec.basePath("/booking").contentType("application/json").body(body)
                .when().post(); }


}
