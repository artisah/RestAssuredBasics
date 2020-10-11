package org.example;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Test2 {

    @Test
    public void requestUsZipCode90210_checkStatusCode_expectHttp200() {

        given().
                when().
                    get("http://api.zippopotam.us/us/90210").
                then().
                    assertThat().
                    statusCode(200);
    }

    @Test
    public void requestUsZipCode90210_checkContentType_expectApplicationJson() {
        given().
                when().
                    get("http://api.zippopotam.us/us/90210").
                then().
                    assertThat().
                    contentType(ContentType.JSON);


    }

    @Test
    public void requestUsZipCode90210_logRequestAndResponsedetails() {
        given().
                log().all().
        when().
                get("http://api.zippopotam.us/us/90210").
        then().
              log().body();
    }

    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
        when().get("http://api.zippopotam.us/us/90210").
        then().
                assertThat().
               body("places[0].'place name'", equalTo("Beverly Hills"));  // place name has space so quotes are required
    }

    @Test
    public void requestUsZipCode90210_checkStateNameInResponseBody_expectCalifornia() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].state", equalTo("California"));  //state is single word so no quotes required.
    }

    @Test
    public void requestUsZipCode90210_checkListOfPlaceNamesInResponseBody_expectContainsBeverlyHills() {
        given().
        when().
                get("http://zippopotam.us/us/90210").
        then().
                assertThat().
                body("places.'place name'", hasItem("Beverly Hills")); // returns coolection of all place name and we are checking if that collection contains 'Beverly hills'
    }

    @Test
    public void requestUsZipCode90210_checkNumberOfPlaceNamesInResponseBody_expectOne() {
        given().
        when().
                get("http://zippopotam.us/us/90210").
        then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }
}
