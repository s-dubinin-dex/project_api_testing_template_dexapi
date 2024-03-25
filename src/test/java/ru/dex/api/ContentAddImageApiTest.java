package ru.dex.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.spec.Specifications;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Добавление изображения jpg контента в хранилище")
class ContentAddImageApiTest extends TestBase {
    String version = "1";

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление изображения jpg контента в хранилище")
    void contentAddImageJpgTest() {
        Specifications.installSpecification(Specifications.requestSpecAdminImage(URLContent), Specifications.responseSpecOK200WithoutJson());
        File file = new File("src/test/resources/contents/content.jpg");

        given()
                .multiPart("formFile", file, "image/jpeg")
                .when()
                .log().all()
                .post("v" + version + "/Content/AddImage")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .body(notNullValue());
    }


}
