package endpoint;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestTest {
@BeforeTest(alwaysRun = true)
@Parameters({"url"})
    public void setUp(String url) {
        baseURI = url;
    }

    @Test
    public void getAllUsers(){
        String body = given()
                .when()
                    .get("/users")
                .then()
                    .statusCode(200)
                .body("data[1].first_name",equalTo("Janet"))
                    .extract().body().asString();

        System.out.println("\n"+body+"\n");
    }

    @Test public void getUserByAndSpecifyId(){
        given()
                .when()
                    .get("/users/5")
                .then()
                    .statusCode(200);
    }

    @Test public void getUserByIdUsingTheResponse(){
       Response response = given()
                .when()
                    .get("/users")
                .then()
                    .statusCode(200)
                    .extract().response();

       Integer user_id = response.path("data[0].id");
        System.out.println("For the test getUserByIdUsingTheResponse");
       System.out.println("The number of ID is: " + user_id);
       Response response2 = given()
                .when()
                .   get("/users/{id}", user_id)
                .then()
                    .statusCode(200)
                .extract().response();
       String user_name = response2.path("data.first_name");
       System.out.println("The user name is: " + user_name);
    }

    @Test
    @Parameters({"name","job"})
    public void createUserUsingMap(String name, String job){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("job", job);

        given()
                .body(map.toString())
        .when()
                .post("/users")
        .then()
                .statusCode(201);
        System.out.printf("\nNew user Created using Map\nHis name is: %s and his job is %s \n", name, job);
    }

    @Test
    @Parameters({"name","job"})
        public void createUSerUsingJsonObject(String name, String job){
        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name);
        requestParams.put("Job", job);

        given()
            .body(requestParams.toString())
        .when()
            .post("/users")
        .then()
                .assertThat()
            .statusCode(201);

        System.out.printf("\nNew user Created using JSONObject\nHis name is: %s and his job is %s\n", name, job);

    }

    @Test
    @Parameters({"email","password"})
    public void registerUser(String email, String password){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);
        map.put("password", password);

        JsonPath jsonPath =  given()
                .accept(ContentType.JSON)
                .body(map.toString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
           .extract().jsonPath();;
        System.out.printf("\nNew user register using Map\nHis email is: %s and his password is %s\n", email, password);
        System.out.println("LA-AAA "+ jsonPath.get("id").toString());
    }

    @Test
    public void failedTest(){
        String body = given()
                .when()
                .get("/")
                .then()
                .statusCode(200)
                .body("data[1].first_name",equalTo("Janet"))
                .extract().body().asString();

        System.out.println("\n"+body+"\n");
    }
    @Test
    public void skipTest(){
        System.out.println("Test case - Skip exception example");
        throw new SkipException("Skipping this exception");
    }

    @Test(enabled = false)
    public void skipTest2(){
        System.out.println("Test case - Skip exception example");
    }
}
