package ru.dex.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.contents.Content;
import ru.dex.api.models.contents.ContentModel;
import ru.dex.api.models.contents.UpdateContent;
import ru.dex.api.spec.Specifications;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
@DisplayName("Контент")
public class ContentApiTest extends TestBase {
    @BeforeAll
    public static void setUp() {
        Specifications.installRequestSpecification(Specifications.requestSpec(URLContent));
    }

    String version = "1";

    // Эндпоинт /Content/GetWithViewMode
    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Интерфейс получения контента")
    void getWithViewModeCTAllTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());
        given()
                .log().all()
                .when()
                .get("v" + version + "/Content/GetWithViewMode")
                .then()
                .log().all();
    }

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Интерфейс получения контента со статусом Unknown")
    void getWithViewModeCTUnknownTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());
        given()
                .param("contentType", "Unknown")
                .log().all()
                .when()
                .get("v" + version + "/Content/GetWithViewMode")
                .then()
                .log().all();
    }

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Интерфейс получения контента со статусом Image")
    void getWithViewModeCTImageTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());
        given()
                .param("contentType", "Image")
                .log().all()
                .when()
                .get("v" + version + "/Content/GetWithViewMode")
                .then()
                .log().all();
    }

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Интерфейс получения контента со статусом Story")
    void getWithViewModeCTStoryTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());
        given()
                .param("contentType", "Story")
                .log().all()
                .when()
                .get("v" + version + "/Content/GetWithViewMode")
                .then()
                .log().all();
    }

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Интерфейс получения контента со статусом Banner")
    void getWithViewModeCTBannerTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());
        given()
                .param("contentType", "Banner")
                .log().all()
                .when()
                .get("v" + version + "/Content/GetWithViewMode")
                .then()
                .log().all();
    }

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Интерфейс получения контента со статусом InfoPage")
    void getWithViewModeCTInfoPageTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());
        given()
                .param("contentType", "InfoPage")
                .log().all()
                .when()
                .get("v" + version + "/Content/GetWithViewMode")
                .then()
                .log().all();
    }

    // Эндпоинт /Content/Create

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Добавление контента с contentType InfoPage")
    void createCTInfoPageTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());
        Content content = DataGenerator.getInfoPage();

        ContentModel contentModel = given()
                .body(content)
                .log().all()
                .when()
                .post("v" + version + "/Content/Create")
                .then()
                .log().all()
                .extract()
                .as(ContentModel.class);
        assertThat(contentModel.getId()).isNotEmpty();
        assertThat(contentModel.getName()).isNotEmpty();
        assertThat(contentModel.getContentStatus()).isNotEmpty();
        assertThat(contentModel.getContentType()).isNotEmpty();
    }

// Обновление контента

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление контента с contentType InfoPage")
    void updateCTInfoPageTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());
        UpdateContent updateContent = DataGenerator.updateContent();

        ContentModel contentModel = given()
                .pathParam("contentId", updateContent.getId())
                .body(updateContent)
                .log().all()
                .when()
                .put("v" + version + "/Content/Update/{contentId}")
                .then()
                .log().all()
                .extract()
                .as(ContentModel.class);
        assertThat(contentModel.getId()).isNotEmpty();
        assertThat(contentModel.getName()).isNotEmpty();
        assertThat(contentModel.getContentStatus()).isNotEmpty();
        assertThat(contentModel.getContentType()).isNotEmpty();
    }

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление статуса с contentType InfoPage на Hidden")
    void updateHiddenStatusCTInfoPageTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200WithoutJson());
        UpdateContent updateContent = DataGenerator.updateContent();

        given()
                .pathParam("contentId", updateContent.getId())
                .queryParam("contentStatus", "Hidden")
                .log().all()
                .when()
                .put("v" + version + "/Content/UpdateStatus/{contentId}")
                .then()
                .log().all();
    }

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление статуса с contentType InfoPage на Published")
    void updatePublishedCTInfoPageTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200WithoutJson());
        UpdateContent updateContent = DataGenerator.updateContent();

        given()
                .pathParam("contentId", updateContent.getId())
                .queryParam("contentStatus", "Published")
                .log().all()
                .when()
                .put("v" + version + "/Content/UpdateStatus/{contentId}")
                .then()
                .log().all();
    }

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление даты публикации контента")
    void updatePublishedDateTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200WithoutJson());
        UpdateContent updateContent = DataGenerator.updateContent();
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);
        System.out.println(date);
        given()
                .pathParam("contentId", updateContent.getId())
                .queryParam("publishDate", date)
                .log().all()
                .when()
                .put("v" + version + "/Content/UpdatePublishDate/{contentId}")
                .then()
                .log().all();
    }

    @Test
    @Feature("Контент")
    @Story("Контент")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление контента")
    void deleteContentTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200WithoutJson());
        UpdateContent updateContent = DataGenerator.updateContent();

        given()
                .pathParam("contentId", updateContent.getId())
                .log().all()
                .when()
                .delete("v" + version + "/Content/Delete/{contentId}")
                .then()
                .log().all();
    }


}
