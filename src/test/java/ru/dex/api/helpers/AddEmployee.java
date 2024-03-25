package ru.dex.api.helpers;

import io.qameta.allure.Step;
import ru.dex.api.models.employee.Employee;
import ru.dex.api.models.employee.EmployeeModel;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;


public class AddEmployee {
    private final static String URL = "https://api-dev.k3s.dex-it.ru/admin/";

    @Step("Отправляю запрос на добавление сотрудника")
    public static EmployeeModel addEmployee(Employee employeeModel) {
        //  Specifications.installSpecification(Specifications.requestSpecImg(URL), Specifications.responseSpecOK200());

        return given()
                .baseUri(URL)
                .body(employeeModel)
                .when()
                .post("Employee/AddEmployee")
                .then()
                .log().all()
                .extract().as((Type) EmployeeModel.class);

    }


}
