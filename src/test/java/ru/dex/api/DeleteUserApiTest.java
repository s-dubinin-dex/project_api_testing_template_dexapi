package ru.dex.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.users.DeleteUser;
import ru.dex.api.spec.Specifications;

import static io.restassured.RestAssured.given;
@DisplayName("Удаление пользователя")
class DeleteUserApiTest extends TestBase{
    String version = "1";
    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление пользователя")
    void deleteUserTest() {
        Specifications.installSpecification(Specifications.requestSpecOnlyMobileNewUserToken(URLProfile), Specifications.responseSpecOK200WithoutJson());
        DeleteUser deleteUser = DataGenerator.SmsCode();
        given()
                .body(deleteUser)
                .log().all()
                .when()
                .delete("v" + version + "/Register/DeleteMyself")
                .then()
                .log().all();
    }
}
