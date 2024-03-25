package ru.dex.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.users.UpdateUser;
import ru.dex.api.spec.Specifications;

import static io.restassured.RestAssured.given;
@DisplayName("Изменение настроек текущего пользователя")
class UpdateUserProfileApiTest extends TestBase{
    String version = "1";
    // Обновление пользователя
    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Изменение настроек текущего пользователя")
    void updateUserTest() {
        Specifications.installSpecification(Specifications.requestSpecOnlyMobileNewUserToken(URLProfile), Specifications.responseSpecOK200WithoutJson());
        UpdateUser updateUser = DataGenerator.updateUser();
        given()
                .body(updateUser)
                .log().all()
                .when()
                .post("v" + version + "/UserProfile/Update")
                .then()
                .log().all();
    }
}
