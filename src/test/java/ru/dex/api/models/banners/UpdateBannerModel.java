package ru.dex.api.models.banners;

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
public class UpdateBannerModel {
    private String locationKey;
    private String locationName;
    private String author;
    private String clubName;
    private String clubId;
    private String transitionUri;
    private String imageUri;
    private String locationId;
    private String startPublish;
    private String name;
    private String id;
    private String endPublish;
    private String status;
}