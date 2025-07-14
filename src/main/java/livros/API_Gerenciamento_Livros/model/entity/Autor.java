package livros.API_Gerenciamento_Livros.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer autorId;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 60)
    private String pais;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

    public Autor(Integer autorId, String nome, String descricao, String pais, List<Livro> livros) {
        this.autorId = autorId;
        this.nome = nome;
        this.descricao = descricao;
        this.pais = pais;
        this.livros = livros;
    }
}
