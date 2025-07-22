package livros.API_Gerenciamento_Livros.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer livroId;

    @Column(nullable = false, length = 60)
    private String titulo;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 60)
    private String tema;

    @Column(nullable = false)
    private int Volume;

    @Column(nullable = false)
    private Date dataCriacao;

    @Column(nullable = false, length = 60)
    private String areaAtuacao;

    @OneToMany(mappedBy = "livro",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<CopiaLivro> copiaLivros;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonBackReference
    private Autor autor;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "livro")
    @JsonManagedReference
    private Estante estante;

    public Livro() {
    }

    public Livro(Integer livroId, String titulo, String descricao, String tema, int volume, Date dataCriacao, String areaAtuacao, Autor autor, Estante estante, List<CopiaLivro> copiaLivros) {
        this.livroId = livroId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tema = tema;
        Volume = volume;
        this.dataCriacao = dataCriacao;
        this.areaAtuacao = areaAtuacao;
        this.autor = autor;
        this.estante = estante;
        this.copiaLivros = copiaLivros;
    }
}
