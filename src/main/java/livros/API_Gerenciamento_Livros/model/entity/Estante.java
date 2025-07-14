package livros.API_Gerenciamento_Livros.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Estante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer estanteId;

    @Column(nullable = false, length = 60)
    private String areaAtuacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @OneToMany(mappedBy = "estante")
    private List<CopiaLivro> copiaLivros;

    @OneToMany(mappedBy = "estante")
    private List<Pratileira> pratileiras;

    public Estante(Integer estanteId, String areaAtuacao, Livro livro) {
        this.estanteId = estanteId;
        this.areaAtuacao = areaAtuacao;
        this.livro = livro;
    }
}
