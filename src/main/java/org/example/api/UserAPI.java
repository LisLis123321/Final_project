package org.example.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.pojo.AdResponse;
import org.example.pojo.LoginUserRequest;
import org.example.pojo.RegisterUserRequest;
import static io.restassured.RestAssured.given;

public class UserAPI {
    private static final String BASE_URL = "https://qa-desk.education-services.ru";
    private static final String REGISTER_API = "/api/signup";
    private static final String LOGIN_API = "/api/signin";
    private static final String DELETE_AD = "/api/listings/";

    public Response registerUser(RegisterUserRequest request) {
        return given()
                .baseUri(BASE_URL)
                .log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .post(REGISTER_API)
                .then()
                .statusCode(201)
                .extract().response();
    }

    public Response registerUserNegative(RegisterUserRequest request) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(request)
                .post(REGISTER_API)
                .then()
                .extract().response();
    }

    public Response loginUserAndGetToken(LoginUserRequest request) {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(request)
                .post(LOGIN_API)
                .then()
                .statusCode(201)
                .extract().response();
    }

    public AdResponse createNewAd(String token, String name, String category, String condition, String city, String description, String price) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .log().all()
                .multiPart("condition", condition)
                .multiPart("city", city)
                .multiPart("price", price)
                .multiPart("name", name)
                .multiPart("description", description)
                .multiPart("category", category)
                .post("/api/create-listing")
                .then()
                .log().all()
                .statusCode(201)
                .extract()
                .as(AdResponse.class);
    }

    public Response deleteAd(String token, int adId) {
        return given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .log().all()
                .delete(DELETE_AD + adId)
                .then()
                .log().all()
                .statusCode(200)
                .extract().response();
    }
}