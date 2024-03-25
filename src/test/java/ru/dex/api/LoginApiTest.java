package ru.dex.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.models.users.RegisterUser;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static ru.dex.api.helpers.AddUser.addUser;

@DisplayName("Авторизация")
class LoginApiTest {
    private final static String IdentityUrl = "https://api-dev.k3s.dex-it.ru/identity/";

    @Test
    @Feature("Авторизация")
    @Story("Вход в систему")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение токена авторизации(позитивный сценарий)")
    void loginPositiveTest() {
        Response response = given()
                .contentType(ContentType.URLENC)
                .baseUri(IdentityUrl)
                .when()
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("token_type", is("Bearer"))
                .log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.get("access_token");
        System.out.println(token);
    }

    @Test
    @Feature("Авторизация")
    @Story("Вход в систему")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение токена авторизации(негативный сценарий)")
    void loginNegativeTest() {
        given()
                .contentType(ContentType.URLENC)
                .baseUri(IdentityUrl)
                .when()
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "invalidUserName")
                .formParam("password", "5647")
                .post("connect/token")
                .then()
                .statusCode(400)
                .contentType(JSON)
                .body("error", is("invalid_grant"))
                .log().all();

    }


    // Получение мобильного токена


    @Test
    @Feature("Авторизация")
    @Story("Вход в систему")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение токена авторизации новым пользователем(позитивный сценарий)")
    void loginMobilePositiveTest() {
        //  RegisterUser registerUser = DataGenerator.createUser();
        RegisterUser newUser = addUser();

        Response response = given()
                .contentType(ContentType.URLENC)
                .baseUri(IdentityUrl)
                .when()
                .formParam("client_id", "mobile.client")
                .formParam("client_secret", "D7927BE0-A841-414C-880E-206D08235B6D")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access mobile-api")
                .formParam("username", newUser.phone)
                .formParam("password", newUser.password)
                .post("connect/token")
                .then()
                .statusCode(200)
                .contentType(JSON)
                .body("token_type", is("Bearer"))
                .log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String token = jsonPath.get("access_token");
        System.out.println(token);
    }


}