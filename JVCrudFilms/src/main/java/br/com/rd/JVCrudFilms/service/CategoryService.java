package br.com.rd.JVCrudFilms.service;

import br.com.rd.JVCrudFilms.model.dto.CategoryDTO;
import br.com.rd.JVCrudFilms.model.entity.Category;
import br.com.rd.JVCrudFilms.repository.contract.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    // Método de adição:

    public CategoryDTO addCategory(CategoryDTO dto) {
        Category newCategory = this.dtoToBusiness(dto);
        newCategory.setLast_update(new Date());
        newCategory = categoryRepository.save(newCategory);
        return businessToDto(newCategory);
    }

    // Método de listar:

    public List<CategoryDTO> showListCategories() {
        List<Category> allList = categoryRepository.findAll();
        return listToDto(allList);
    }

    // Método de encontrar por id:

    public CategoryDTO findCategoryById(Long id) {
        Optional<Category> op = categoryRepository.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    // Método de atualizar por id:

    public CategoryDTO updateById(CategoryDTO dto, Long id) {
        Optional<Category> op = categoryRepository.findById(id);

        if(op.isPresent()) {

            Category obj = op.get();

            if(dto.getName_category() != null) {
                obj.setName_category(dto.getName_category());
            }

            obj.setLast_update(new Date());
            obj = this.categoryRepository.save(obj);
            return businessToDto(obj);

        }

        return null;
    }

    // Método de deletar:

    public void deleteById(Long id) {
        if (this.categoryRepository.existsById(id)) {
            this.categoryRepository.deleteById(id);
        }
    }

    // Métodos de conversão:

    private Category dtoToBusiness(CategoryDTO dto) {
        Category business = new Category();
        business.setName_category(dto.getName_category());
        return business;
    }

    private CategoryDTO businessToDto(Category business) {
        CategoryDTO dto = new CategoryDTO();
        dto.setCategory_id(business.getCategory_id());
        dto.setName_category(business.getName_category());
        dto.setLast_update(business.getLast_update());
        return dto;
    }

    private List<CategoryDTO> listToDto(List<Category> list) {
        List<CategoryDTO> listDto = new ArrayList<>();
        for (Category c : list) {
            listDto.add(this.businessToDto(c));
        }
        return listDto;
    }

}
