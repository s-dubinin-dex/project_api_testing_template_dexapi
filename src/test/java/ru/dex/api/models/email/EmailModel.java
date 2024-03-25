package ru.dex.api.models.email;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class EmailModel {
    public String heading;
    public String message;
    public String id;
    public String projectId;
    public Date createdUtc;
    public String type;
    public Date sendTime;
    public Object userList;
    public Object userTopicCondition;
    public UserFilter userFilter;

    public static class UserFilter {
        public ArrayList<String> citiesList;
    }

}
