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
@DisplayName("Удаление пользователя - неправильный смс")
class DeleteUserInvalidSmsApiTest extends TestBase{
    String version = "1";
    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Удаление пользователя - неправильный смс")
    void deleteUserInvalidSmsTest() {
        Specifications.installSpecification(Specifications.requestSpecOnlyMobileNewUserToken(URLProfile), Specifications.responseSpecBadRequest400());
        DeleteUser deleteUser = DataGenerator.InvalidSmsCode();
        given()
                .body(deleteUser)
                .log().all()
                .when()
                .delete("v" + version + "/Register/DeleteMyself")
                .then()
                .log().all();
    }
}
