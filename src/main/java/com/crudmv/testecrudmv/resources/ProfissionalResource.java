package com.crudmv.testecrudmv.resources;

import com.crudmv.testecrudmv.domain.Profissional;
import com.crudmv.testecrudmv.dto.ProfissionalDTO;
import com.crudmv.testecrudmv.dto.ProfissionalNewDTO;
import com.crudmv.testecrudmv.services.ProfissionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/profissionais")
public class ProfissionalResource {

    @Autowired
    private ProfissionalService service;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Profissional> find(@PathVariable Long id) {
        Profissional obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ProfissionalNewDTO objDto) {
        Profissional obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
    // Método para efetuar atualização.
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ProfissionalDTO objDto, @PathVariable Long id) {
        Profissional obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }
    // Método para deletar.
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    // Método para pegar todos os profissionais.
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProfissionalDTO>> findAll() {
        List<Profissional> list = service.findAll();
        List<ProfissionalDTO> listDto = list.stream().map(obj -> new ProfissionalDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    // Método com pagina para pegar os profissionais com paginação.
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ProfissionalDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Profissional> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ProfissionalDTO> listDto = list.map(obj -> new ProfissionalDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }


}
