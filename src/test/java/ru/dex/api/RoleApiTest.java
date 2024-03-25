package ru.dex.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.spec.Specifications;
import ru.dex.api.models.role.Role;
import ru.dex.api.models.role.RoleModel;
import ru.dex.api.models.role.UpdateRole;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static ru.dex.api.helpers.AddRole.addRole;
@DisplayName("Роли")
public class RoleApiTest extends TestBase{

    @BeforeAll
    public static void setUp() {
        Specifications.installRequestSpecification(Specifications.requestSpec(URL));
    }

    @Test
    @Feature("Роли")
    @Story("Роли")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание роли")
    void addRoleTest() {
       Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        Role newRole = DataGenerator.getRole();
        RoleModel roleModel = given()
                .body(newRole)
                .log().all()
                .when()
                .post("/Role/AddRole")
                .then()
                .log().all()
                .extract().as(RoleModel.class);
        assertThat(roleModel.getId()).isNotEmpty();
        assertThat(roleModel.getName()).isNotEmpty();
    }

    @Test
    @Feature("Роли")
    @Story("Роли")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание роли - негативный сценарий")
    void addRole400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .log().all()
                .when()
                .post("/Role/AddRole")
                .then()
                .log().all();
    }

    @Test
    @Feature("Роли")
    @Story("Роли")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Список доступных Policy для настройки ролей")
    void getPoliciesTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        given()
                .params("api-version", "1.0")
                .log().all()
                .when()
                .get("/Role/GetPolicies")
                .then()
                .log().all()
                .body("info.key", notNullValue())
                .body("info.category", notNullValue());
    }

    @Test
    @Feature("Роли")
    @Story("Роли")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Интерфейс запроса данных о ролях")
    void getRoleTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        given()
                .params("api-version", "1.0")
                .log().all()
                .when()
                .get("/odata/Role")
                .then()
                .log().all()
                .body("value.Id", notNullValue())
                .body("value.Name", notNullValue());
    }


    @Test
    @Feature("Роли")
    @Story("Роли")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление роли")
    void deleteRoleTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecDelete());

        Role role = DataGenerator.getRole();
        RoleModel newRole = addRole(role);

        given()
                .when()
                .log().all()
                .param("id", newRole.getId())
                .delete("Role/DeleteRole")
                .then()
                .log().all();
    }

    @Test
    @Feature("Роли")
    @Story("Роли")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление роли - негативный сценарий")
    void deleteRole400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .when()
                .log().all()
                .delete("Role/DeleteRole")
                .then()
                .log().all();
    }

    @Test
    @Feature("Роли")
    @Story("Роли")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменение роли")
    void updateRoleTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecDelete());

        UpdateRole updateRole = DataGenerator.updateRole();
        RoleModel roleModel = given()
                .when()
                .log().all()
                .body(updateRole)
                .put("Role/UpdateRole")
                .then()
                .log().all()
                .extract().as(RoleModel.class);
        assertThat(roleModel.getId()).isNotEmpty();
        Assertions.assertTrue(roleModel.getPolicies().contains("role.read"));
    }

    @Test
    @Feature("Роли")
    @Story("Роли")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Изменение роли - негативный сценарий")
    void updateRole400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .when()
                .log().all()
                .put("Role/UpdateRole")
                .then()
                .log().all();
    }
}
