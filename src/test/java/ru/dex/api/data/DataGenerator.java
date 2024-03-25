package ru.dex.api.data;

import com.github.javafaker.Faker;
import ru.dex.api.models.banners.CreateBanner;
import ru.dex.api.models.contents.Content;
import ru.dex.api.models.contents.ContentModel;
import ru.dex.api.models.contents.UpdateContent;
import ru.dex.api.models.email.Email;
import ru.dex.api.models.email.EmailModel;
import ru.dex.api.models.email.UpdateEmail;
import ru.dex.api.models.employee.Employee;
import ru.dex.api.models.employee.EmployeeModel;
import ru.dex.api.models.employee.UpdateEmployee;
import ru.dex.api.models.push.Push;
import ru.dex.api.models.push.PushModel;
import ru.dex.api.models.push.UpdatePush;
import ru.dex.api.models.reminder.Reminder;
import ru.dex.api.models.role.Role;
import ru.dex.api.models.role.RoleModel;
import ru.dex.api.models.role.UpdateRole;
import ru.dex.api.models.sms.Sms;
import ru.dex.api.models.sms.SmsModel;
import ru.dex.api.models.sms.UpdateSms;
import ru.dex.api.models.users.ChangePassword;
import ru.dex.api.models.users.DeleteUser;
import ru.dex.api.models.users.RegisterUser;
import ru.dex.api.models.users.UpdateUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import static ru.dex.api.helpers.AddContent.addContent;
import static ru.dex.api.helpers.AddEmail.addEmail;
import static ru.dex.api.helpers.AddEmployee.addEmployee;
import static ru.dex.api.helpers.AddPush.addPush;
import static ru.dex.api.helpers.AddRole.addRole;
import static ru.dex.api.helpers.AddSms.addSms;



public class DataGenerator {
    // Справочники для создания контента
    public enum contentType {Unknown, Image, Story, Banner, InfoPage}

    public enum contentStatus {Unknown, Published, Hidden}


    public static Employee getEmployee() {
        Faker faker = new Faker();
        return Employee.builder()
                .roleId("d2ee530d-8384-439a-8486-3e960118084b")
                .name(faker.name().username())
                .email(faker.internet().emailAddress())
                .build();
    }

    public static UpdateEmployee updateEmployee() {
        Faker faker = new Faker();
        Employee employee = getEmployee();
        EmployeeModel newEmployee = addEmployee(employee);
        return UpdateEmployee.builder()
                .roleId("d2ee530d-8384-439a-8486-3e960118084b")
                .name(faker.name().username())
                .id(newEmployee.getId())
                .build();
    }


    public static UpdateEmployee updateInvitation() {
        Employee employee = getEmployee();
        EmployeeModel newEmployee = addEmployee(employee);
        return UpdateEmployee.builder()
                .id(newEmployee.getId())
                .build();
    }

