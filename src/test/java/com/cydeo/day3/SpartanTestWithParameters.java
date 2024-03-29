package com.cydeo.day3;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestWithParameters extends SpartanTestBase {


    @DisplayName("GET request to /api/spartans/{id} with id 5")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParams("id", 5)
                .when()
                .get("/api/spartans/{id}");

        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify "Blythe" inside the payload
        assertTrue(response.body().asString().contains("Blythe"));

    }

    @Test
    public void test2() {
/*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */
        Response response = given()
                .accept(ContentType.JSON)
                .pathParams("id", 500)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.header("Content-type"));
        assertTrue(response.body().asString().contains("Not Found"));

    }



         /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */

        @DisplayName("GET request to /api/spartans/search with Query Param")
        @Test
        public void test3(){
            Response response = given().log().all().
                    accept(ContentType.JSON)
                    .and().queryParam("gender", "Female")
                    .and().queryParam("nameContains", "e")
                    .when()
                    .get("/api/spartans/search");

            assertEquals(200, response.statusCode());
            assertEquals("application/json", response.contentType());
            assertTrue(response.body().asString().contains("Female"));
            assertTrue(response.body().asString().contains("Janette"));

        }

    @DisplayName("GET request to /api/spartans/search with Query Params (MAP)")
    @Test
    public void test4(){
        //create a map and store query params information
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().log().all().
                accept(ContentType.JSON).
                queryParams(queryMap)
                .when().
                get("/api/spartans/search");

        //verify status code
        assertEquals(200,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());
        //verify Female inside body
        assertTrue(response.body().asString().contains("Female"));
        //verify Janette inside the json body
        assertTrue(response.body().asString().contains("Janette"));

    }
}


