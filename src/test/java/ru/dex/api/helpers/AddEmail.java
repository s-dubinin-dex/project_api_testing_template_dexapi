package ru.dex.api.helpers;

import io.qameta.allure.Step;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.email.Email;
import ru.dex.api.models.email.EmailModel;

import static io.restassured.RestAssured.given;

public class AddEmail {
    private final static String URL = "https://api-dev.k3s.dex-it.ru/admin/";

    @Step("Отправляю запрос на создание email нотификации")
    public static EmailModel addEmail(Email EmailModel) {
        Email createEmail = DataGenerator.getEmail();

        return given()
                .baseUri(URL)
                .body(createEmail)
                .when()
                .post("/Notification/AddEmail")
                .then()
                .log().all()
                .extract().as(EmailModel.class);
    }

}
