package ru.dex.api.models.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUser {
    public String phone;
    public String password;
    public String smsCode;
    public boolean isConditionUsageAndConfidentialPoliticsAgree;
    public String email;
    public String firstName;
    public String middleName;
    public String lastName;
    public String birthDay;
}
