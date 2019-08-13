package lt.codeacademy.cauzduotis.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
@ApiModel(description = "Comment. ")
public class Komentaras {
    @ApiModelProperty(notes = "The database generated menu item ID.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ApiModelProperty(notes = "Comment author")
    @Column
    @NotNull
    private String autorius;

    @ApiModelProperty(notes = "Comment text")
    @Column
    @NotNull
    private String komentaras;

    @ApiModelProperty(notes = "Comment answer")
    @Column
    private String atsakymas;

    @ApiModelProperty(notes = "Comment date")
    @Column
     private LocalDateTime komentdata;

    @ApiModelProperty(notes = "Comment irasas")
    @ManyToOne
    @JoinColumn(name = "irasas",referencedColumnName = "id",foreignKey = @ForeignKey(name ="FK_irasas_to_komentaras") )
    private Irasas irasas;

    public Komentaras(@NotNull String autorius, @NotNull String komentaras, String atsakymas) {
        this.autorius = autorius;
        this.komentaras = komentaras;
        this.atsakymas = atsakymas;
        }



    @Override
    public String toString() {
        return "Komentaras{" +
                "id=" + id +
                ", autorius='" + autorius + '\'' +
                ", komentras='" + komentaras + '\'' +
                ", atsakymas='" + atsakymas + '\'' +
                ", komentdata=" + komentdata +
                '}';
    }
}
