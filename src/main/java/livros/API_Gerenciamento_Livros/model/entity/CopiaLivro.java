package livros.API_Gerenciamento_Livros.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class CopiaLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Boolean disponivel;

    @Column(nullable = false,length = 20)
    private String condicaoLivro;

    @OneToMany(mappedBy = "copiaLivro")
    private List<Aluguel> aluguel;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    @JsonBackReference
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "estante_id")
    @JsonBackReference
    private Estante estante;

    @ManyToOne
    @JoinColumn(name = "pratileira_id")
    @JsonBackReference
    private Pratileira pratileira;

    public CopiaLivro(Integer id, Boolean disponivel, String condicaoLivro,List<Aluguel> aluguel, Livro livro, Estante estante, Pratileira pratileira) {
        this.id = id;
        this.disponivel = disponivel;
        this.condicaoLivro = condicaoLivro;
        this.aluguel = aluguel;
        this.livro = livro;
        this.estante = estante;
        this.pratileira = pratileira;
    }

    public CopiaLivro() {
    }
}
