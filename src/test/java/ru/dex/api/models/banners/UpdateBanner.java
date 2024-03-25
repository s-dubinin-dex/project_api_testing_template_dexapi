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
public class UpdateBanner {
    private String imageUri;
    private String locationId;
    private String startPublish;
    private String name;
    private String clubId;
    private String id;
    private String endPublish;
    private String transitionUri;
}