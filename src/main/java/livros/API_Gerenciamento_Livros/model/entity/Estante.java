package livros.API_Gerenciamento_Livros.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private Livro livro;

    @OneToMany(mappedBy = "estante")
    @JsonBackReference
    private List<CopiaLivro> copiaLivros;

    @OneToMany(mappedBy = "estante", cascade =  CascadeType.ALL)
    @JsonManagedReference
    private List<Pratileira> pratileiras;

    public Estante(Integer estanteId, String areaAtuacao, Livro livro) {
        this.estanteId = estanteId;
        this.areaAtuacao = areaAtuacao;
        this.livro = livro;
    }
}
