package com.crudmv.testecrudmv.services;

import com.crudmv.testecrudmv.domain.Estabelecimento;
import com.crudmv.testecrudmv.dto.EstabelecimentoDTO;
import com.crudmv.testecrudmv.repositories.EstabelecimentoRepository;
import com.crudmv.testecrudmv.services.exceptions.DataIntegrityException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository repo;

    public Estabelecimento find(Long id) {
        Optional<Estabelecimento> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id, null));
    }

    public Estabelecimento insert(Estabelecimento obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Estabelecimento update(Estabelecimento obj) {
        Estabelecimento newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possável excluir uma categoria que possui produtos.");
        }
    }

    public List<Estabelecimento> findAll() {
        return repo.findAll();
    }

    // Método que retorna o estabelecimento especificado.
    public Page<Estabelecimento> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    // Método auxiliar que instância um estabelecimento a partir de DTO.
    public Estabelecimento fromDTO(EstabelecimentoDTO objDto) {
        return new Estabelecimento(objDto.getId(), objDto.getNome());
    }

    private void updateData(Estabelecimento newObj, Estabelecimento obj) {
        newObj.setNome(obj.getNome());
    }
}
