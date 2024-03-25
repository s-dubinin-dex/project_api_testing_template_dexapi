package ru.dex.api.models.sms;

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
public class Sms {
    public String sendTime;
    public String projectId;
    public String message;
    public String type;
    public UserFilter userFilter;


    public static class UserFilter {
        public ArrayList<String> citiesList;
    }
}
