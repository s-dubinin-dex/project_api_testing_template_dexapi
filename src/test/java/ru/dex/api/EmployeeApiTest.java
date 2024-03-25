package ru.dex.api;


import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.spec.Specifications;
import ru.dex.api.models.employee.Employee;
import ru.dex.api.models.employee.EmployeeModel;
import ru.dex.api.models.employee.InvitationModel;
import ru.dex.api.models.employee.UpdateEmployee;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static ru.dex.api.helpers.AddEmployee.addEmployee;
import static ru.dex.api.helpers.PrepareEmployee.prepareEmployee;
@DisplayName("Сотрудники")
public class EmployeeApiTest extends TestBase{
    @BeforeAll
    public static void setUp() {
        Specifications.installRequestSpecification(Specifications.requestSpec(URL));
    }

    @Test
    @Feature("Сотрудники")
    @Story("Сотрудники")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание нового сотрудника")
    void createNewEmployeeTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        Employee newEmployee = DataGenerator.getEmployee();

        EmployeeModel employeeModel = given()
                .body(newEmployee)
                .when()
                .post("Employee/AddEmployee")
                .then()
                .log().all()
              .extract().as(EmployeeModel.class);

        assertThat(employeeModel.getRoleId()).contains("d2ee530d-8384-439a-8486-3e960118084b");
        assertThat(employeeModel.getId()).isNotEmpty();
        assertThat(employeeModel.getName()).isNotEmpty();
    }

    @Test
    @Feature("Сотрудники")
    @Story("Сотрудники")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Создание нового сотрудника - негативный сценарий")
    void createNewEmployee400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .when()
                .post("Employee/AddEmployee")
                .then()
                .log().all();
    }


    @Test
    @Feature("Сотрудники")
    @Story("Сотрудники")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Получение списка сотрудников")
    void getEmployeeTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        given()
                .when()
                .get("odata/Employee")
                .then()
                .log().all()
                .body("value.Id", notNullValue())
                .body("value.Name", notNullValue())
                .body("value.Role", notNullValue())
                .body("value.Email", notNullValue());
    }

    @Test
    @Feature("Сотрудники")
    @Story("Сотрудники")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление сотрудников")
    void deleteEmployeeTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecDelete());

        Employee employee = prepareEmployee();
        EmployeeModel newEmployeeInStore = addEmployee(employee);

        given()
                .when()
                .log().all()
                .param("id", newEmployeeInStore.getId())
                .delete("Employee/DeleteEmployee")
                .then()
                .log().all();
    }

    @Test
    @Feature("Сотрудники")
    @Story("Сотрудники")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Удаление сотрудников - негативный сценарий")
    void deleteEmployee400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .when()
                .log().all()
                .delete("Employee/DeleteEmployee")
                .then()
                .log().all();
    }


    @Test
    @Feature("Сотрудники")
    @Story("Сотрудники")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление сотрудника")
    void updateEmployeeTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        UpdateEmployee updateEmployee = DataGenerator.updateEmployee();

        EmployeeModel employeeModel = given()
                .body(updateEmployee)
                .when()
                .put("Employee/UpdateEmployee")
                .then()
                .log().all()
                .extract().as(EmployeeModel.class);

        assertThat(employeeModel.getRoleId()).contains("d2ee530d-8384-439a-8486-3e960118084b");
        assertThat(employeeModel.getId()).isNotEmpty();
    }

    @Test
    @Feature("Сотрудники")
    @Story("Сотрудники")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Обновление сотрудника - негативный сценарий")
    void updateEmployee400Test() {
        Specifications.installResponseSpecification(Specifications.responseSpecBadRequest400());

        given()
                .when()
                .put("Employee/UpdateEmployee")
                .then()
                .log().all();
    }

    @Test
    @Feature("Сотрудники")
    @Story("Сотрудники")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Генерация нового приглашения с новым токеном активации")
    void updateInvitationTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());
        UpdateEmployee updateInvitation = DataGenerator.updateInvitation();
        InvitationModel invitationModel = given()
                .body("\""+updateInvitation.getId()+"\"")
                .when()
                .put("Employee/UpdateInvitation")
                .then()
                .log().all()
                .extract().as(InvitationModel.class);
        assertThat(invitationModel.getId()).contains(updateInvitation.getId());
        assertThat(invitationModel.getName()).isNotEmpty();
    }
}
