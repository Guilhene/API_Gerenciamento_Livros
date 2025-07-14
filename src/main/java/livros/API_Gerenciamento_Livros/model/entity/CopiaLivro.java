package livros.API_Gerenciamento_Livros.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CopiaLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean disponivel;

    @Column(nullable = false,length = 20)
    private String CondicaoLivro;

    @OneToMany(mappedBy = "copiaLivro")
    private List<Aluguel> aluguel;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "estante_id")
    private Estante estante;

    @ManyToOne
    @JoinColumn(name = "pratileira_id")
    private Pratileira pratileira;

}
