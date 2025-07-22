package livros.API_Gerenciamento_Livros.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Aluguel {

    @EmbeddedId
    private UsuarioCopiaLivroId usuarioCopiaLivroId;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate dt_emprestado;

    @Column(nullable = false)
    private LocalDate dt_devolucao;

    private LocalDate dt_devolucao_realizada = null;

    @Column(nullable = false)
    private Integer realugar;


    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    @ManyToOne
    @MapsId("copiaLivroId")
    @JoinColumn(name = "copia_livro_id")
    @JsonBackReference
    private CopiaLivro copiaLivro;

    public Aluguel() {
    }

    public Aluguel(LocalDate dt_devolucao_realizada, Integer realugar, Usuario usuario, CopiaLivro copiaLivro) {
        this.dt_devolucao_realizada = dt_devolucao_realizada;
        this.realugar = realugar;
        this.usuario = usuario;
        this.copiaLivro = copiaLivro;
    }

}
