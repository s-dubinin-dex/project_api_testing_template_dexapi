package ru.dex.api.helpers;

import io.qameta.allure.Step;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.contents.Content;
import ru.dex.api.models.contents.ContentModel;
import ru.dex.api.models.email.Email;
import ru.dex.api.models.email.EmailModel;

import static io.restassured.RestAssured.given;

public class AddContent {
    static String version = "1";
    private final static String URL = "https://api-dev.k3s.dex-it.ru/content/";

    @Step("Отправляю запрос на создание контента")
    public static ContentModel addContent(Content ContentModel) {
        Content content = DataGenerator.getInfoPage();

        return given()
                .baseUri(URL)
                .body(content)
                .log().all()
                .when()
                .post("v" + version + "/Content/Create")
                .then()
                .log().all()
                .extract()
                .as(ContentModel.class);
    }
}
