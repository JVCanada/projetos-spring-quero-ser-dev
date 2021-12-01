package br.com.rd.MKCrudFilm.service;

import br.com.rd.MKCrudFilm.model.dto.CategoryDTO;
import br.com.rd.MKCrudFilm.model.entity.Category;
import br.com.rd.MKCrudFilm.repository.contract.CategoryRepository;
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

    public CategoryDTO add(CategoryDTO dto){
        Category newCat = dtoToBusiness(dto);
        newCat.setLastUpdate(new Date());
        newCat = categoryRepository.save(newCat);
        return businessToDto(newCat);
    }

    public CategoryDTO update(Long id, CategoryDTO dto){
        Optional<Category> op = categoryRepository.findById(id);
        if(op.isPresent()){
            Optional<Category> opSameName = categoryRepository.findCatIdByName(dto.getName());
            if(opSameName.isPresent()){ //se já existe uma categoria com o mesmo nome
                Category updateCat = opSameName.get();
                updateCat.setLastUpdate(new Date());
                updateCat = categoryRepository.save(updateCat);
                return businessToDto(opSameName.get());
            }

            Category updateCat = op.get();
            if(dto.getName() != null){
                updateCat.setName(dto.getName());
            }
            updateCat.setLastUpdate(new Date());
            updateCat = categoryRepository.save(updateCat);
            return businessToDto(updateCat);
        }
        return null;
    }

    public void delete(Long id){
        List<Long> filmIds = categoryRepository.findFilmIdByCategoryId(id);
        if(filmIds.size() > 0) {
            System.out.println("Impossível deletar categoria. Categoria usada nos filmes com ID:");
            for (Long l : filmIds) {
                System.out.println(l);
            }
        } else {
            if (categoryRepository.existsById(id)) {
                categoryRepository.deleteById(id);
            }
        }
    }

    public List<CategoryDTO> showAll(){
        List<Category> list = categoryRepository.findAll();
        return listToDto(list);
    }

    public CategoryDTO findById(Long id){
        Optional<Category> op = categoryRepository.findById(id);

        if(op.isPresent()){
            return businessToDto(op.get());
        }
        return null;
    }



    //CONVERSOES

    private List<CategoryDTO> listToDto(List<Category> list){
        List<CategoryDTO> listDto = new ArrayList<CategoryDTO>();

        for (Category c : list){
            listDto.add(businessToDto(c));
        }
        return listDto;
    }

    private Category dtoToBusiness(CategoryDTO dto){
        Category category = new Category();
        Optional<Category> op = categoryRepository.findCatIdByName(dto.getName());

        if(op.isPresent()){
            return op.get();
        }
        category.setName(dto.getName());
        return category;
    }

    private CategoryDTO businessToDto(Category category){
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setLastUpdate(category.getLastUpdate());
        return dto;
    }
}
