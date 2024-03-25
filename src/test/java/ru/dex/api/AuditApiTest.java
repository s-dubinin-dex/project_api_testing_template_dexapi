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
@DisplayName("Аудит")
public class AuditApiTest extends TestBase {
    @BeforeAll
    public static void setUp() {
        Specifications.installRequestSpecification(Specifications.requestSpec(URL));
    }

    @Test
    @Feature("Аудит")
    @Story("Аудит")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Журнал системы")
    void auditTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        given()
                .when()
                .log().all()
                .get("odata/Audit")
                .then()
                .log().all()
                .body("value.Id", notNullValue())
                .body("value.CreatedUtc", notNullValue())
                .body("value.UserId", notNullValue())
                .body("value.UserName", notNullValue())
                .body("value.ActionName", notNullValue())
                .body("value.TableName", notNullValue())
                .body("value.DataChanges", notNullValue())
                .body("value.EntityId", notNullValue())
                .body("value.EntityName", notNullValue());
    }
}
