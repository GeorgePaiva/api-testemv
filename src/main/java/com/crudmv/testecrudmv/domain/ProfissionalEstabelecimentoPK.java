package com.crudmv.testecrudmv.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Table(name = "PROFISSIONALESTABELECIMENTOPK")
public class ProfissionalEstabelecimentoPK implements Serializable {
    private static final long serialVersionUID = 1L;


    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;

    @ManyToOne
    @JoinColumn(name = "estabelecimento_id")
    private Estabelecimento estabelecimento;

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfissionalEstabelecimentoPK)) return false;
        ProfissionalEstabelecimentoPK that = (ProfissionalEstabelecimentoPK) o;
        return Objects.equals(getProfissional(), that.getProfissional()) &&
                Objects.equals(getEstabelecimento(), that.getEstabelecimento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProfissional(), getEstabelecimento());
    }
}
