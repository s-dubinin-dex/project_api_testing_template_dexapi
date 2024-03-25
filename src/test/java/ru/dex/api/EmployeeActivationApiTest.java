package ru.dex.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.spec.Specifications;

import static io.restassured.RestAssured.given;
@DisplayName("Забыли пароль")
class EmployeeActivationApiTest extends TestBase{
    String version = "1";
    // функция приглашения отключена
    @Test
    @Disabled
    @Feature("Сотрудники")
    @Story("Сотрудники")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Функция забыли пароль")
    void employeeActivationTest() {
       Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200WithoutJson());

       given()
                .body("\"andrey.zavrichko@yandex.ru\"")
                .when()
                .post("v"+version+"EmployeeActivation/Forgot")
                .then()
                .log().all();
    }
}
