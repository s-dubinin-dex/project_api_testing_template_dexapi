package ru.dex.api.models.email;

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
public class UpdateEmail {
    public String id;
    public String type;
    public String sendTime;
    public String projectId;
    public String heading;
    public String message;
    public UserFilter userFilter;

    public static class UserFilter {
        public ArrayList<String> citiesList;
    }

    @Override
    public String toString() {
        return "UpdateEmail{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", projectId='" + projectId + '\'' +
                ", heading='" + heading + '\'' +
                ", message='" + message + '\'' +
                ", userFilter=" + userFilter +
                '}';
    }
}
