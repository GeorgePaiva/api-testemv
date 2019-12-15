package com.crudmv.testecrudmv.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "ESTABELECIMENTO")
public class Estabelecimento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_estabelecimento")
    private String nome;

    @JoinTable(name = "PROFISSIONAL_ESTABELECIMENTO", joinColumns = @JoinColumn(name = "profissional_id"), inverseJoinColumns = @JoinColumn(name = "estabelecimento_id"))
    @OneToMany
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @OneToMany
    private List<Profissional> profissionais = new ArrayList<>();

    public Estabelecimento(){}

    public Estabelecimento(Long id, String nome, String telefone1, String telefone2, String telefone3) {
    }

    public Estabelecimento(Long id, String nome) {
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

    public List<Profissional> getListProfissionais() {
        return profissionais;
    }

    public void setListProfissionais(List<Profissional> profissionais) {
        this.profissionais = profissionais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estabelecimento)) return false;
        Estabelecimento that = (Estabelecimento) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
