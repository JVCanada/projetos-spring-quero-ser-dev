package br.com.rd.JVCrudFilms.controller;


import br.com.rd.JVCrudFilms.model.dto.ActorDTO;
import br.com.rd.JVCrudFilms.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    ActorService actorService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ActorDTO create (@RequestBody ActorDTO dto) {
        return this.actorService.addActor(dto);
    }

    @GetMapping
    public List<ActorDTO> showList() {
        return this.actorService.showListActors();
    }

    @GetMapping("/{id}")
    public ActorDTO find(@PathVariable("id") Long id) {
        return this.actorService.findActorById(id);
    }

    @PutMapping("/{id}")
    public ActorDTO update(@RequestBody ActorDTO dto, @PathVariable("id") Long id ) {
        return actorService.updateById(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        actorService.deleteById(id);
    }

    @GetMapping("/searchActors/{name}")
    public List<ActorDTO> searchByName(@PathVariable("name") String name) {
        return this.actorService.searchByName(name);
    }

}
