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
public class Email {
    public String sendTime;
    public String projectId;
    public String heading;
    public String message;
    public String type;
    public UserFilter userFilter;

    public static class UserFilter {
        public ArrayList<String> citiesList;
    }

    @Override
    public String toString() {
        return "Email{" +
                "sendTime=" + sendTime +
                ", projectId='" + projectId + '\'' +
                ", heading='" + heading + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", userFilter=" + userFilter +
                '}';
    }
}
