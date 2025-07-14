package livros.API_Gerenciamento_Livros.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


@Embeddable
public class UsuarioCopiaLivroId {

    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "copiaLivro_id")
    private Integer copiaLivroId;

    public UsuarioCopiaLivroId() {
    }

    public UsuarioCopiaLivroId(Integer usuarioId, Integer copiaLivroId) {
        this.usuarioId = usuarioId;
        this.copiaLivroId = copiaLivroId;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getCopiaLivroId() {
        return copiaLivroId;
    }

    public void setCopiaLivroId(Integer copiaLivroId) {
        this.copiaLivroId = copiaLivroId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
