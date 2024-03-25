package ru.dex.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.users.ChangePassword;
import ru.dex.api.models.users.RegisterUser;
import ru.dex.api.models.users.RegisterUserErrorModel;
import ru.dex.api.spec.Specifications;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Профиль")
class ProfileApiTest extends TestBase {
    String version = "1";


    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация с валидными данными")
    void registerUserValidTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecOK200WithoutJson());
        RegisterUser registerUser = DataGenerator.createUser();
        given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация без номера")
    void registerUserWithoutPhoneTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecBadRequest400());
        RegisterUser registerUser = DataGenerator.createUserWithoutPhone();
        RegisterUserErrorModel registerUserErrorModel = given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser")
                .then()
                .log().all()
                .extract().as(RegisterUserErrorModel.class);
        assertThat(registerUserErrorModel.getTitle()).isEqualTo("Request Validation Error");
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация без пароля")
    void registerUserWithoutPasswordTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecBadRequest400());
        RegisterUser registerUser = DataGenerator.createUserWithoutPassword();
        RegisterUserErrorModel registerUserErrorModel = given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser")
                .then()
                .log().all()
                .extract().as(RegisterUserErrorModel.class);
        assertThat(registerUserErrorModel.getTitle()).isEqualTo("Request Validation Error");
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация без смс кода")
    void registerUserWithoutSmsTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecBadRequest400());
        RegisterUser registerUser = DataGenerator.createUserWithoutSmsCode();
        RegisterUserErrorModel registerUserErrorModel = given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser")
                .then()
                .log().all()
                .extract().as(RegisterUserErrorModel.class);
        assertThat(registerUserErrorModel.getTitle()).isEqualTo("Request Validation Error");
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация без email")
    void registerUserWithoutEmailTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecOK200WithoutJson());
        RegisterUser registerUser = DataGenerator.createUserWithoutEmail();
        given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация без имени")
    void registerUserWithoutFirstNameTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecOK200WithoutJson());
        RegisterUser registerUser = DataGenerator.createUserWithoutFirstName();
        given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация без фамилии")
    void registerUserWithoutMiddleNameTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecOK200WithoutJson());
        RegisterUser registerUser = DataGenerator.createUserWithoutMiddleName();
        given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация без отчества")
    void registerUserWithoutLastNameTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecOK200WithoutJson());
        RegisterUser registerUser = DataGenerator.createUserWithoutLastName();
        given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация без даты рождения")
    void registerUserWithoutBirthdayTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecOK200WithoutJson());
        RegisterUser registerUser = DataGenerator.createUserWithoutBirthday();
        given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Регистрация без всех полей")
    void registerUserWithoutAllTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecBadRequest400());
        RegisterUser registerUser = DataGenerator.createUserWithoutAll();
        RegisterUserErrorModel registerUserErrorModel = given()
                .body(registerUser)
                .log().all()
                .when()
                .post("v" + version + "/Register/RegisterUser")
                .then()
                .log().all()
                .extract().as(RegisterUserErrorModel.class);
        assertThat(registerUserErrorModel.getTitle()).isEqualTo("Request Validation Error");
    }


    // Смс коды
    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка смс кода при регистрации")
    void checkSmsCodeRegTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecOK200WithoutJson());

        given()
                .param("SmsCode", "0000")
                .param("SmsCodeReason", "Registration")
                .param("Phone", "77777777777")
                .log().all()
                .when()
                .get("v" + version + "/Register/CheckSmsCode")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка смс кода при восстановлении пароля")
    void checkSmsCodeResetPassTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecOK200WithoutJson());

        given()
                .param("SmsCode", "0000")
                .param("SmsCodeReason", "ResetPassword")
                .param("Phone", "77777777777")
                .log().all()
                .when()
                .get("v" + version + "/Register/CheckSmsCode")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка смс кода при удалении аккаунта")
    void checkSmsCodeDeleteAccountTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecOK200WithoutJson());

        given()
                .param("SmsCode", "0000")
                .param("SmsCodeReason", "DeleteAccount")
                .param("Phone", "77777777777")
                .log().all()
                .when()
                .get("v" + version + "/Register/CheckSmsCode")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка смс кода с причиной None")
    void checkSmsCodeNoneTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecBadRequest400());

        given()
                .param("SmsCode", "0000")
                .param("SmsCodeReason", "None")
                .param("Phone", "77777777777")
                .log().all()
                .when()
                .get("v" + version + "/Register/CheckSmsCode")
                .then()
                .log().all()
                .body("title", notNullValue());
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка смс кода с неправильным кодом")
    void checkSmsCodeInvalidCodeTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecBadRequest400());

        given()
                .param("SmsCode", "00000")
                .param("SmsCodeReason", "None")
                .param("Phone", "77777777777")
                .log().all()
                .when()
                .get("v" + version + "/Register/CheckSmsCode")
                .then()
                .log().all()
                .body("errors.smsCode.errorCode", contains("validations.must be only 4 digit"));
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка смс кода с неправильным номером телефона")
    void checkSmsCodeInvalidPhoneTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecBadRequest400());

        given()
                .param("SmsCode", "0000")
                .param("SmsCodeReason", "Registration")
                .param("Phone", "777777777778")
                .log().all()
                .when()
                .get("v" + version + "/Register/CheckSmsCode")
                .then()
                .log().all()
                .body("errors.phone.errorCode", contains("validations.InvalidPhoneRus"));
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Смена пароля")
    void changePasswordTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecOK200WithoutJson());
        ChangePassword changePassword = DataGenerator.changePassword();
        given()
                .body(changePassword)
                .log().all()
                .when()
                .post("v" + version + "/Register/ChangePasswordByPhone")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Смена пароля - невалидный номер")
    void changePasswordInvalidPhoneTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecNotFound404());
        ChangePassword changePassword = DataGenerator.changePassInvalidPhone();
        given()
                .body(changePassword)
                .log().all()
                .when()
                .post("v" + version + "/Register/ChangePasswordByPhone")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Смена пароля - невалидный пароль")
    void changePasswordInvalidPassTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecBadRequest400());
        ChangePassword changePassword = DataGenerator.changePassInvalidPassword();
        given()
                .body(changePassword)
                .log().all()
                .when()
                .post("v" + version + "/Register/ChangePasswordByPhone")
                .then()
                .log().all();
    }

    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Смена пароля - невалидный смс")
    void changePasswordInvalidSmsTest() {
        Specifications.installSpecification(Specifications.requestSpecWithoutTokenProfile(URLProfile), Specifications.responseSpecBadRequest400());
        ChangePassword changePassword = DataGenerator.changePassInvalidSms();
        given()
                .body(changePassword)
                .log().all()
                .when()
                .post("v" + version + "/Register/ChangePasswordByPhone")
                .then()
                .log().all();
    }


    @Test
    @Feature("Регистрация")
    @Story("Профиль")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Смена пароля - активная сессия - неправильный пароль")
    void changePasswordIActiveSessionInvalidPassTest() {
        Specifications.installSpecification(Specifications.requestSpecOnlyMobileToken(URLProfile), Specifications.responseSpecBadRequest400());
        ChangePassword changePassword = DataGenerator.changePassActiveSessionInvalidPass();
        given()
                .body(changePassword)
                .log().all()
                .when()
                .post("v" + version + "/Register/ChangePassword")
                .then()
                .log().all();
    }


}