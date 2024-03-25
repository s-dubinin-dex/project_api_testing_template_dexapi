package ru.dex.api.models.employee;

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
public class Employee {

    private String roleId;

    private String name;

    private String email;


    @Override
    public String toString() {
        return
                "Employee{" +
                        "roleId = '" + roleId + '\'' +
                        ",name = '" + name + '\'' +
                        ",email = '" + email + '\'' +
                        "}";
    }
}