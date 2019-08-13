package lt.codeacademy.cauzduotis.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class KomentarasView {
    private int id;
    private String autorius;
    private String komentaras;
    private String atsakymas;
    private String komentdata;


    public KomentarasView(int id, String autorius, String komentaras, String atsakymas,String komentdata) {
        this.id = id;
        this.autorius = autorius;
        this.komentaras = komentaras;
        this.atsakymas = atsakymas;
        this.komentdata=komentdata;

    }
}
