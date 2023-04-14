package Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class parameter {
    @Test
    public void fullURLwithQueryParams(){
        RestAssured.given()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=ak_som@hotmail.com&password=Som0430kenG")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON);
    }

    @Test
    public void loginWithMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username","ak_som@hotmail.com");
        map.put("password", "Som0430kenG");

        RestAssured.given()
                .log()
                .all()
                .queryParams(map)
                .when()
                .post("https://api.octoperf.com/public/users/login")
                .then()
                .statusCode(200);

    }

    @Test
    public void logInwithQueryParams(){
        RestAssured.given()
                .log()
                .all()
                .queryParams("username", "ak_som@hotmail.com")
                .queryParams("password", "Som0430kenG")
                .when()
                .post("https://api.octoperf.com/public/users/login")
                .then()
                .statusCode(200);
    }
    @Test
    public void logInwithMultipleQueryParams() {
        RestAssured.baseURI = "https://api.octoperf.com";
        String path = "/public/users/login";
        RestAssured.given()
                .log()
                .all()
                .queryParams("username", "ak_som@hotmail.com", "password", "Som0430kenG")
                .when()
                .post("https://api.octoperf.com/public/users/login")
                .then()
                .statusCode(200);
    }
}
