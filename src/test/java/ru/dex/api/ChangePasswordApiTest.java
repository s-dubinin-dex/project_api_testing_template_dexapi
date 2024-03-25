package ru.dex.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.users.ChangePassword;
import ru.dex.api.spec.Specifications;

import static io.restassured.RestAssured.given;
@DisplayName("Смена пароля в активной сеансе")
class ChangePasswordApiTest extends TestBase{

    String version = "1";
    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Смена пароля - активная сессия")
    void changePasswordIActiveSessionTest() {
        Specifications.installSpecification(Specifications.requestSpecOnlyMobileToken(URLProfile), Specifications.responseSpecOK200WithoutJson());
        ChangePassword changePassword = DataGenerator.changePassActiveSession();
        given()
                .body(changePassword)
                .log().all()
                .when()
                .post("v" + version + "/Register/ChangePassword")
                .then()
                .log().all();
    }
}
