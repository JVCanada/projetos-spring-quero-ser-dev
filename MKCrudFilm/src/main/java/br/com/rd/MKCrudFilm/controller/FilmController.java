package br.com.rd.MKCrudFilm.controller;

import br.com.rd.MKCrudFilm.model.dto.ActorDTO;
import br.com.rd.MKCrudFilm.model.dto.ActorFilmsDTO;
import br.com.rd.MKCrudFilm.model.dto.FilmDTO;
import br.com.rd.MKCrudFilm.model.entity.Film;
import br.com.rd.MKCrudFilm.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {
    @Autowired
    FilmService filmService;

    @GetMapping
    public List<FilmDTO> showAll(){
        return filmService.showAll();
    }

    @GetMapping("/{id}")
    public FilmDTO findById(@PathVariable("id") Long id){
        return filmService.findById(id);
    }

    @GetMapping("/actor/{id}")
    public ActorFilmsDTO showAllFilmsByActorId(@PathVariable("id") Long id){
        return filmService.showAllFilmsByActorId(id);
    }

    @PutMapping("/{id}")
    public FilmDTO update(@PathVariable("id") Long id, @RequestBody FilmDTO dto){
        return filmService.update(id, dto);
    }

    @PutMapping("/deleteActor")
    public void deleteActorFromFilm(@RequestParam("idFilm") Long idFilm, @RequestParam("idActor") Long idActor){
        filmService.deleteActorFromFilm(idFilm, idActor);
    }

    @PostMapping
    public FilmDTO add(@RequestBody FilmDTO dto){
        return filmService.add(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        filmService.delete(id);
    }
}
