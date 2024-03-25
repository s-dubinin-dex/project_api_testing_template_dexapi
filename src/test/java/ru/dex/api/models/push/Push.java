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
public class Push {
    private String sendTime;
    private String heading;
    private String message;
    private String type;
    private String typeLink;
    private String linkValueUrl;
    public ArrayList<String> userList;

    @Override
    public String toString() {
        return "Push{" +
                "sendTime='" + sendTime + '\'' +
                ", heading='" + heading + '\'' +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", typeLink='" + typeLink + '\'' +
                ", linkValueUrl='" + linkValueUrl + '\'' +
                ", userList=" + userList +
                '}';
    }
}
