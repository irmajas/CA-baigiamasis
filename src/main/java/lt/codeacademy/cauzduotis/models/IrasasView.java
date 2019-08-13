package lt.codeacademy.cauzduotis.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
public class IrasasView {
    private int id;

    private String pavadinimas;
    private String tekstas;
    private String irasodata;

    public IrasasView(int id, String pavadinimas, String tekstas, String irasodata) {
        this.id = id;
        this.pavadinimas = pavadinimas.toUpperCase();
        this.tekstas = tekstas;
        this.irasodata = irasodata;


    }
}
