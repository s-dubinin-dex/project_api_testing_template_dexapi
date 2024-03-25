package ru.dex.api.models.reminder;

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
public class ReminderModel {
    public String id;
    public String createdUtc;
    public Object deletedUtc;
    public String title;
    public String text;
    public ArrayList<String> employees;

    @Override
    public String toString() {
        return "ReminderModel{" +
                "id='" + id + '\'' +
                ", createdUtc='" + createdUtc + '\'' +
                ", deletedUtc=" + deletedUtc +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", employees=" + employees +
                '}';
    }
}
