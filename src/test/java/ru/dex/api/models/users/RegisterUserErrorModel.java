package ru.dex.api.models.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUserErrorModel {
    public String title;
    public int status;
    public String detail;
    public Errors errors;

    public static class Errors {
        public ArrayList<Phone> phone;
        public ArrayList<SmsCode> smsCode;
        public ArrayList<Password> password;
        public ArrayList<IsConditionUsageAndConfidentialPoliticsAgree> isConditionUsageAndConfidentialPoliticsAgree;
    }

    public static class IsConditionUsageAndConfidentialPoliticsAgree {
        public String errorCode;
        public Object params;
    }

    public static class Password {
        public String errorCode;
        public Object params;
    }

    public static class Phone {
        public String errorCode;
        public Object params;
    }

    public static class SmsCode {
        public String errorCode;
        public Object params;
    }

}
