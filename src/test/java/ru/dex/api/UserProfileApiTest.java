package ru.dex.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.spec.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
@DisplayName("Получить настройки пользователя текущей сессии")
class UserProfileApiTest extends TestBase{
    String version = "1";
    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Получить настройки пользователя")
    void userProfileTest() {
        Specifications.installSpecification(Specifications.requestSpecOnlyMobileNewUserToken(URLProfile), Specifications.responseSpecOK200());

        given()
                .log().all()
                .when()
                .get("v" + version + "/UserProfile/Get")
                .then()
                .log().all()
                .body("result.id", notNullValue())
                .body("result.phone", notNullValue());
    }
}
