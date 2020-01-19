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

public class GETPlanetStepDefs {

    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;

    private String ENDPOINT_GET_ALL_PLANETS = "https://swapi.co/api/planets";
    private String ENDPOINT_GET_PLANET_BY_ID = "https://swapi.co/api/planets/{id}";

    @When("^a user retrieves the all planets$")
    public void aUserRetrievesTheAllPlanets() {
        response = RestAssured
                .when()
                .get(ENDPOINT_GET_ALL_PLANETS);
    }

    @Then("^* planets the status code is (\\d+)$")
    public void allPeopleTheStatusCodeIs(int statusCode) {
        json = response
                .then()
                .statusCode(statusCode);
    }

    @And("^planet response includes the following$")
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

    @Given("^a planet exists with an id of (\\d+)$")
    public void aPlanetExistsWithAnIdOf(int id) {
        request = given()
                .pathParam("id", id);
    }

    @When("^a user retrieves the planet by id$")
    public void aUserRetrievesThePlanetById() {
        response = request
                .when()
                .get(ENDPOINT_GET_PLANET_BY_ID);
    }
}
