package ru.dex.api.helpers;

import io.qameta.allure.Step;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.sms.Sms;
import ru.dex.api.models.sms.SmsModel;

import static io.restassured.RestAssured.given;

public class AddSms {
    private final static String URL = "https://api-dev.k3s.dex-it.ru/admin/";

    @Step("Отправляю запрос на создание sms нотификации")
    public static SmsModel addSms(Sms SmsModel) {
        Sms createSms = DataGenerator.getSms();

        return given()
                .baseUri(URL)
                .body(createSms)
                .when()
                .post("/Notification/AddSms")
                .then()
                .log().all()
                .extract().as(SmsModel.class);
    }
}
