package API_test.Day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class StudentPractice {

    @Test
    public void bookingTest1() {
        RestAssured.given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .prettyPrint();
    }

    @Test
    public void bookingTest2() {
        RestAssured.given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .prettyPeek();
    }

    @Test
    public void bookingTest3() {
        RestAssured.given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void bookingTest4() {
        RestAssured.given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void bookingTest5() {
        RestAssured.given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .assertThat().contentType(ContentType.JSON);
    }

    @Test
    public void bodyLogs() {
        RestAssured.given()
                .log().body()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void verifyTimeStamp() {
        Response response = RestAssured.given()
                .log()
                .all()
                .when()
                .post("https://api.octoperf.com/public/users/login?username=ak_som@hotmail.com&password=Som0430kenG")
                .then().log().all()
                .extract()
                .response();

        System.out.println(response.jsonPath().getString("expireInsec"));
        Assert.assertEquals(response.jsonPath().getString("expireInsec"), 86400);

    }

    // Task write a GET request to endpoint https://jsonplaceholder.typicode.com/posts
// Verify status code 200
// verify content type  Application json
// Verify response body contains title = "qui est esse" for id 2
// Array [1]

/**
 * [
 *   {
 *     "userId": 1,
 *     "id": 1,
 *     "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
 *     "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
 *   },
 *   {
 *     "userId": 1,
 *     "id": 2,
 *     "title": "qui est esse",
 *     "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
 *   }
 */

    @Test
    public void verifyTitle(){
        Response response = RestAssured.given()
                .log()
                .all()
                .when()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .log()
                .all()
                .assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .extract()
                .response();

        System.out.println(response.jsonPath().getString("[1].title"));
        Assert.assertEquals(response.jsonPath().getString("[1].title"),"qui est esse");
    }

}
