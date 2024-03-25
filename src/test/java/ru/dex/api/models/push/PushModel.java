package ru.dex.api.models.push;

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
public class PushModel {
    private String heading;
    private String message;
    private String linkValueUrl;
    private String type;
    private String userTopicCondition;
    private String sendTime;
    private Object userList;
    private String typeLink;
    private Object userFilter;
    private String id;
    private String projectId;
    private String createdUtc;
}