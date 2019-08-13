package lt.codeacademy.cauzduotis.models;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class IrasasSimple {
    private int id;
    private String pavadinimas;
    private String tekstas;
    private String irasodata;

    public IrasasSimple(int id, String pavadinimas, String tekstas, String irasodata) {
        this.id = id;
        this.pavadinimas = pavadinimas.toUpperCase();
        this.tekstas = tekstas;
        this.irasodata = irasodata;
    }




}
