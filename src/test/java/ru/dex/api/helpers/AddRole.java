package ru.dex.api.helpers;

import io.qameta.allure.Step;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.models.role.Role;
import ru.dex.api.models.role.RoleModel;

import static io.restassured.RestAssured.given;

public class AddRole {
    private final static String URL = "https://api-dev.k3s.dex-it.ru/admin/";

    @Step("Отправляю запрос на добавление роли")
    public static RoleModel addRole(Role roleModel) {
        //  Specifications.installSpecification(Specifications.requestSpecImg(URL), Specifications.responseSpecOK200());
        Role newRole = DataGenerator.getRole();
        return given()
                .baseUri(URL)
                .body(roleModel)
                .when()
                .post("/Role/AddRole")
                .then()
                .log().all()
                .extract().as(RoleModel.class);

    }


}
