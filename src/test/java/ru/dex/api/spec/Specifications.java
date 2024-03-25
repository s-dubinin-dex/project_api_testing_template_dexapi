package ru.dex.api.spec;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import static io.restassured.http.ContentType.JSON;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static ru.dex.api.helpers.AddUser.addUser;
import static ru.dex.api.helpers.CustomAllureListener.withCustomTemplates;


public class Specifications {

    public static RequestSpecification requestSpecOnlyMobileToken(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "mobile.client")
                .formParam("client_secret", "D7927BE0-A841-414C-880E-206D08235B6D")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access mobile-api")
                .formParam("username", "77777777777")
                .formParam("password", "Passw0rd%")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(JSON)//---> Установка Content Type
                .setAccept(JSON)//---> Установка Accept
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                .build();
    }

    public static RequestSpecification requestSpecOnlyToken(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                .build();
    }


    public static RequestSpecification requestSpecWithoutToken(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();

        return new RequestSpecBuilder()
                .setBaseUri(url)
                //   .setRelaxedHTTPSValidation()//---> Отключение проверки сертификата
                //  .setContentType(ANY)//---> Установка Content Type
                // .setAccept(ANY)//---> Установка Accept
                // .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                .build();
    }

    public static RequestSpecification requestSpecWithoutTokenProfile(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();

        return new RequestSpecBuilder()
                .setBaseUri(url)
                //   .setRelaxedHTTPSValidation()//---> Отключение проверки сертификата
                .setContentType(JSON)//---> Установка Content Type
                .setAccept(JSON)//---> Установка Accept
                // .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                .build();
    }

    public static RequestSpecification requestSpec(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                //   .setRelaxedHTTPSValidation()//---> Отключение проверки сертификата
                .setContentType(JSON)//---> Установка Content Type
                .setAccept(JSON)//---> Установка Accept
                .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                .build();
    }

    public static RequestSpecification requestSpecImg(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                //   .setRelaxedHTTPSValidation()//---> Отключение проверки сертификата
                .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                //   .setContentType(TEXT)
                //  .setAccept(String.valueOf(MultipartBody.FORM))
                //    .setContentType(TEXT)
                .build();
    }

    public static RequestSpecification requestSpecAdminImage(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                //   .setRelaxedHTTPSValidation()//---> Отключение проверки сертификата
                // .setContentType(JSON)//---> Установка Content Type
                // .setAccept(JSON)//---> Установка Accept
                .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                .build();
    }

    public static RequestSpecification requestSpecWithout(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                // .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                //   .setRelaxedHTTPSValidation()//---> Отключение проверки сертификата
                // .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                //   .setContentType(JSON)

                // .setAccept(String.valueOf(MultipartBody.FORM))
                //  .setContentType(JSON)
                .build();
    }

    public static RequestSpecification requestSpecPost(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                //  .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                //   .setRelaxedHTTPSValidation()//---> Отключение проверки сертификата
                .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                //      .setContentType(JSON)
                //   .setAccept(String.valueOf(JSON))
                //     .setContentType(JSON)
                .build();
    }

    public static RequestSpecification requestSpecDelete(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "admin.client")
                .formParam("client_secret", "9F45EA47-9BD6-48D8-B218-273A256DB093")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access admin-api policy")
                .formParam("username", "test@gmail.com")
                .formParam("password", "005")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();
        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                //   .setRelaxedHTTPSValidation()//---> Отключение проверки сертификата
                .addFilter(withCustomTemplates()) //---> Добавление красивого шаблона для отчетов
                //      .setContentType(JSON)
                // .setAccept(String.valueOf(JSON))
                .setContentType(JSON)
                .build();
    }

    public static ResponseSpecification responseSpecContinue100() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_CONTINUE)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecSwitchingProtocols101() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_SWITCHING_PROTOCOLS)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecProcessing102() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_PROCESSING)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecOK200() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_OK)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecOK200Sec5() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_OK)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(5L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecOK200WithoutJson() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectStatusCode(HttpStatus.SC_OK)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }


    public static ResponseSpecification responseSpecDelete() {
        return new ResponseSpecBuilder()
                // .log(LogDetail.STATUS)//---> Уровень логирования
                // .expectContentType(JSON)//---> Ожидаемый Content Type
                // .expectStatusCode(HttpStatus.SC_OK)//---> Ожидаемый Status Code
                // .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecUpdate() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                //    .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_OK)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecCreated201() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_CREATED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecAccepted202() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_ACCEPTED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecNonAuthoritativeInformation203() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecNoContent204() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_NO_CONTENT)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecResetContent205() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_RESET_CONTENT)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecPartialContent206() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_PARTIAL_CONTENT)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecMultiStatus207() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_MULTI_STATUS)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecMultipleChoices300() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_MULTIPLE_CHOICES)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecMovedPermanently301() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_MOVED_PERMANENTLY)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecMovedTemporarily302() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_MOVED_TEMPORARILY)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecSeeOther303() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_SEE_OTHER)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecNotModified304() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_NOT_MODIFIED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecUseProxy305() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_USE_PROXY)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecTemporaryRedirect307() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_TEMPORARY_REDIRECT)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecBadRequest400() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_BAD_REQUEST)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecUnauthorized401() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_UNAUTHORIZED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecPaymentRequired402() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_PAYMENT_REQUIRED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecForbidden403() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_FORBIDDEN)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecNotFound404() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecMethodNotAllowedFound405() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecNotAcceptable406() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_NOT_ACCEPTABLE)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecProxyAuthenticationRequired407() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecRequestTimeout408() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_REQUEST_TIMEOUT)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecConflict409() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_CONFLICT)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecGone410() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_GONE)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecLengthRequired411() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_LENGTH_REQUIRED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecPreconditionFailed412() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_PRECONDITION_FAILED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecRequestTooLong413() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_REQUEST_TOO_LONG)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecUriTooLong414() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_REQUEST_URI_TOO_LONG)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecUnsupportedMediaType415() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecRequestedRangeNotSatisfiable416() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecExpectationFailed417() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_EXPECTATION_FAILED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecInsufficientSpaceOnResource419() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecMethodFailure420() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_METHOD_FAILURE)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecUnprocessableEntity422() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecLocked423() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_LOCKED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecFailedDependency424() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_FAILED_DEPENDENCY)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecInternalServerError500() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecNotImplemented501() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_NOT_IMPLEMENTED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecBadGateway502() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_BAD_GATEWAY)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecServiceUnavailable503() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_SERVICE_UNAVAILABLE)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecGatewayTimeout504() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_GATEWAY_TIMEOUT)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecHttpVersionNotSupported505() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_HTTP_VERSION_NOT_SUPPORTED)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpecInsufficientStorage507() {
        return new ResponseSpecBuilder()
                .log(LogDetail.STATUS)//---> Уровень логирования
                .expectContentType(JSON)//---> Ожидаемый Content Type
                .expectStatusCode(HttpStatus.SC_INSUFFICIENT_STORAGE)//---> Ожидаемый Status Code
                .expectResponseTime(lessThanOrEqualTo(3L), SECONDS)//---> Ожидаемое время ответа максимум 3 секунды
                .build();
    }

    public static ResponseSpecification responseSpec(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)
                .build();
    }

    public static void installSpecification(RequestSpecification requestSpec, ResponseSpecification responseSpec) {
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    public static void installRequestSpecification(RequestSpecification requestSpec) {
        RestAssured.requestSpecification = requestSpec;
    }

    public static void installResponseSpecification(ResponseSpecification responseSpec) {
        RestAssured.responseSpecification = responseSpec;
    }


// Генерирование токена нового пользователя

    public static RequestSpecification requestSpecOnlyMobileNewUserToken(String url) {
        RestAssured.baseURI = "https://api-dev.k3s.dex-it.ru/identity/";
        RequestSpecification request = RestAssured.given();
        Response responseFromGenerateToken = request
                .formParam("client_id", "mobile.client")
                .formParam("client_secret", "D7927BE0-A841-414C-880E-206D08235B6D")
                .formParam("grant_type", "password")
                .formParam("scope", "openid profile offline_access mobile-api")
                .formParam("username", addUser().phone)
                .formParam("password", "Passw0rd%")
                .post("connect/token");

        responseFromGenerateToken.prettyPrint();

        String jsonString = responseFromGenerateToken.getBody().asString();
        String tokenGenerated = JsonPath.from(jsonString).get("access_token");
        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(JSON)//---> Установка Content Type
                .setAccept(JSON)//---> Установка Accept
                .addHeader("Authorization", "Bearer " + tokenGenerated) // ---> Добавление токена в заголовок
                .build();
    }


}
