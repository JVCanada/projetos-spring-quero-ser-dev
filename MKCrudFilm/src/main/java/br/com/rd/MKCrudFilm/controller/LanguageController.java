package br.com.rd.MKCrudFilm.controller;


import br.com.rd.MKCrudFilm.model.dto.LanguageDTO;
import br.com.rd.MKCrudFilm.model.entity.Language;
import br.com.rd.MKCrudFilm.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @GetMapping
    public List<LanguageDTO> showAll(){

        return languageService.showAll();
    }

    @GetMapping("/find")
    public LanguageDTO findById(@RequestParam("name") String name, @RequestParam("country") String country){
        return languageService.findById(name, country);
    }

    @PutMapping
    public LanguageDTO update(@RequestParam("name") String name, @RequestParam("country") String country, @RequestBody LanguageDTO dto){
        return languageService.update(name, country, dto);
    }

    @PostMapping
    public LanguageDTO add(@RequestBody LanguageDTO dto){
        return languageService.add(dto);
    }

    @DeleteMapping()
    public void deleteById(@RequestParam("name") String name, @RequestParam("country") String country){
        languageService.delete(name, country);
    }
}
