package livros.API_Gerenciamento_Livros.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pratileira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pratileiraId;

    @Column(nullable = false, length = 60)
    private Integer identificacao;

    @ManyToOne
    @JoinColumn(name = "estante_id")
    @JsonBackReference
    private Estante estante;

    @OneToMany(mappedBy = "pratileira")
    @JsonBackReference
    private List<CopiaLivro> copiaLivros;

    public Pratileira(Integer identificacao, Estante estante) {
        this.identificacao = identificacao;
        this.estante = estante;
    }
}
