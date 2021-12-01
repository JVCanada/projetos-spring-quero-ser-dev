package br.com.rd.MKCrudFilm.controller;

;
import br.com.rd.MKCrudFilm.model.dto.ActorDTO;
import br.com.rd.MKCrudFilm.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {
    @Autowired
    ActorService actorService;

    @GetMapping
    public List<ActorDTO> showAll(){
        return actorService.showAll();
    }

    @GetMapping("/{id}")
    public ActorDTO findById(@PathVariable("id") Long id){
        return actorService.findById(id);
    }

    @GetMapping("/find")
    public List<ActorDTO> findByName(@RequestParam("name") String name){
        return actorService.findByName(name);
    }

    @PutMapping("/{id}")
    public ActorDTO update(@PathVariable("id") Long id, @RequestBody ActorDTO dto){
        return actorService.update(id, dto);
    }

    @PostMapping
    public ActorDTO add(@RequestBody ActorDTO dto){
        return actorService.add(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        actorService.deleteById(id);
    }
}
