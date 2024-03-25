package ru.dex.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.email.Email;
import ru.dex.api.models.email.EmailModel;
import ru.dex.api.models.email.UpdateEmail;
import ru.dex.api.models.push.Push;
import ru.dex.api.models.push.PushModel;
import ru.dex.api.models.push.UpdatePush;
import ru.dex.api.models.sms.Sms;
import ru.dex.api.models.sms.SmsModel;
import ru.dex.api.models.sms.UpdateSms;
import ru.dex.api.spec.Specifications;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static ru.dex.api.helpers.AddEmail.addEmail;
import static ru.dex.api.helpers.AddPush.addPush;
import static ru.dex.api.helpers.AddSms.addSms;

@DisplayName("Нотификации")
public class NotificationApiTest extends TestBase {

    @BeforeAll
    public static void setUp() {
        Specifications.installRequestSpecification(Specifications.requestSpec(URL));
    }

    @Test
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Интерфейс запроса уведомлений")
    void getNotificationTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        given()
                .params("$skip", "0")
                .params("$top", "10")
                .params("$count", "true")
                .log().all()
                .when()
                .get("/odata/Notification")
                .then()
                .log().all()
                .body("value.Id", notNullValue())
                .body("value.Type", notNullValue())
                .body("value.UserTopicCondition", notNullValue())
                .body("value.LinkValueUrl", notNullValue())
                .body("value.TypeLink", notNullValue());
    }

    @Test
    @Disabled
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание push уведомления")
    void addPushTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        Push newPush = DataGenerator.getPush();
        PushModel pushModel = given()
                .body(newPush)
                .log().all()
                .when()
                .post("/Notification/AddPush")
                .then()
                .log().all()
                .extract().as(PushModel.class);
        assertThat(pushModel.getId()).isNotEmpty();
        assertThat(pushModel.getTypeLink()).isNotEmpty();
    }

    @Test
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание push уведомления - негативный сценарий")
    void addPush400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .log().all()
                .when()
                .post("/Notification/AddPush")
                .then()
                .log().all();
    }

    @Test
    @Disabled
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание email уведомления")
    void addEmailTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        Email newEmail = DataGenerator.getEmail();

        EmailModel emailModel = given()
                .body(newEmail)
                .log().all()
                .when()
                .post("/Notification/AddEmail")
                .then()
                .log().all()
                .extract().as(EmailModel.class);
        assertThat(emailModel.getId()).isNotEmpty();
        assertThat(emailModel.getHeading()).isNotEmpty();
    }

    @Test
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание email уведомления - негативный сценарий")
    void addEmail400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .log().all()
                .when()
                .post("/Notification/AddEmail")
                .then()
                .log().all();
    }

    @Test
    @Disabled
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание смс уведомления")
    void addSmsTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        Sms newSms = DataGenerator.getSms();

        SmsModel smsModel = given()
                .body(newSms)
                .log().all()
                .when()
                .post("/Notification/AddSms")
                .then()
                .log().all()
                .extract().as(SmsModel.class);
        assertThat(smsModel.getId()).isNotEmpty();
        assertThat(smsModel.getMessage()).isNotEmpty();
    }

    @Test
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание смс уведомления - негативный сценарий")
    void addSms400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .log().all()
                .when()
                .post("/Notification/AddSms")
                .then()
                .log().all();
    }

    @Test
    @Disabled
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление уведомления email")
    void deleteEmailNotificationTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecDelete());

        Email email = DataGenerator.getEmail();
        EmailModel newEmail = addEmail(email);

        given()
                .when()
                .log().all()
                .param("id", newEmail.getId())
                .delete("Notification/DeleteNotification")
                .then()
                .log().all();
    }

    @Test
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление уведомления email - негативный сценарий")
    void deleteEmailNotification400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecDelete());

        given()
                .when()
                .log().all()
                .delete("Notification/DeleteNotification")
                .then()
                .log().all();
    }

    @Test
    @Disabled
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление уведомления sms")
    void deleteSmsNotificationTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecDelete());

        Sms sms = DataGenerator.getSms();
        SmsModel newSms = addSms(sms);

        given()
                .when()
                .log().all()
                .param("id", newSms.getId())
                .delete("Notification/DeleteNotification")
                .then()
                .log().all();
    }

    @Test
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление уведомления sms - негативный сценарий")
    void deleteSmsNotification400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .when()
                .log().all()
                .delete("Notification/DeleteNotification")
                .then()
                .log().all();
    }

    @Test
    @Disabled
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление уведомления push")
    void deletePushNotificationTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecDelete());

        Push push = DataGenerator.getPush();
        PushModel newPush = addPush(push);

        given()
                .when()
                .log().all()
                .param("id", newPush.getId())
                .delete("Notification/DeleteNotification")
                .then()
                .log().all();
    }

    @Test
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление уведомления push - негативный сценарий")
    void deletePushNotification400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .when()
                .log().all()
                .delete("Notification/DeleteNotification")
                .then()
                .log().all();
    }


    @Test
    @Disabled
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление email уведомления")
    void updateEmailTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecUpdate());

        UpdateEmail updateEmail = DataGenerator.updateEmail();
        given()
                .contentType("application/json")
                .queryParam("id", updateEmail.getId())
                .body(updateEmail)
                .log().all()
                .when()
                .put("/Notification/UpdateEmail")
                .then()
                .log().all();
    }

    @Test
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление email уведомления - негативный сценарий")
    void updateEmail400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .contentType("application/json")
                .log().all()
                .when()
                .put("/Notification/UpdateEmail")
                .then()
                .log().all();
    }

    @Test
    @Disabled
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление sms уведомления")
    void updateSmsTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecUpdate());

        UpdateSms updateSms = DataGenerator.updateSms();
        given()
                .contentType("application/json")
                .queryParam("id", updateSms.getId())
                .body(updateSms)
                .log().all()
                .when()
                .put("/Notification/UpdateSms")
                .then()
                .log().all();
    }

    @Test
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление sms уведомления - негативный сценарий")
    void updateSms400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .contentType("application/json")
                .log().all()
                .when()
                .put("/Notification/UpdateSms")
                .then()
                .log().all();
    }

    @Test
    @Disabled
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление push уведомления")
    void updatePushTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecUpdate());

        UpdatePush updatePush = DataGenerator.updatePush();
        given()
                .contentType("application/json")
                .queryParam("id", updatePush.getId())
                .body(updatePush)
                .log().all()
                .when()
                .put("/Notification/UpdatePush")
                .then()
                .log().all();
    }

    @Test
    @Feature("Уведомления")
    @Story("Уведомления")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление push уведомления - негативный сценарий")
    void updatePush400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .contentType("application/json")
                .log().all()
                .when()
                .put("/Notification/UpdatePush")
                .then()
                .log().all();
    }
}
