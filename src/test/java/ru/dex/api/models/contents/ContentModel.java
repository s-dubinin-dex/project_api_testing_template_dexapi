package ru.dex.api.models.contents;

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
public class ContentModel {

    public String id;
    public String createdUtc;
    public String updatedUtc;
    public String deletedUtc;
    public String contentType;
    public String contentStatus;
    public String name;
    public ArrayList<Object> municipalities;
    public String tag;
    public String publishBefore;
    public int sortOrder;
    public String additionalInfo;
}

