package ru.dex.api.models.role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRole {
    private String name;
    private List<String> policies;
    private String id;

    @Override
    public String toString() {
        return "UpdateRole{" +
                "name='" + name + '\'' +
                ", policies=" + policies +
                ", id='" + id + '\'' +
                '}';
    }
}