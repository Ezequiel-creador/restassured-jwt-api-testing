package com.proyect.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;


public class UserCrudApiTest {
    
private String getToken(String username, String password){
 return given()
     .contentType(ContentType.JSON)
     .body("""
        {"username":"%s",
        "password":"%s"
        }
             """.formatted(username, password))
    .when()
    .post("http://localhost:8081/api/auth/login")

    .then()
    .statusCode(200)
    .extract()
    .path("token");
}

@Test
void testGetUserByUsername(){
    String token = getToken("admin", "1234");
    given()
    .header("Authorization", "Bearer " +token)
    
    .when()
    .get("http://localhost:8081/api/users/admin")

    .then()
    .statusCode(200)
    .body("username",equalTo("admin"));

}

@Test
void testGetUserByInvalidUsername(){
    String token = getToken("admin", "1234");
    given()
    .header("Authorization", "Bearer " +token)
    
    .when()
    .get("http://localhost:8081/api/users/usuarioNoExiste")

    .then()
    .statusCode(404);
   }

   @Test
    void testCreateUser(){
    String token = getToken("admin", "1234");
    String username = "usuario" + System.currentTimeMillis();
    given()
    .header("Authorization", "Bearer " +token)
    .contentType(ContentType.JSON)
    .body("""
    {
        "username":"%s",
        "password":"1234"
    }
    """.formatted(username))

    .when()
    .post("http://localhost:8081/api/users/create")
    
    .then()
    .statusCode(200)
    .body("username", equalTo(username));
   }

   @Test
    void testCreateLoginAndGetUser(){
    String token = getToken("admin", "1234");
    String username = "usuario" + System.currentTimeMillis();
    given()
    .header("Authorization", "Bearer " +token)
    .contentType(ContentType.JSON)
    .body("""
    {
        "username":"%s",
        "password":"1234"
    }
    """.formatted(username))
    .when()
    .post("http://localhost:8081/api/users/create")
    
    .then()
    .statusCode(200)
    .body("username", equalTo(username));

    token = getToken(username, "1234");
    
    given()
    .header("Authorization", "Bearer " +token)
  
    .when()
    .get("http://localhost:8081/api/users/{username}",username)
    
    .then()
    .statusCode(200)
    .body("username", equalTo(username));
   }


    @Test
    void testCreateLoginAndUpdateUser(){
    String token = getToken("admin", "1234");
    String username = "nuevoUsuario" + System.currentTimeMillis();
    int id =
    given()
    .header("Authorization", "Bearer " +token)
    .contentType(ContentType.JSON)
    .body("""
    {
        "username":"%s",
        "password":"1234"
    }
    """.formatted(username))
    .when()
    .post("http://localhost:8081/api/users/create")
    
    .then()
    .statusCode(200)
    .body("username", equalTo(username))
    .extract()
    .path("id");

    token = getToken(username, "1234");
    
    given()
    .header("Authorization", "Bearer " +token)
  
    .when()
    .get("http://localhost:8081/api/users/{username}", username)
    
    .then()
    .statusCode(200)
    .body("username", equalTo(username));
   
    String usernameNew = "usuarioActualizado" + System.currentTimeMillis();
    given()
    .header("Authorization", "Bearer " +token)
    .contentType(ContentType.JSON)
    .body("""
    {
        "username":"%s",
        "password":"4321"
    }
    """.formatted(usernameNew))

   .when()
   .put("http://localhost:8081/api/users/update/{id}",id)

   .then()
   .statusCode(200);

    
    given()
   .header("Authorization","Bearer " + getToken(usernameNew, "4321"))

   .when()
   .get("http://localhost:8081/api/users/{username}", username)

   .then()
   .statusCode(404);
   
  }
  

   @Test
   void testCreateDeleteAndVerifyUserNotFound(){
    String token = getToken("admin", "1234");
    String username = "nuevoUsuario" + System.currentTimeMillis();
    int id=
    given()
    .header("Authorization", "Bearer " +token)
    .contentType(ContentType.JSON)
    .body("""
    {
        "username":"%s",
        "password":"1234"
    }
    """.formatted(username))
    .when()
    .post("http://localhost:8081/api/users/create")
    
    .then()
    .statusCode(200)
    .body("username", equalTo(username))
    .extract()
    .path("id");

    token= getToken(username, "1234");
    
    given()
    .header("Authorization", "Bearer " +token)
  
    .when()
    .delete("http://localhost:8081/api/users/delete/{id}",id)
    
    .then()
    .statusCode(200);

     given()
   .header("Authorization","Bearer " + getToken("admin", "1234"))

   .when()
   .get("http://localhost:8081/api/users/{username}", username)

   .then()
   .statusCode(404);
   }
   
   
}
