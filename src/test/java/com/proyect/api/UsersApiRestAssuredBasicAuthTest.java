package com.proyect.api;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class UsersApiRestAssuredBasicAuthTest {

    @Test
    void testGetUserBasicAuth(){
     
    given()
     .auth()
     .preemptive()
     .basic("admin","1234")
     
    .when()
       .get("http://localhost:8081/api/users")
     
    .then()
       .statusCode(200);
    }

     @Test
    void testGetUserWrongPass(){
     
    given()
     .auth()
     .preemptive()
     .basic("admin","xxxx")
     
    .when()
       .get("http://localhost:8081/api/users")
     
    .then()
       .statusCode(401);
    }

     @Test
    void testGetUserWithoutAuth(){
     
    given()
    
     
    .when()
       .get("http://localhost:8081/api/users")
     
    .then()
       .statusCode(401);
    }

}
