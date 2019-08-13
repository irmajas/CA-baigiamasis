package lt.codeacademy.cauzduotis.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
@ApiModel(description = "All details about Irasas. ")
public class Irasas {
    @ApiModelProperty(notes = "The database generated menu item ID.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ApiModelProperty(notes = "Irasas Title")
    @Column
    @NotNull
    private String pavadinimas;

    @ApiModelProperty(notes = "Irasas text")
    @Column(length = 8000)
   @NotNull
    private String tekstas;

    @ApiModelProperty(notes = "Irasas Date")
    @Column
    @NotNull
    private LocalDateTime irasodata;

    @ApiModelProperty(notes = "Irasas Comments")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "irasas")
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Komentaras> komentarai;


    public Irasas(@NotNull String pavadinimas, @NotNull String tekstas) {
        this.pavadinimas = pavadinimas;
        this.tekstas = tekstas;
        this.irasodata = LocalDateTime.now();
    }



    @Override
    public String toString() {
        return "Irasas{" +
                "id=" + id +
                ", pavadinimas='" + pavadinimas + '\'' +
                ", tekstas='" + tekstas + '\'' +
                ", irasodata=" + irasodata +
                '}';
    }
}
