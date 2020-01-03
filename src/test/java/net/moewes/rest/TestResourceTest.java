package net.moewes.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

@QuarkusTest
class TestResourceTest {
/*
  @Test
  void sayHello() {
    given().when().get("/api/test/static").then().statusCode(200).body(is("Hello World"));
  }

  @Test
  void sayHelloCdi() {

    ValidatableResponse then = given().when().get("/api/test/cdi").then();

    then.statusCode(200).body(is("Hello from CDI"));
  }
  
 */
}
