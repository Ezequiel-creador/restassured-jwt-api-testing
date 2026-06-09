package com.proyect.testing.api;

import org.junit.jupiter.api.Test;

import com.proyect.testing.report.BaseTest;

import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;

public class JwtApiTest extends BaseTest {


@Test
void testGetUsersWithJwt(){
    String token = 
    given()
     .contentType(ContentType.JSON)
     .body("""
        {"username":"admin",
        "password":"1234"
        }
             """)
    .when()
    .post("http://localhost:8081/api/auth/login")

    .then()
    .statusCode(200)
    .extract()
    .path("token");


    given()
    .header("Authorization","Bearer "+token)

    .when()
    .get("http://localhost:8081/api/users")

    .then()
    .statusCode(200);

}

@Test
void testGetUsersInvalidCredentials(){
  

    given()
     .contentType(ContentType.JSON)
     .body("""
        {"username":"admin",
        "password":"xxxx"
        }
             """)
    .when()
    .post("http://localhost:8081/api/auth/login")

    .then()
    .statusCode(403);
    
   }

   @Test
    void testGetUsersWithInvalidJwt(){
    given()
    .header("Authorization","Bearer "+"token invalido")

    .when()
    .get("http://localhost:8081/api/users")

    .then()
    .statusCode(401);

}

@Test
    void testGetUsersWithEmptyJwt(){
    given()
    .header("Authorization","Bearer ")

    .when()
    .get("http://localhost:8081/api/users")

    .then()
    .statusCode(403);

}

@Test
void testGetUsersWithoutJwt() {

    given()

    .when()
        .get("http://localhost:8081/api/users")

    .then()
        .statusCode(403);
}

}