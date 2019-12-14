package com.crudmv.testecrudmv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "PROFISSIONALESTABELECIMENTO")
public class ProfissionalEstabelecimento implements Serializable {
    private static final long serialVersionUID = 1L;


    @JsonIgnore
    @EmbeddedId
    private ProfissionalEstabelecimentoPK id = new ProfissionalEstabelecimentoPK();

    private String turno;
    private String especialidade;

    public ProfissionalEstabelecimento() {
    }

    public ProfissionalEstabelecimento(Profissional profissional, Estabelecimento estabelecimento, String turno, String especialidade) {
        super();
        id.setProfissional(profissional);
        id.setEstabelecimento(estabelecimento);
        this.turno = turno;
        this.especialidade = especialidade;
    }


    public Profissional getProfissional() {
        return id.getProfissional();
    }

    @JsonIgnore
    public Estabelecimento getEstabelecimento() {
        return id.getEstabelecimento();
    }

    public ProfissionalEstabelecimentoPK getId() {
        return id;
    }

    public void setId(ProfissionalEstabelecimentoPK id) {
        this.id = id;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfissionalEstabelecimento)) return false;
        ProfissionalEstabelecimento that = (ProfissionalEstabelecimento) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
