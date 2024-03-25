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
public class InvitationModel {
    private String role;
    private String roleId;
    private String name;
    private String id;
    private Object activationDate;
    private String email;
    private String createdUtc;
    private Object deletedUtc;
}