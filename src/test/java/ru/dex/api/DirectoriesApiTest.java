package ru.dex.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.spec.Specifications;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
@DisplayName("Справочники")
public class DirectoriesApiTest extends TestBase {
    @BeforeAll
    public static void setUp() {
        Specifications.installRequestSpecification(Specifications.requestSpec(URL));
    }

    String version = "1";

    @Test
    @Feature("Директории")
    @Story("Директории")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Интерфейс запроса городов")
    void getCityTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        given()
                .params("$skip", "0")
                .params("$top", "10")
                .params("$count", "true")
                .log().all()
                .when()
                .get("/odata/City")
                .then()
                .log().all()
                .body("value.Id", notNullValue())
                .body("value.NameRu", notNullValue())
                .body("value.NameEn", notNullValue())
                .body("value.OrderNum", notNullValue())
                .body("value.CreatedUtc", notNullValue());
    }

    @Test
    @Feature("Директории")
    @Story("Директории")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Интерфейс запроса справочника deep links")
    void getDeepLinksTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        given()
                .params("api-version", "Directories")
                .log().all()
                .when()
                .get("v" + version + "/DeepLink/GetDeepLinks")
                .then()
                .log().all()
                .body("result.items.link", notNullValue())
                .body("result.items.name", notNullValue());
    }
}
