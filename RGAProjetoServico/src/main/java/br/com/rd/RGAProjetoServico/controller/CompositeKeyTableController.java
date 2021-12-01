package br.com.rd.RGAProjetoServico.controller;


import br.com.rd.RGAProjetoServico.model.dto.CompositeKeyTableDTO;
import br.com.rd.RGAProjetoServico.model.dto.VehicleDTO;
import br.com.rd.RGAProjetoServico.model.entity.CompositeKeyTable;
import br.com.rd.RGAProjetoServico.service.CompositeKeyTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/composites")
public class CompositeKeyTableController {

    @Autowired
    CompositeKeyTableService compositeKeyTableService;

    @PostMapping
    public CompositeKeyTableDTO create(@RequestBody CompositeKeyTableDTO vehicle){
        try {
            return compositeKeyTableService.add(vehicle);
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping
    public List<CompositeKeyTableDTO> findAll(){
        return compositeKeyTableService.findAll();
    }

    @GetMapping("/{id}/{name}")
    public CompositeKeyTableDTO searchId(@PathVariable("id") Long id, @PathVariable("name") String name){
        return compositeKeyTableService.searchId(id, name);
    }

    @GetMapping("/{id}")
    public List<CompositeKeyTableDTO> searchId(@PathVariable("id") Long id){
        return compositeKeyTableService.searchAllById(id);
    }

}
