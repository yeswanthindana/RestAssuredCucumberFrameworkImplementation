package stepdefinitions;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import pojo.addPlace;
import pojo.location;
import resources.APIResources;
import resources.testdatabuild;
import resources.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class stepdefs extends utilities {

    public static addPlace sp;
    public static RequestSpecification req;
    public static ResponseSpecification res;
    public static RequestSpecification reqspec;
    public static Response response;
    public static JsonPath js ;
    public static APIResources resourceAPI;
    public static String actplace_id;
    public static String body;

    @ParameterType("true|false")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }


    @Given("{string} request payload with {int} {int} {int} {string} {string} {string}")
    public void request_payload_with_true(String string, Integer id, Integer petId, Integer quantity, String shipDate, String status, String complete) throws  Exception{
        body = testdatabuild.createOrder(id, petId, quantity, shipDate, status, complete);
        req = requestSpecification();
        res = responseSpecification();

        reqspec = given()
                .spec(req)
                .body(body)
                .log().body();


    }


    @Given("{string} request payload with {string} {string} {string}")
    public void request_payload_with(String string, String name, String aisle, String author) throws Exception {
        body  = testdatabuild.addLibraryBook(name,aisle,author);
        req = requestSpecification();
        res = responseSpecification();

        reqspec =
                given()
                        .spec(req)
                        .body(body)
                        .log().body();

    }





    @Given("{string} request Payload with name {string} {string} {string}")
    public void request_payload_with_name(String string, String name, String address, String language) throws IOException {
        sp = testdatabuild.addPlacepayloadwithexamples(name,address,language);
        req = requestSpecification();
        res = responseSpecification();


        reqspec =
                given()
                        .queryParam("key","qaclick123")
                        .spec(req)
                        .body(sp)
                        .log().body();


    }

    @Given("{string} request Payload")
    public void request_payload(String string) throws IOException {

        sp = testdatabuild.addPlacepayload();
        req = requestSpecification();
        res = responseSpecification();


        reqspec =
                given()
                        .queryParam("key","qaclick123")
                        .spec(req)
                        .body(sp)
                        .log().body();


    }

    @When("user calls {string} with {string} https request")
    public void user_calls_with_https_request(String resource, String method) {

        resourceAPI = APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        switch(method.toLowerCase()){
            case "post":
                response =
                        reqspec.when()
                                .post(resourceAPI.getResource())
                                .then()
                                .log().body()
                                .extract().response();
                break;

            case "get":
                response =
                        reqspec.when()
                                .get(resourceAPI.getResource())
                                .then()
                                .log().body()
                                .extract().response();
                break;

            case "delete":
                response =
                        reqspec.when()
                                .delete(resourceAPI.getResource())
                                .then()
                                .log().body()
                                .extract().response();
                break;
            default:
                Assert.fail("Invalid method type provided: " + method);
                break;
        }


    }

    @Then("the Response should be success with code {int}")
    public void the_response_should_be_success_with_code(int statcode) {

        assertEquals(response.getStatusCode(),statcode);
    }
    @Then("{string} message should be {string}")
    public void message_should_be(String field,String statmsg) {
     //   js = new JsonPath(response.asString());
      //  assertEquals(js.get("status"), statmsg);
        assertEquals(utilities.getSONPath(response,field),statmsg);
    }

    @Then("{string} in response should be {string}")
    public void in_response_should_be(String key, String value) {
     //   js = new JsonPath(response.asString());
      //  assertEquals(js.get(key),value);
        Object act = utilities.getSONPath(response, key);
        assertEquals(value,act.toString());
    }



    @Then("verify {string} {string} {string} should be equal from {string} response")
    public void verify_in_should_be_equal_from_response(String name, String language, String address, String resource) { {
        actplace_id = utilities.getSONPath(response, "place_id");
        print("place_id: " + actplace_id);

        if (actplace_id == null || actplace_id.trim().isEmpty()) {
            Assert.fail("place_id is null or empty in the previous response");
        }

        reqspec = given()
                .queryParam("place_id", actplace_id)
                .queryParam("key", "qaclick123")
                .spec(req)
                .log().body();

        user_calls_with_https_request(resource, "GET");

        String actName = utilities.getSONPath(response, "name");
        String actAddress = utilities.getSONPath(response, "address");
        String actLanguage = utilities.getSONPath(response, "language");

        assertEquals(name, actName);
        assertEquals(address, actAddress);
        assertEquals(language, actLanguage);
    }

    }

    @Given("delPlaceAPI request payload")
    public void del_place_api_request_payload() throws IOException {
        String delbody = testdatabuild.deletePlacePayload(actplace_id);
        req = requestSpecification();
        res = responseSpecification();

        reqspec =
                given()
                        .spec(req)
                        .body(delbody)
                        .log().body();
    }


    @Given("{string} request payload with {string}")
    public void request_payload_with(String string, String id) throws IOException {
       req = requestSpecification();
       res = responseSpecification();


       reqspec =
       given()
                .spec(req)
                .pathParam("id", Integer.parseInt(id))
                .log().all();
    }
}
