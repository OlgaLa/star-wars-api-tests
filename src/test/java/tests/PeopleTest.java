package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import models.PeopleResponse;
import org.junit.jupiter.api.Test;

import static constants.ApiNames.PEOPLE;
import static constants.ApiNames.SCHEMA;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PeopleTest extends TestBase {

    @Test
    public void givenGetAllPeople_thenReturnAllPeople() {

        var peopleResponse = starWarsApiClient.getDeserializedResponse(PEOPLE, PeopleResponse.class);

        var numberOfPeople = peopleResponse.results.size();

        while (peopleResponse.next != null) {
            peopleResponse = starWarsApiClient.getDeserializedResponse(peopleResponse.next, PeopleResponse.class);
            numberOfPeople += peopleResponse.results.size();
        }

        assertEquals(peopleResponse.count, numberOfPeople);
    }


    @Test
    public void givenGetPeopleAndSchema_thenReturnPeopleLikeInSchema() throws JsonProcessingException, ProcessingException {

        var schema = given()
                .get(PEOPLE + SCHEMA)
                .getBody()
                .asString();

        var firstPerson = starWarsApiClient.getDeserializedResponse(PEOPLE, PeopleResponse.class)
                .results.stream().findFirst();


        var people = given()
                .get(firstPerson.get().url)
                .getBody()
                .asString();

        var factory = JsonSchemaFactory.byDefault();
        var m = new ObjectMapper();
        var jsonSchema = factory
                .getJsonSchema(m.readTree(schema));
        var json = m.readTree(people);
        var report = jsonSchema.validate(json);

        assertTrue(report.isSuccess());
    }

}
