package br.com.rd.MKCrudFilm.controller;

import br.com.rd.MKCrudFilm.model.dto.CategoryDTO;
import br.com.rd.MKCrudFilm.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> showAll(){
        return categoryService.showAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable("id") Long id){
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable("id") Long id, @RequestBody CategoryDTO dto){
        return categoryService.update(id, dto);
    }

    @PostMapping
    public CategoryDTO add(@RequestBody CategoryDTO dto){
        return categoryService.add(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        categoryService.delete(id);
    }
}
