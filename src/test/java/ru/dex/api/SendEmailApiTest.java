package ru.dex.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.spec.Specifications;

import static io.restassured.RestAssured.given;
@DisplayName("Отправка письма для проверки email")
class SendEmailApiTest extends TestBase{
    String version = "1";
    // Повторно отправить письмо в текущей сессии

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Отправить письмо для проверки email в активной сессии")
    void sendEmailConfirmationTest() {
        Specifications.installSpecification(Specifications.requestSpecOnlyMobileNewUserToken(URLProfile), Specifications.responseSpecOK200WithoutJson());

        given()
                .log().all()
                .when()
                .post("v" + version + "/UserProfile/SendEmailConfirmation")
                .then()
                .log().all();
    }
}
