package br.com.rd.JVCrudFilms.controller;

import br.com.rd.JVCrudFilms.model.dto.FilmDTO;
import br.com.rd.JVCrudFilms.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    FilmService filmService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public FilmDTO create(@RequestBody FilmDTO dto) {
        return this.filmService.addFilm(dto);
    }

    @GetMapping
    public List<FilmDTO> showList() {
        return this.filmService.findAllFilms();
    }

    @GetMapping("/{id}")
    public FilmDTO find(@PathVariable("id") Long id) {
        return this.filmService.searchById(id);
    }

    @PutMapping("/{id}")
    public FilmDTO update(@RequestBody FilmDTO dto, @PathVariable("id") Long id) {
        return this.filmService.updateById(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        this.filmService.deleteById(id);
    }

    @GetMapping("/searchByYear")
    public List<FilmDTO> findByYear() {
        return this.filmService.searchByYear();
    }

    @GetMapping("/searchDesc")
    public List<FilmDTO> findDesc() {
        return this.filmService.searchByDesc();
    }

}
