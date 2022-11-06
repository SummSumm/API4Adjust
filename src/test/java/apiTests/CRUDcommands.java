package apiTests;

import Utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class CRUDcommands {


    @BeforeAll
    public static void init() {

        baseURI = ConfigurationReader.getProperty("URL");
    }

    // get all the available pets and the count
    @Test
    public void GET() {
        Response response = given().accept(ContentType.JSON).
                and().queryParam("status", "available").
                when().get("/pet/findByStatus");
        assertEquals(200, response.statusCode());

        List<Map<String, Object>> availablePets = response.body().as(List.class);
        System.out.println("Number of pets available = " + availablePets.size());

    }

    @Test
    public void POST() {

        String requestJsonBody = "{\"id\": 2,\n" +
                "  \"petId\": 0,\n" +
                "  \"quantity\": 0,\n" +
                "  \"shipDate\": \"2022-11-06T00:12:37.493Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true}";


        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON).
                body(requestJsonBody).when().post("/store/order");
        assertEquals(200, response.statusCode());
        response.prettyPrint();
    }
    @Test
    public void DELETE() {
        String requestJsonBody = "{\n" +
                "  \"code\": 200,\n" +
                "  \"type\": \"unknown\",\n" +
                "  \"message\": \"2\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON).
                body(requestJsonBody).pathParam("orderId", 2).
                when().delete("/store/order/{orderId}");
        assertEquals(200, response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void PUT() {
        String requestJsonBody = "{\n" +
                "  \"id\": 0,\n" +
                "  \"username\": \"string\",\n" +
                "  \"firstName\": \"Sumeyye\",\n" +
                "  \"lastName\": \"Aslan\",\n" +
                "  \"email\": \"smy@gmail.com\",\n" +
                "  \"password\": \"1234\",\n" +
                "  \"phone\": \"12345\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON).
                body(requestJsonBody).pathParam("username", "string").
                when().put("/user/{username}");
        assertEquals(200, response.statusCode());
        response.prettyPrint();
    }
}
