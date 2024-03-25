package ru.dex.api;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.dex.api.data.DataGenerator;
import ru.dex.api.spec.Specifications;
import ru.dex.api.models.reminder.Reminder;
import ru.dex.api.models.reminder.ReminderModel;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
@DisplayName("Напоминания")
public class ReminderApiTest extends TestBase{
    @BeforeAll
    public static void setUp() {
        Specifications.installRequestSpecification(Specifications.requestSpec(URL));
    }


    @Test
    @Feature("Напоминания")
    @Story("Напоминания")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Создать новое напоминание")
    void addReminderTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        Reminder newReminder = DataGenerator.getReminder();

        ReminderModel reminderModel = given()
                .body(newReminder)
                .log().all()
                .when()
                .post("/Reminder/AddReminder")
                .then()
                .log().all()
                .extract().as(ReminderModel.class);
        assertThat(reminderModel.getId()).isNotEmpty();
        assertThat(reminderModel.getTitle()).isNotEmpty();
        assertThat(reminderModel.getText()).isNotEmpty();
    }



    @Test
    @Feature("Напоминания")
    @Story("Напоминания")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Интерфейс запроса напоминаний")
    void getNotificationTest() {
        Specifications.installResponseSpecification(Specifications.responseSpecOK200());

        given()
                .params("includeDeleted", "true")
                .log().all()
                .when()
                .get("/odata/Reminder")
                .then()
                .log().all();
    }

}
