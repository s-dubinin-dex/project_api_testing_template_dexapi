package ru.dex.api.models.push;

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
public class UpdatePush {
    public String id;
    public String type;
    public String sendTime;
    public String projectId;
    public String heading;
    public String message;
    public String typeLink;
    public String linkValueUrl;
    public ArrayList<String> userList;
    public TopicConditionInfo topicConditionInfo;
    public static class TopicConditionInfo{
        public ArrayList<Object> mobilePlatforms;
        public Object cityId;
    }

    @Override
    public String toString() {
        return "UpdatePush{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", projectId='" + projectId + '\'' +
                ", heading='" + heading + '\'' +
                ", message='" + message + '\'' +
                ", typeLink='" + typeLink + '\'' +
                ", linkValueUrl='" + linkValueUrl + '\'' +
                ", topicConditionInfo=" + topicConditionInfo +
                '}';
    }
}
