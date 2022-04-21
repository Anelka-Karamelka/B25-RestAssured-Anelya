package com.cydeo.day3;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiTestsWithParam extends HrTestBase {

    @Test
    public void test(){

        Response response = get("/regions");

        response.prettyPrint();

    }
    @Test
    public void test2(){
        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParam("q","{\"region_id\":2}")
                .log().all() //optional
                .when()
                .get("/countries");

        //verify status code
        assertEquals(200,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());
        // verify United States of America exists
        assertTrue(response.body().asString().contains("United States of America"));

}
    /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */
}
