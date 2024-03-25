package ru.dex.api.models.contents;

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
public class Content {
    public String contentType;
    public String contentStatus;
    public String name;
    public String tag;
    public String publishBefore;
    public int sortOrder;
    public String additionalInfo;
    public String imagePath;
    public String id;


    @Override
    public String toString() {
        return "Content{" +
                "contentType='" + contentType + '\'' +
                ", contentStatus='" + contentStatus + '\'' +
                ", name='" + name + '\'' +
                ", tag='" + tag + '\'' +
                ", publishBefore='" + publishBefore + '\'' +
                ", sortOrder=" + sortOrder +
                ", additionalInfo='" + additionalInfo + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
