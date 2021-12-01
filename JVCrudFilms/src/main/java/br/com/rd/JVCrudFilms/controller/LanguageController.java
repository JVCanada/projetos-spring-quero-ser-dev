package br.com.rd.JVCrudFilms.controller;

import br.com.rd.JVCrudFilms.model.dto.LanguageDTO;
import br.com.rd.JVCrudFilms.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public LanguageDTO create(@RequestBody LanguageDTO dto) {
        try {
            return this.languageService.addLanguage(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping
    public List<LanguageDTO> showList() {
        return this.languageService.showListLanguages();
    }

    @GetMapping("/{name}/{country}")
    public LanguageDTO find(@PathVariable("name") String name, @PathVariable("country") String country) {
        return this.languageService.searchByIds(name, country);
    }

    @PutMapping("/{name}/{country}")
    public LanguageDTO update(@RequestBody LanguageDTO dto, @PathVariable("name") String name, @PathVariable("country") String country) {
        return this.languageService.updateByIds(dto, name, country);
    }

    @DeleteMapping("/{name}/{country}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("name") String name, @PathVariable("country") String country) {
        this.languageService.deleteById(name, country);
    }

}
