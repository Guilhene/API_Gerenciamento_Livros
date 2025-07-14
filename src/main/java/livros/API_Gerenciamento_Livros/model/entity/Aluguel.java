package livros.API_Gerenciamento_Livros.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Aluguel_usuario_copiaLivro")
public class Aluguel {

    @EmbeddedId
    private UsuarioCopiaLivroId usuarioCopiaLivroId;

    @Column(nullable = false)
    private Date dt_emprestado;

    @Column(nullable = false)
    private Date dt_devolucao;

    @Column(nullable = false)
    private Date dt_devolucao_realizada;

    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @MapsId("copiaLivroId")
    @JoinColumn(name = "copia_livro_id")
    private CopiaLivro copiaLivro;

    public Aluguel() {
    }

    public Aluguel(UsuarioCopiaLivroId usuarioCopiaLivroId, Date dt_emprestado, Date dt_devolucao, Date dt_devolucao_realizada, Usuario usuario, CopiaLivro copiaLivro) {
        this.usuarioCopiaLivroId = usuarioCopiaLivroId;
        this.dt_emprestado = dt_emprestado;
        this.dt_devolucao = dt_devolucao;
        this.dt_devolucao_realizada = dt_devolucao_realizada;
        this.usuario = usuario;
        this.copiaLivro = copiaLivro;
    }

    public UsuarioCopiaLivroId getUsuarioCopiaLivroId() {
        return usuarioCopiaLivroId;
    }

    public void setUsuarioCopiaLivroId(UsuarioCopiaLivroId usuarioCopiaLivroId) {
        this.usuarioCopiaLivroId = usuarioCopiaLivroId;
    }

    public Date getDt_emprestado() {
        return dt_emprestado;
    }

    public void setDt_emprestado(Date dt_emprestado) {
        this.dt_emprestado = dt_emprestado;
    }

    public Date getDt_devolucao() {
        return dt_devolucao;
    }

    public void setDt_devolucao(Date dt_devolucao) {
        this.dt_devolucao = dt_devolucao;
    }

    public Date getDt_devolucao_realizada() {
        return dt_devolucao_realizada;
    }

    public void setDt_devolucao_realizada(Date dt_devolucao_realizada) {
        this.dt_devolucao_realizada = dt_devolucao_realizada;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CopiaLivro getCopiaLivro() {
        return copiaLivro;
    }

    public void setCopiaLivro(CopiaLivro copiaLivro) {
        this.copiaLivro = copiaLivro;
    }
}
