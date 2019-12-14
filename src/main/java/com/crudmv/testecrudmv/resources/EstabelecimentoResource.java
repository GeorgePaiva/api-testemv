package com.crudmv.testecrudmv.resources;

import com.crudmv.testecrudmv.domain.Estabelecimento;
import com.crudmv.testecrudmv.dto.EstabelecimentoDTO;
import com.crudmv.testecrudmv.services.EstabelecimentoService;
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
@RequestMapping(value = "/estabelecimentos")
public class EstabelecimentoResource {

    @Autowired
    private EstabelecimentoService service;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Estabelecimento> find(@PathVariable Long id) {
        Estabelecimento obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody EstabelecimentoDTO objDto) {
        Estabelecimento obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    // Método para efetuar atualização.
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody EstabelecimentoDTO objDto, @PathVariable Long id) {
        Estabelecimento obj = service.fromDTO(objDto);
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
    public ResponseEntity<List<EstabelecimentoDTO>> findAll() {
        List<Estabelecimento> list = service.findAll();
        List<EstabelecimentoDTO> listDto = list.stream().map(obj -> new EstabelecimentoDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    // Método com pagina para pegar os profissionais com paginação.
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<EstabelecimentoDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Estabelecimento> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<EstabelecimentoDTO> listDto = list.map(obj -> new EstabelecimentoDTO(obj));

        return ResponseEntity.ok().body(listDto);
    }
}
