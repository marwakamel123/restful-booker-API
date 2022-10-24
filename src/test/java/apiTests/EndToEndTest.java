package apiTests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class EndToEndTest {

    String token;
    String bookingId;


    @BeforeClass

    public void createToken()
    {
        String url = "https://restful-booker.herokuapp.com/auth";
        String body = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }""";
        ValidatableResponse validatableResponse =
        given().baseUri(url).body(body).header("Content-Type" , "application/json")
                .when().post().then();
        Response response = validatableResponse.extract().response();
        JsonPath jsonPath = response.jsonPath();
        token = jsonPath.getString("token") ;


                /*.then().assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("token", Matchers.notNullValue())
                .body("token", Matchers.matchesRegex("^[a-z0-9]+$"));
*/
    }

    @Test(priority = 0)
    public void createBookingTest()
    {
        String url = "https://restful-booker.herokuapp.com/booking";
        String body =  """
                {
                     "firstname" : "Jim",
                     "lastname" : "Brown",
                     "totalprice" : 111,
                     "depositpaid" : true,
                     "bookingdates" : {
                         "checkin" : "2018-01-01",
                         "checkout" : "2019-01-01"
                     },
                     "additionalneeds" : "Breakfast"
                 }
                """;
        ValidatableResponse validatableResponse =
                given().baseUri(url).body(body).header("Content-Type" , "application/json")
                        .when().post()
                        .then().assertThat()

                        .statusCode(200)
                        .statusLine("HTTP/1.1 200 OK")
                        .body("booking.firstname", Matchers.equalTo("Jim"))
                        ;

        Response response = validatableResponse.extract().response();
        JsonPath jsonPath = response.jsonPath();
        bookingId= jsonPath.getString("bookingid");

    }

    @Test(priority = 1, dependsOnMethods = {"createBookingTest"})
    public void editBookingTest()
    {
        String url = "https://restful-booker.herokuapp.com/booking/" + bookingId;
        String body =  """
                {
                    "firstname" : "James",
                    "lastname" : "Brown",
                    "totalprice" : 111,
                    "depositpaid" : true,
                    "bookingdates" : {
                        "checkin" : "2018-01-01",
                        "checkout" : "2019-01-01"
                    },
                    "additionalneeds" : "Breakfast"
                }
                """;

        given().baseUri(url).body(body).header("Content-Type" , "application/json")
                .header("Cookie" , "token="  + token)
                .header("Authorisation" , "Basic")
                .when().put()
                .then()//.log().body()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("firstname", Matchers.equalTo("James"))
        ;
    }
    @Test(priority = 2, dependsOnMethods = {"editBookingTest"})
    public void getBookingTest()
    {
        String url= "https://restful-booker.herokuapp.com/booking/"+bookingId;
        given().baseUri(url).header("Content-Type" , "application/json")
                //.header("Cookie" , "token="  + token)
                //.header("Authorisation" , "Basic")
                .when().get()
                .then()//.log().body()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("firstname", Matchers.equalTo("James"))
        ;


    }

    @Test(priority = 3, dependsOnMethods = {"getBookingTest"})
    public void deleteBookingTest()
    {
        String url= "https://restful-booker.herokuapp.com/booking/"+bookingId;
        given().baseUri(url).header("Content-Type" , "application/json")
                .header("Cookie" , "token="  + token)
                .header("Authorisation" , "Basic")
                .when().delete()
                .then()//.log().body()
                .assertThat()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK")

        ;


    }



}
