import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class apiExamples {
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MDE5OTc2NjYsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTcwMjA0MDg2NiwidXNlcklkIjoiNjA5NiJ9.n35-LFUqsOUz79zBjdPu6D745l4QDSEviWmYBLEqKxs";

    @Test
    public void createAnEmployee(){
//prepare the request
        RequestSpecification request = given().header("Content-Type", "application/json").header("Authorization", token)
                .body("{\n" +
                        "  \"emp_firstname\": \"abra\",\n" +
                        "  \"emp_lastname\": \"ca\",\n" +
                        "  \"emp_middle_name\": \"dabra\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2023-12-01\",\n" +
                        "  \"emp_status\": \"unemployee\",\n" +
                        "  \"emp_job_title\": \"none\"\n" +
                        "}");

//    send the request to the endpoint
        Response response = request.when().post("/createEmployee.php");

//    response.prettyPrint();
//    extract the response in the form of string
        ResponseBodyExtractionOptions resp = response.then().extract().body();
        System.out.println(resp.asString());

//    extract the value of the key message
        String message = response.jsonPath().getString("Message");
//    assertion
        Assert.assertEquals(message,"Employee Created");

        // Asser that the created employee's firstName is abra

        String fname=response.jsonPath().get("Employee.emp_firstname");
        System.out.println(fname);


    }


}