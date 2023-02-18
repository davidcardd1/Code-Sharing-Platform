package platform.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Code {

    @Id
    @JsonIgnore
    private String id;
    private String code;
    private LocalDateTime date = LocalDateTime.now();

    private long time = 0;

    private int views = 0;

    @JsonIgnore
    private Boolean timeRestricted = false;

    @JsonIgnore
    private long timeOriginal = 0;

    @JsonIgnore
    private Boolean viewsRestricted = false;

    public Code(String code, long time, int views) {
        this.id = UUID.randomUUID().toString();
        this.code = code;
        this.time = time < 0 ? 0 : time;
        this.timeOriginal = time < 0 ? 0 : time;
        this.views = views < 0 ? 0 : views;

        if (time > 0) {
            timeRestricted = true;
        }
        if (views > 0) {
            viewsRestricted = true;
        }
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }

    @JsonIgnore
    public LocalDateTime getDateNormal() {
        return this.date;
    }
}
