package com.cydeo.day7;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class SpartanWithAuthTest extends SpartanAuthTestBase {


    @Test
    public void test(){
        given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(401).log().all().body("error",is ("Unauthorized"));
    }

    @Test
    public void test2(){

        given().auth().basic("admin", "admin")
                .and().accept(ContentType.JSON).log().all()
                .when().get("/api/spartans")
                .then().statusCode(200).log().all();
    }

    @Test
    public void test3(){
        given().auth().basic("editor", "editor")
                .and().accept(ContentType.JSON).and().pathParams("id", 40)
                .when().delete("/api/spartans/{id}").then().statusCode(403).log().all();
    }
}
