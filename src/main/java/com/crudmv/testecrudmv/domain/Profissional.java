package com.crudmv.testecrudmv.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "PROFISSIONAL")
public class Profissional implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_profissional")
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "profissionais", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Estabelecimento> estabelecimento = new ArrayList<Estabelecimento>();

    @OneToMany(mappedBy = "profissionais")
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    public Profissional() {
    }

    public Profissional(Long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Estabelecimento> getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(List<Estabelecimento> estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profissional)) return false;
        Profissional that = (Profissional) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
