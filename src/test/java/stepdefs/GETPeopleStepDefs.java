package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GETPeopleStepDefs {

    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    private String ENDPOINT_GET_ALL_PEOPLE = "https://swapi.co/api/people";
    private String ENDPOINT_GET_PEOPLE_BY_ID = "https://swapi.co/api/people/{id}";

    @When("^a user retrieves the all people$")
    public void aUserRetrievesTheAllPeople() {
        response = RestAssured
                .when()
                .get(ENDPOINT_GET_ALL_PEOPLE);
    }

    @And("^people response includes the following$")
    public void responseIncludesTheFollowing(Map<String,String> responseFields) {
        for (Map.Entry<String, String> field : responseFields.entrySet()) {
            if(StringUtils.isNumeric(field.getValue())){
                json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
            }
            else{
                json.body(field.getKey(), equalTo(field.getValue()));
            }
        }
    }

    @Given("^a people exists with an id of (\\d+)$")
    public void aPeopleExistsWithAnIdOf(int id) {
        request = given()
                .pathParam("id", id);
    }

    @When("^a user retrieves the people by id$")
    public void aUserRetrievesThePeopleById() {
        response = request
                .when()
                .get(ENDPOINT_GET_PEOPLE_BY_ID);
    }

    @Then("^* people the status code is (\\d+)$")
    public void allPeopleTheStatusCodeIs(int statusCode) {
        json = response
                .then()
                .statusCode(statusCode);
    }
}
