package com.crudmv.testecrudmv.dto;

import com.crudmv.testecrudmv.domain.Estabelecimento;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EstabelecimentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String endereco;

    private Integer telefone;

    public EstabelecimentoDTO() {
    }

    public EstabelecimentoDTO(Estabelecimento obj) {
        id = obj.getId();
        nome = obj.getNome();
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }
}
