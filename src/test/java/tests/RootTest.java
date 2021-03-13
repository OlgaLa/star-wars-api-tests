package tests;

import io.restassured.RestAssured;
import models.RootResponse;
import org.junit.jupiter.api.Test;

import static constants.ApiNames.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class RootTest extends TestBase {


    @Test
    public void givenBaseURL_thenResponseContainsCorrectURLs() {

        var rootResponse = starWarsApiClient
                .getDeserializedResponse("", RootResponse.class);

        assertAll(
                () -> assertTrue(rootResponse.films.endsWith(FILMS)),
                () -> assertTrue(rootResponse.people.endsWith(PEOPLE)),
                () -> assertTrue(rootResponse.planets.endsWith(PLANETS)),
                () -> assertTrue(rootResponse.species.endsWith(SPECIES)),
                () -> assertTrue(rootResponse.starships.endsWith(STARSHIPS)),
                () -> assertTrue(rootResponse.vehicles.endsWith(VEHICLES))
        );
    }

    @Test
    public void givenRootRequest_thenReturnSchema() {

        var response = starWarsApiClient
                .getDeserializedResponse("", RootResponse.class);


        assertAll(
                () -> assertDoesNotThrow(() -> starWarsApiClient.ensureSchema(response.films)),
                () -> assertDoesNotThrow(() -> starWarsApiClient.ensureSchema(response.people)),
                () -> assertDoesNotThrow(() -> starWarsApiClient.ensureSchema(response.starships)),
                () -> assertDoesNotThrow(() -> starWarsApiClient.ensureSchema(response.planets)),
                () -> assertDoesNotThrow(() -> starWarsApiClient.ensureSchema(response.species)),
                () -> assertDoesNotThrow(() -> starWarsApiClient.ensureSchema(response.vehicles))
        );
    }

    @Test
    public void givenHTTPUrl_thenRedirectToHTTPS() {

        final String httpURL = RestAssured.baseURI.replace("https:/", "http:/");
        given()
                .redirects()
                .follow(false)
                .expect()
                .statusCode(301)
                .header("Location", RestAssured.baseURI)
                .when()
                .get(httpURL);

    }
}