    public static CreateBanner getBanner() {
        Faker faker = new Faker();
        // Получаем дату в строгом формате (другие форматы не поддерживаются, поэтому обрезаем всё после 23 символа полной даты)
        String dateStart = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);
        // Устанавливаем дату +5 дней
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 5);
        String dateEnd = dateFormat.format(c.getTime()).substring(0, 23);

        return CreateBanner.builder()
                .clubId("d9b44127-890e-42a0-97ec-978060ccda98")
                .endPublish(dateEnd)
                .imageUri("https://s3.dex-it.ru/public/winline/banner.images/5b3dd000-b47f-484c-bc50-91c28152efb4.png")
                .locationId("a3865b31-1769-470e-99ba-f6cea3128f2c")
                .name(faker.name().username())
                .startPublish(dateStart)
                .transitionUri("https://winline.ru/")
                .build();
    }


    public static Role getRole() {
        Faker faker = new Faker();
        return Role.builder()
                .name(faker.name().username())
                .policies(Collections.singletonList("user.read"))
                .build();
    }

    public static UpdateRole updateRole() {
        Faker faker = new Faker();
        Role role = getRole();
        RoleModel newRole = addRole(role);

        return UpdateRole.builder()
                .name(faker.name().username())
                .policies(Collections.singletonList("role.read"))
                .id(newRole.getId())
                .build();
    }


    public static Push getPush() {
        Faker faker = new Faker();
        // Получаем дату в строгом формате (другие форматы не поддерживаются, поэтому обрезаем всё после 23 символа полной даты)
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);

        System.out.println(date);
        return Push.builder()
                .sendTime(date)
                .heading(faker.name().username())
                .message(faker.name().username())
                .type("Push")
                .typeLink("HyperLink")
                .linkValueUrl("https://example.com/")
                .userList(new ArrayList<String>(Collections.singleton("3fa85f64-5717-4562-b3fc-2c963f66afa6")))
                .build();
    }

    // Создание email

    public static Email getEmail() {
        Faker faker = new Faker();
        // Получаем дату в строгом формате (другие форматы не поддерживаются, поэтому обрезаем всё после 23 символа полной даты)
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);
        Email.UserFilter userFilter = new Email.UserFilter();
        userFilter.citiesList = new ArrayList<>(Collections.singleton("0c5b2444-70a0-4932-980c-b4dc0d3f02b5"));
        System.out.println(date);
        return Email.builder()
                .sendTime(date)
                .projectId("d9b44127-890e-42a0-97ec-978060ccda98")
                .heading(faker.funnyName().name())
                .message(faker.animal().name())
                .type("Email")
                .userFilter(userFilter)
                .build();
    }

    // Создание sms
    public static Sms getSms() {
        Faker faker = new Faker();
        // Получаем дату в строгом формате (другие форматы не поддерживаются, поэтому обрезаем всё после 23 символа полной даты)
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);
        Sms.UserFilter userFilter = new Sms.UserFilter();
        userFilter.citiesList = new ArrayList<>(Collections.singleton("0c5b2444-70a0-4932-980c-b4dc0d3f02b5"));
        System.out.println(date);
        return Sms.builder()
                .sendTime(date)
                .projectId("d9b44127-890e-42a0-97ec-978060ccda98")
                .message(faker.animal().name())
                .type("SMS")
                .userFilter(userFilter)
                .build();
    }

    // Обновление Email

    public static UpdateEmail updateEmail() {
        Faker faker = new Faker();
        // Получаем дату в строгом формате (другие форматы не поддерживаются, поэтому обрезаем всё после 23 символа полной даты)
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);
        UpdateEmail.UserFilter userFilter = new UpdateEmail.UserFilter();
        userFilter.citiesList = new ArrayList<>(Collections.singleton("0c5b2444-70a0-4932-980c-b4dc0d3f02b5"));
        System.out.println(date);
        Email email = getEmail();
        EmailModel newEmail = addEmail(email);

        return UpdateEmail.builder()
                .sendTime(date)
                .projectId("d9b44127-890e-42a0-97ec-978060ccda98")
                .heading(faker.funnyName().name())
                .message(faker.animal().name())
                .type("Email")
                .userFilter(userFilter)
                .id(newEmail.getId())
                .build();
    }

    // Обновление SMS
    public static UpdateSms updateSms() {
        Faker faker = new Faker();
        // Получаем дату в строгом формате (другие форматы не поддерживаются, поэтому обрезаем всё после 23 символа полной даты)
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);
        UpdateSms.UserFilter userFilter = new UpdateSms.UserFilter();
        userFilter.citiesList = new ArrayList<>(Collections.singleton("0c5b2444-70a0-4932-980c-b4dc0d3f02b5"));
        System.out.println(date);
        Sms sms = getSms();
        SmsModel newSms = addSms(sms);

        return UpdateSms.builder()
                .id(newSms.getId())
                .sendTime(date)
                .projectId("d9b44127-890e-42a0-97ec-978060ccda98")
                .message(faker.animal().name())
                .type("SMS")
                .userFilter(userFilter)
                .build();
    }

    // Обновление Push

    public static UpdatePush updatePush() {
        Faker faker = new Faker();
        // Получаем дату в строгом формате (другие форматы не поддерживаются, поэтому обрезаем всё после 23 символа полной даты)
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);
        System.out.println(date);
        Push push = getPush();
        PushModel newPush = addPush(push);

        return UpdatePush.builder()
                .id(newPush.getId())
                .sendTime(date)
                .projectId("d9b44127-890e-42a0-97ec-978060ccda98")
                .heading(faker.name().username())
                .message(faker.name().username())
                .type("Push")
                .typeLink("HyperLink")
                .linkValueUrl("https://example.com/")
                .userList(new ArrayList<String>(Collections.singleton("3fa85f64-5717-4562-b3fc-2c963f66afa6")))
                .build();
    }

    // Создание уведомления
    public static Reminder getReminder() {
        Faker faker = new Faker();
        // Получаем дату в строгом формате (другие форматы не поддерживаются, поэтому обрезаем всё после 23 символа полной даты)
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);

        System.out.println(date);
        return Reminder.builder()
                .title(faker.animal().name())
                .text(faker.animal().name())
                .deletedUtc(date)
                .employees((new ArrayList<>(Collections.singleton("f02f7e62-dfee-441e-a64d-b78f5336c88d"))))
                .build();
    }


    // Создание контента

    public static Content getInfoPage() {
        Faker faker = new Faker();
        // Получаем дату в строгом формате (другие форматы не поддерживаются, поэтому обрезаем всё после 23 символа полной даты)
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);
        System.out.println(date);
        String image = "https://s3.dex-it.ru/public/apptemplate/ContentImages/c5394197-fb48-4c48-9a62-47220f9cf4bc.png";
        String info = "{\n" +
                "  \"id\": \"edfa9e88-38ac-4eb6-98d0-a14d919dfe26\",\n" +
                "  \"createdUtc\": \"2022-12-15T09:05:43.2038326Z\",\n" +
                "  \"updatedUtc\": \"2022-12-15T09:05:43.2038326Z\",\n" +
                "  \"deletedUtc\": null,\n" +
                "  \"contentType\": \"InfoPage\",\n" +
                "  \"contentStatus\": \"Published\",\n" +
                "  \"name\": \"fghgfh\",\n" +
                "  \"municipalities\": [],\n" +
                "  \"tag\": \"fgfgh\",\n" +
                "  \"publishBefore\": \"2022-12-16T08:09:07.806Z\",\n" +
                "  \"sortOrder\": 0,\n" +
                "  \"additionalInfo\": \"{\\\"previewTitle\\\":\\\"для скрытия\\\",\\\"text\\\":\\\"gdfgdfg\\\",\\\"imageUrl\\\":\\\"https://s3.dex-it.ru/public/clubtemplate/content.images/09990e52-e4f2-4c33-947d-de91813dae39.jpg\\\",\\\"imageMiniUrl\\\":\\\"https://s3.dex-it.ru/public/clubtemplate/content.images/bff67e16-1e14-4b11-bfe3-f30d19e2234d.jpg\\\",\\\"component\\\":[]}\"\n" +
                "}";
        return Content.builder()
                .contentType(String.valueOf(contentType.InfoPage))
                .contentStatus(String.valueOf(contentStatus.Published))
                .name(faker.name().name())
                .tag("autoTest")
                .publishBefore(date)
                .sortOrder(0)
                .imagePath(image)
                .additionalInfo(info)
                .build();
    }


    public static UpdateContent updateContent() {
        Content content = getInfoPage();
        ContentModel newContent = addContent(content);

        Faker faker = new Faker();
        // Получаем дату в строгом формате (другие форматы не поддерживаются, поэтому обрезаем всё после 23 символа полной даты)
        String date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(Calendar.getInstance().getTime()).substring(0, 23);
        System.out.println(date);
        String image = "https://s3.dex-it.ru/public/apptemplate/ContentImages/c5394197-fb48-4c48-9a62-47220f9cf4bc.png";
        String info = "{\n" +
                "  \"id\": \"edfa9e88-38ac-4eb6-98d0-a14d919dfe26\",\n" +
                "  \"createdUtc\": \"2022-12-15T09:05:43.2038326Z\",\n" +
                "  \"updatedUtc\": \"2022-12-15T09:05:43.2038326Z\",\n" +
                "  \"deletedUtc\": null,\n" +
                "  \"contentType\": \"InfoPage\",\n" +
                "  \"contentStatus\": \"Published\",\n" +
                "  \"name\": \"fghgfh\",\n" +
                "  \"municipalities\": [],\n" +
                "  \"tag\": \"fgfgh\",\n" +
                "  \"publishBefore\": \"2022-12-16T08:09:07.806Z\",\n" +
                "  \"sortOrder\": 0,\n" +
                "  \"additionalInfo\": \"{\\\"previewTitle\\\":\\\"для скрытия\\\",\\\"text\\\":\\\"gdfgdfg\\\",\\\"imageUrl\\\":\\\"https://s3.dex-it.ru/public/clubtemplate/content.images/09990e52-e4f2-4c33-947d-de91813dae39.jpg\\\",\\\"imageMiniUrl\\\":\\\"https://s3.dex-it.ru/public/clubtemplate/content.images/bff67e16-1e14-4b11-bfe3-f30d19e2234d.jpg\\\",\\\"component\\\":[]}\"\n" +
                "}";
        return UpdateContent.builder()
                .id(newContent.getId())
                .contentType(String.valueOf(contentType.InfoPage))
                .contentStatus(String.valueOf(contentStatus.Published))
                .name(faker.name().name())
                .tag("newTag")
                .publishBefore(date)
                .sortOrder(0)
                .imagePath(image)
                .additionalInfo(info)
                .build();
    }


    // Регистрация пользователя
    public static RegisterUser createUser() {
        Faker faker = new Faker();
        String phone = "7" + faker.number().digits(10);
        String smsCode = "0000";
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String middleName = faker.name().lastName();
        String lastName = faker.animal().name();
        String birthDay = "2000-12-17T08:29:33.197Z";
        String password = "Passw0rd%";

        return RegisterUser.builder()
                .phone(phone)
                .password(password)
                .smsCode(smsCode)
                .isConditionUsageAndConfidentialPoliticsAgree(true)
                .email(email)
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .birthDay(birthDay)
                .build();
    }

    public static RegisterUser createUserWithoutPhone() {
        Faker faker = new Faker();
        String smsCode = "0000";
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String middleName = faker.name().lastName();
        String lastName = faker.animal().name();
        String birthDay = "2000-12-17T08:29:33.197Z";
        String password = "Passw0rd%";

        return RegisterUser.builder()
                .password(password)
                .smsCode(smsCode)
                .isConditionUsageAndConfidentialPoliticsAgree(true)
                .email(email)
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .birthDay(birthDay)
                .build();
    }

    public static RegisterUser createUserWithoutSmsCode() {
        Faker faker = new Faker();
        String phone = "7" + faker.number().digits(10);
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String middleName = faker.name().lastName();
        String lastName = faker.animal().name();
        String birthDay = "2000-12-17T08:29:33.197Z";
        String password = "Passw0rd%";

        return RegisterUser.builder()
                .phone(phone)
                .password(password)
                .isConditionUsageAndConfidentialPoliticsAgree(true)
                .email(email)
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .birthDay(birthDay)
                .build();
    }

    public static RegisterUser createUserWithoutEmail() {
        Faker faker = new Faker();
        String phone = "7" + faker.number().digits(10);
        String smsCode = "0000";
        String firstName = faker.name().firstName();
        String middleName = faker.name().lastName();
        String lastName = faker.animal().name();
        String birthDay = "2000-12-17T08:29:33.197Z";
        String password = "Passw0rd%";

        return RegisterUser.builder()
                .phone(phone)
                .password(password)
                .smsCode(smsCode)
                .isConditionUsageAndConfidentialPoliticsAgree(true)
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .birthDay(birthDay)
                .build();
    }

    public static RegisterUser createUserWithoutFirstName() {
        Faker faker = new Faker();
        String phone = "7" + faker.number().digits(10);
        String smsCode = "0000";
        String email = faker.internet().emailAddress();
        String middleName = faker.name().lastName();
        String lastName = faker.animal().name();
        String birthDay = "2000-12-17T08:29:33.197Z";
        String password = "Passw0rd%";

        return RegisterUser.builder()
                .phone(phone)
                .password(password)
                .smsCode(smsCode)
                .isConditionUsageAndConfidentialPoliticsAgree(true)
                .email(email)
                .middleName(middleName)
                .lastName(lastName)
                .birthDay(birthDay)
                .build();
    }

    public static RegisterUser createUserWithoutMiddleName() {
        Faker faker = new Faker();
        String phone = "7" + faker.number().digits(10);
        String smsCode = "0000";
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String lastName = faker.animal().name();
        String birthDay = "2000-12-17T08:29:33.197Z";
        String password = "Passw0rd%";

        return RegisterUser.builder()
                .phone(phone)
                .password(password)
                .smsCode(smsCode)
                .isConditionUsageAndConfidentialPoliticsAgree(true)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .birthDay(birthDay)
                .build();
    }

    public static RegisterUser createUserWithoutLastName() {
        Faker faker = new Faker();
        String phone = "7" + faker.number().digits(10);
        String smsCode = "0000";
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String middleName = faker.name().lastName();
        String birthDay = "2000-12-17T08:29:33.197Z";
        String password = "Passw0rd%";

        return RegisterUser.builder()
                .phone(phone)
                .password(password)
                .smsCode(smsCode)
                .isConditionUsageAndConfidentialPoliticsAgree(true)
                .email(email)
                .firstName(firstName)
                .middleName(middleName)
                .birthDay(birthDay)
                .build();
    }

    public static RegisterUser createUserWithoutBirthday() {
        Faker faker = new Faker();
        String phone = "7" + faker.number().digits(10);
        String smsCode = "0000";
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String middleName = faker.name().lastName();
        String lastName = faker.animal().name();
        String password = "Passw0rd%";

        return RegisterUser.builder()
                .phone(phone)
                .password(password)
                .smsCode(smsCode)
                .isConditionUsageAndConfidentialPoliticsAgree(true)
                .email(email)
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .build();
    }

    public static RegisterUser createUserWithoutPassword() {
        Faker faker = new Faker();
        String phone = "7" + faker.number().digits(10);
        String smsCode = "0000";
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String middleName = faker.name().lastName();
        String lastName = faker.animal().name();
        String birthDay = "2000-12-17T08:29:33.197Z";

        return RegisterUser.builder()
                .phone(phone)
                .smsCode(smsCode)
                .isConditionUsageAndConfidentialPoliticsAgree(true)
                .email(email)
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .birthDay(birthDay)
                .build();
    }

    public static RegisterUser createUserWithoutAll() {
        return RegisterUser.builder()
                .build();
    }

    // Смена пароля

    public static ChangePassword changePassword() {
        String phone = "77777777777";
        String smsCode = "0000";
        String password = "Passw0rd%";

        return ChangePassword.builder()
                .phone(phone)
                .password(password)
                .smsCode(smsCode)
                .build();
    }

    public static ChangePassword changePassInvalidPhone() {
        String phone = "78877788878";
        String smsCode = "0000";
        String password = "Passw0rd%";

        return ChangePassword.builder()
                .phone(phone)
                .password(password)
                .smsCode(smsCode)
                .build();
    }

    public static ChangePassword changePassInvalidPassword() {
        String phone = "78877788878";
        String smsCode = "0000";
        String password = "Passw0rd";

        return ChangePassword.builder()
                .phone(phone)
                .password(password)
                .smsCode(smsCode)
                .build();
    }

    public static ChangePassword changePassInvalidSms() {
        String phone = "77777777777";
        String smsCode = "00000";
        String password = "Passw0rd%";

        return ChangePassword.builder()
                .phone(phone)
                .password(password)
                .smsCode(smsCode)
                .build();
    }

    public static ChangePassword changePassActiveSession() {
        String password = "Passw0rd%";
        return ChangePassword.builder()
                .password(password)
                .build();
    }

    public static ChangePassword changePassActiveSessionInvalidPass() {
        String password = "Pass";
        return ChangePassword.builder()
                .password(password)
                .build();
    }


    // Генерирование токена нового пользователя

    public static DeleteUser SmsCode() {
        String smsCode = "0000";
        return DeleteUser.builder()
                .smsCode(smsCode)
                .build();
    }

    public static DeleteUser InvalidSmsCode() {
        String smsCode = "56568";
        return DeleteUser.builder()
                .smsCode(smsCode)
                .build();
    }

    // Обновление пользователя

    public static UpdateUser updateUser() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String firstName = faker.name().firstName();
        String middleName = faker.name().lastName();
        String lastName = faker.animal().name();

        return UpdateUser.builder()
                .email(email)
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .build();
    }


}