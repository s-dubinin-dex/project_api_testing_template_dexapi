package ru.dex.api.helpers;

import io.qameta.allure.Step;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.users.RegisterUser;
import ru.dex.api.spec.Specifications;

import static io.restassured.RestAssured.given;

public class AddUser {
    protected final static String URLProfile = "https://api-dev.k3s.dex-it.ru/profile/";
    static String version = "1";

    @Step("Отправляю запрос на создание пользователя")
    public static RegisterUser addUser() {
        Specifications.installRequestSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile));
        RegisterUser registerUser = DataGenerator.createUser();

        given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser");
        return registerUser;
    }
}
