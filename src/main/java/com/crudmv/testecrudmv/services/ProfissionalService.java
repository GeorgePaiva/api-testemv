package com.crudmv.testecrudmv.services;

import com.crudmv.testecrudmv.domain.Endereco;
import com.crudmv.testecrudmv.domain.Estabelecimento;
import com.crudmv.testecrudmv.domain.Profissional;
import com.crudmv.testecrudmv.dto.ProfissionalDTO;
import com.crudmv.testecrudmv.dto.ProfissionalNewDTO;
import com.crudmv.testecrudmv.repositories.EnderecoRepository;
import com.crudmv.testecrudmv.repositories.ProfissionalRepository;
import com.crudmv.testecrudmv.services.exceptions.DataIntegrityException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository repo;

    @Autowired
    private EnderecoRepository enderecoRepository;


    public Profissional find(Long id) {
        Optional<Profissional> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id, null));

    }

    @Transactional
    public Profissional insert(Profissional obj) {
        obj.setId(null);
        obj = repo.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Profissional update(Profissional obj) {
        Profissional newObj = find(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void delete(Long id) {
        find(id);
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possável excluir porque há entidades relacionadas");
        }
    }

    public List<Profissional> findAll() {
        return repo.findAll();
    }

    // Método que retorna os estabelecimentos especificados.
    public Page<Profissional> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    // Método auxiliar que instância uma Profissional a partir de um DTO.
    public Profissional fromDTO(ProfissionalDTO objDto) {
        return new Profissional(objDto.getId(), objDto.getNome());
    }

    public Profissional fromDTO(ProfissionalNewDTO objDto) {
        Profissional prof = new Profissional(null, objDto.getNome());

        Estabelecimento est = new Estabelecimento(null, objDto.getNome(), objDto.getTelefone1(), objDto.getTelefone2(), objDto.getTelefone3());
        Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
                objDto.getBairro(), objDto.getCep(), prof, est);

        prof.getEnderecos().add(end);
        prof.getTelefones().add(objDto.getTelefone1());
        if (objDto.getTelefone2() != null) {
            prof.getTelefones().add(objDto.getTelefone2());
        }
        if (objDto.getTelefone3() != null) {
            prof.getTelefones().add(objDto.getTelefone3());
        }
        return prof;
    }

    private void updateData(Profissional newObj, Profissional obj) {
        newObj.setNome(obj.getNome());
        newObj.setEnderecos(obj.getEnderecos());
    }

}
