package br.com.rd.JVCrudFilms.service;

import br.com.rd.JVCrudFilms.model.dto.CategoryDTO;
import br.com.rd.JVCrudFilms.model.dto.CompositeKeyLanguageDTO;
import br.com.rd.JVCrudFilms.model.dto.FilmDTO;
import br.com.rd.JVCrudFilms.model.dto.LanguageDTO;
import br.com.rd.JVCrudFilms.model.embeddable.CompositeKeyLanguage;
import br.com.rd.JVCrudFilms.model.entity.Category;
import br.com.rd.JVCrudFilms.model.entity.Film;
import br.com.rd.JVCrudFilms.model.entity.Language;
import br.com.rd.JVCrudFilms.repository.contract.CategoryRepository;
import br.com.rd.JVCrudFilms.repository.contract.FilmRepository;
import br.com.rd.JVCrudFilms.repository.contract.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    // Método de adição:

    public FilmDTO addFilm(FilmDTO dto) {
        Film newFilm = this.dtoToBusiness(dto);

        if (newFilm.getLanguage() != null) {
            CompositeKeyLanguage key = new CompositeKeyLanguage();

            key.setCountry(dto.getLanguageDTO().getCompositeKeyLanguageDTO().getCountry());
            key.setName_language(dto.getLanguageDTO().getCompositeKeyLanguageDTO().getName_language());
            if (key != null) {
                Language lang = this.languageRepository.getById(key);
                lang.setLast_update(new Date());
                newFilm.setLanguage(lang);
            }
        }

        if (newFilm.getCategory() != null) {
            Long id = newFilm.getCategory().getCategory_id();
            if (id != null) {
                Category c = this.categoryRepository.getById(id);
                c.setLast_update(new Date());
                newFilm.setCategory(c);
            }
        }

        newFilm.setLast_update(new Date());
        newFilm = filmRepository.save(newFilm);
        return this.businessToDto(newFilm);

    }

    // Método de listar filmes:

    public List<FilmDTO> findAllFilms() {
        List<Film> allList = filmRepository.findAll();
        return this.listToDto(allList);
    }

    // Método para encontrar por id:

    public FilmDTO searchById(Long id) {
        Optional<Film> op = filmRepository.findById(id);

        if(op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    // Método para atualizar:

    public FilmDTO updateById(FilmDTO dto, Long id) {

        Optional<Film> op = filmRepository.findById(id);

        if(op.isPresent()) {

            Film obj = op.get();

            if (dto.getTitle_film() != null) {
                obj.setTitle_film(dto.getTitle_film());
            }

            if(dto.getDescription() != null) {
                obj.setDescription(dto.getDescription());
            }

            if(dto.getRelease_year() != null) {
                obj.setRelease_year(dto.getRelease_year());
            }

            if(dto.getRental_rate() != null) {
                obj.setRental_rate(dto.getRental_rate());
            }

            if(dto.getCategoryDTO() != null) {
                Category c = new Category();
                if(dto.getCategoryDTO().getCategory_id() != null) {
                    c.setCategory_id(dto.getCategoryDTO().getCategory_id());
                } else {
                    c.setName_category(dto.getCategoryDTO().getName_category());
                    c.setLast_update(new Date());
                }
                obj.setCategory(c);
            }

            if(dto.getLanguageDTO() != null) {
                Language lang = new Language();
                CompositeKeyLanguage key = new CompositeKeyLanguage();

                if(dto.getLanguageDTO().getCompositeKeyLanguageDTO() != null) {
                    key.setCountry(dto.getLanguageDTO().getCompositeKeyLanguageDTO().getCountry());
                    key.setName_language(dto.getLanguageDTO().getCompositeKeyLanguageDTO().getName_language());
                    lang.setCompositeKeyLanguage(key);
                }
                obj.setLanguage(lang);
            }

            obj.setLast_update(new Date());
            filmRepository.save(obj);
            return businessToDto(obj);

        }
        return null;
    }

    // Método para deletar:

    public void deleteById(Long id) {
        if(filmRepository.existsById(id)) {
            filmRepository.deleteById(id);
        }
    }

    // Método para ordernar por ordem de lançamento:

    public List<FilmDTO> searchByYear() {
        List<Film> listFilms = this.filmRepository.myFindAllReleaseYear();
        return listToDto(listFilms);
    }

    // Método para ordernar pela descrição:

    public List<FilmDTO> searchByDesc() {
        List<Film> listFilms = this.filmRepository.findAllByOrderByDescriptionDesc();
        return listToDto(listFilms);
    }

    // Métodos de conversão:

    public Film dtoToBusiness(FilmDTO dto) {
        Film business = new Film();

        business.setTitle_film(dto.getTitle_film());
        business.setDescription(dto.getDescription());
        business.setRelease_year(dto.getRelease_year());

        if (dto.getLanguageDTO() != null) {
            Language lang = new Language();
            CompositeKeyLanguage key = new CompositeKeyLanguage();
            if (dto.getLanguageDTO().getCompositeKeyLanguageDTO() != null) {
                key.setCountry(dto.getLanguageDTO().getCompositeKeyLanguageDTO().getCountry());
                key.setName_language(dto.getLanguageDTO().getCompositeKeyLanguageDTO().getName_language());
            }
            lang.setCompositeKeyLanguage(key);
            business.setLanguage(lang);
        }

        business.setRental_rate(dto.getRental_rate());

        if (dto.getCategoryDTO() != null) {
            Category cg = new Category();
            if(dto.getCategoryDTO().getCategory_id() != null) {
                cg.setCategory_id(dto.getCategoryDTO().getCategory_id());
            } else {
                cg.setName_category(dto.getCategoryDTO().getName_category());
                cg.setLast_update(dto.getCategoryDTO().getLast_update());
            }

            business.setCategory(cg);
        }

        return business;
    }

    public FilmDTO businessToDto(Film business) {
        FilmDTO dto = new FilmDTO();

        dto.setFilm_id(business.getFilm_id());
        dto.setTitle_film(business.getTitle_film());
        dto.setDescription(business.getDescription());
        dto.setRelease_year(business.getRelease_year());

        if (business.getLanguage() != null) {
            LanguageDTO lang = new LanguageDTO();
            CompositeKeyLanguageDTO key = new CompositeKeyLanguageDTO();
            key.setCountry(business.getLanguage().getCompositeKeyLanguage().getCountry());
            key.setName_language(business.getLanguage().getCompositeKeyLanguage().getName_language());
            lang.setCompositeKeyLanguageDTO(key);
            dto.setLanguageDTO(lang);
        }

        dto.setRental_rate(business.getRental_rate());

        if (business.getCategory() != null) {
            CategoryDTO cg = new CategoryDTO();
            cg.setCategory_id(business.getCategory().getCategory_id());
            cg.setName_category(business.getCategory().getName_category());
            cg.setLast_update(business.getCategory().getLast_update());
            dto.setCategoryDTO(cg);
        }

        return dto;

    }

    public List<FilmDTO> listToDto(List<Film> list) {
        List<FilmDTO> listDto = new ArrayList<>();
        for (Film f: list) {
            listDto.add(this.businessToDto(f));
        }
        return listDto;
    }

}
