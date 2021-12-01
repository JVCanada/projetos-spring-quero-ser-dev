package br.com.rd.JVCrudFilms.controller;

import br.com.rd.JVCrudFilms.model.dto.CategoryDTO;
import br.com.rd.JVCrudFilms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CategoryDTO create(@RequestBody CategoryDTO dto) {
        return this.categoryService.addCategory(dto);
    }

    @GetMapping
    public List<CategoryDTO> showList() {
        return this.categoryService.showListCategories();
    }

    @GetMapping("/{id}")
    public CategoryDTO find(@PathVariable("id") Long id) {
        return this.categoryService.findCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@RequestBody CategoryDTO dto, @PathVariable("id") Long id) {
        return this.categoryService.updateById(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        this.categoryService.deleteById(id);
    }

}
