package ru.dex.api.helpers;

import io.qameta.allure.Step;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.push.Push;
import ru.dex.api.models.push.PushModel;

import static io.restassured.RestAssured.given;


public class AddPush {
    private final static String URL = "https://api-dev.k3s.dex-it.ru/admin/";

    @Step("Отправляю запрос на создание sms нотификации")
    public static PushModel addPush(Push PushModel) {
        Push createPush = DataGenerator.getPush();

        return given()
                .baseUri(URL)
                .body(createPush)
                .when()
                .post("/Notification/AddPush")
                .then()
                .log().all()
                .extract().as(PushModel.class);
    }
}
