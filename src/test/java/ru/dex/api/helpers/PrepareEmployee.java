package ru.dex.api.helpers;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import ru.dex.api.models.employee.Employee;


public class PrepareEmployee {
    static Faker faker = new Faker();

    @Step("Подготавливаю нового сотрудника.")
    public static Employee prepareEmployee() {
        return Employee
                .builder()
                .roleId("d2ee530d-8384-439a-8486-3e960118084b")
                .name(faker.name().username())
                .email(faker.internet().emailAddress())
                .build();
    }
}
