package br.com.rd.MKCrudFilm.service;


import br.com.rd.MKCrudFilm.model.dto.*;
import br.com.rd.MKCrudFilm.model.embeddable.LanguageCompositeKey;
import br.com.rd.MKCrudFilm.model.entity.Actor;
import br.com.rd.MKCrudFilm.model.entity.Category;
import br.com.rd.MKCrudFilm.model.entity.Film;
import br.com.rd.MKCrudFilm.model.entity.Language;
import br.com.rd.MKCrudFilm.repository.contract.ActorRepository;
import br.com.rd.MKCrudFilm.repository.contract.CategoryRepository;
import br.com.rd.MKCrudFilm.repository.contract.FilmRepository;
import br.com.rd.MKCrudFilm.repository.contract.LanguageRepository;
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

    @Autowired
    ActorRepository actorRepository;

    public FilmDTO add(FilmDTO dto){
        Film newFilm = dtoToBusiness(dto);

        if(newFilm.getCategory().getLastUpdate() == null){
            newFilm.getCategory().setLastUpdate(new Date());
        }
        if(newFilm.getLanguage().getLastUpdate() == null){
            newFilm.getLanguage().setLastUpdate(new Date());
        }
        if(newFilm.getActors() != null){
            List<Actor> listActors = newFilm.getActors();
            for (Actor a : listActors){
                if(a.getLastUpdate() == null){
                    a.setLastUpdate(new Date());
                }
            }
        }
        newFilm.setLastUpdate(new Date());


        newFilm = filmRepository.save(newFilm);
        return businessToDto(newFilm);
    }

    public FilmDTO update(Long id, FilmDTO dto){
        Optional<Film> op = filmRepository.findById(id);

        if(op.isPresent()){
            Film updateFilm = op.get();

            if(dto.getTitle() != null){
                updateFilm.setTitle(dto.getTitle());
            }
            if(dto.getDescription() != null){
                updateFilm.setDescription(dto.getDescription());
            }
            if(dto.getReleaseYear() != null){
                updateFilm.setReleaseYear(dto.getReleaseYear());
            }
            if(dto.getLanguage() != null){
                Language lang = langDtoToBusiness(dto.getLanguage());
                if(lang.getLastUpdate() == null){
                    lang.setLastUpdate(new Date());
                }
                updateFilm.setLanguage(lang);
            }
            if(dto.getRentalRate() != null){
                updateFilm.setRentalRate(dto.getRentalRate());
            }
            if(dto.getCategory() != null){
                Category cat = catDtoToBusiness(dto.getCategory());
                if(cat.getLastUpdate() == null){
                    cat.setLastUpdate(new Date());
                }
                updateFilm.setCategory(cat);
            }
            if(dto.getActors() != null){
                List<Actor> listActorFromDto = actorListFromDto(dto.getActors());
                List<Actor> listActor = updateFilm.getActors();
                for (Actor a : listActorFromDto){
                    a.setLastUpdate(new Date());
                    a = actorRepository.save(a);
                    listActor.add(a);
                }
                updateFilm.setActors(listActor);
            }
            updateFilm.setLastUpdate(new Date());

            filmRepository.save(updateFilm);
            return businessToDto(updateFilm);
        }
        return null;
    }

    public void delete(Long id){
        if(filmRepository.existsById(id)){
            filmRepository.deleteById(id);
        }
    }

    public List<FilmDTO> showAll(){
        List<Film> list = filmRepository.findAll();
        return listToDto(list);
    }

    public FilmDTO findById(Long id){
        Optional<Film> op = filmRepository.findById(id);

        if(op.isPresent()){
            return businessToDto(op.get());
        }
        return null;
    }

    public ActorFilmsDTO showAllFilmsByActorId(Long id){
        List<Film> list = filmRepository.findAllFilmsByActorId(id);
        if(list != null){
            return filmListToActFilmsDTO(list, id);
        }
        return null;
    }

    public void deleteActorFromFilm(Long idFilm, Long idActor){
        List<Film> listFilms = filmRepository.findAllFilmsByActorId(idActor);
        Optional<Actor> actOp = actorRepository.findById(idActor);

        if(listFilms.size() > 0 && actOp.isPresent()){
            Actor removeActor = actOp.get();

            for (Film film : listFilms){
                if(film.getId() == idFilm){
                    film.getActors().remove(removeActor);
                    film.setLastUpdate(new Date());
                    filmRepository.save(film);
                    break;
                }
            }
        }
    }


    //CONVERSORES INICIO
    //CONVERSORES DE LISTAS INICIO
    private List<FilmDTO> listToDto(List<Film> list){
        List<FilmDTO> listDto = new ArrayList<FilmDTO>();

        for (Film f : list){
            listDto.add(businessToDto(f));
        }
        return listDto;
    }



    private List<ActorDTO> actorListToDto(List<Actor> list){
        List<ActorDTO> listDto = new ArrayList<ActorDTO>();

        for (Actor a : list){
            listDto.add(actorBusinessToDto(a));
        }
        return listDto;
    }

    private List<Actor> actorListFromDto(List<ActorDTO> listDto){
        List<Actor> listActor = new ArrayList<Actor>();

        for(ActorDTO dto : listDto){
            listActor.add(actorDtoToBusiness(dto));
        }
        return listActor;
    }
    private ActorFilmsDTO filmListToActFilmsDTO(List<Film> listFilms, Long actorId){
        List<ActorFilmsDTO> listDto = new ArrayList<ActorFilmsDTO>();
        Actor actor = actorRepository.getById(actorId);

        ActorFilmsDTO dto = new ActorFilmsDTO();
        dto.setActorId(actorId);
        dto.setFullName(actor.getFirstName() + " " + actor.getLastName());

        for(Film film : listFilms){
            FilmShortDTO filmDto = new FilmShortDTO();
            filmDto.setFilmId(film.getId());
            filmDto.setTitle(film.getTitle());
            filmDto.setReleaseYear(film.getReleaseYear());
            filmDto.setDescription(film.getDescription());
            dto.getFilms().add(filmDto);
        }
        return dto;
    }

    //CONVERSORES DE LISTAS FIM
    //CONVERSORES DE ACTOR INICIO

    private ActorDTO actorBusinessToDto(Actor actor){
        ActorDTO dto = new ActorDTO();

        dto.setId(actor.getId());
        dto.setFirstName(actor.getFirstName());
        dto.setLastName(actor.getLastName());
        dto.setLastUpdate(actor.getLastUpdate());

        return dto;

    }

    private Actor actorDtoToBusiness(ActorDTO dto){
        Actor actor = new Actor();

        if(dto.getId() != null){
            Long idActor = dto.getId();
            if(actorRepository.existsById(idActor)){ //se foi passado um id valido
                actor = actorRepository.getById(idActor);
            } else if(actorRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName()) != null){
                actor = actorRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
            }
            else { //se id passado for invalido e ator não existe na base
                actor.setFirstName(dto.getFirstName());
                actor.setLastName(dto.getLastName());
            }
        } else { //não foi passado id
            if(actorRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName()) != null){
                actor = actorRepository.findByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());
            } else { //nao foi passado id e ator nao existe na base
                actor.setFirstName(dto.getFirstName());
                actor.setLastName(dto.getLastName());
            }

        }

        return actor;
    }

    //CONVERSORES DE ACTOR FIM
    //CONVERSORES DE LANG INICIO
    private Language langDtoToBusiness(LanguageDTO dto){
        Language lang = new Language();
        LanguageCompositeKey langCk = new LanguageCompositeKey();

        langCk.setName(dto.getLanguageCompositeKey().getName());
        langCk.setCountry(dto.getLanguageCompositeKey().getCountry());
        if(languageRepository.existsById(langCk)){
            lang = languageRepository.getById(langCk);
        } else {
            lang.setLanguageCompositeKey(langCk);
        }
        return lang;
    }

    private LanguageDTO langBusinessToDto(Language lang){
        LanguageDTO langDto = new LanguageDTO();
        LanguageCompositeKeyDTO langCkDto = new LanguageCompositeKeyDTO();

        langCkDto.setName(lang.getLanguageCompositeKey().getName());
        langCkDto.setCountry(lang.getLanguageCompositeKey().getCountry());
        langDto.setLanguageCompositeKey(langCkDto);
        langDto.setLastUpdate(lang.getLastUpdate());

        return langDto;
    }
    //CONVERSORES DE LANG FIM
    //CONVERSORES DE CATEGORY INICIO

    private Category catDtoToBusiness(CategoryDTO dto){
        Category cat = new Category();

        if(dto.getId() != null){
            Long catId = dto.getId();
            if(categoryRepository.existsById(catId)){
                cat = categoryRepository.getById(catId);
            }
            else{
                cat.setName(dto.getName());
            }
        } else {
            cat.setName(dto.getName());
        }

        return cat;
    }

    private CategoryDTO catBusinessToDto(Category cat){
        CategoryDTO catDto = new CategoryDTO();

        catDto.setId(cat.getId());
        catDto.setName(cat.getName());
        catDto.setLastUpdate(cat.getLastUpdate());

        return catDto;
    }
    //CONVERSORES DE CATEGORY FIM
    //CONVERSORES DE FILM INICIO

    private Film dtoToBusiness(FilmDTO dto){
        Film film = new Film();
        Language lang = langDtoToBusiness(dto.getLanguage());
        Category cat = catDtoToBusiness(dto.getCategory());
        List<Actor> listActor = actorListFromDto(dto.getActors());

        film.setTitle(dto.getTitle());
        film.setDescription(dto.getDescription());
        film.setReleaseYear(dto.getReleaseYear());
        film.setLanguage(lang);
        film.setRentalRate(dto.getRentalRate());
        film.setCategory(cat);
        film.setActors(listActor);

        return film;
    }


    private FilmDTO businessToDto(Film film){
        FilmDTO filmDto = new FilmDTO();
        LanguageDTO langDto = langBusinessToDto(film.getLanguage());
        CategoryDTO catDto = catBusinessToDto(film.getCategory());
        List<ActorDTO> listActorDto = actorListToDto(film.getActors());

        filmDto.setId(film.getId());
        filmDto.setTitle(film.getTitle());
        filmDto.setDescription(film.getDescription());
        filmDto.setReleaseYear(film.getReleaseYear());
        filmDto.setLanguage(langDto);
        filmDto.setRentalRate(film.getRentalRate());
        filmDto.setLastUpdate(film.getLastUpdate());
        filmDto.setCategory(catDto);
        filmDto.setActors(listActorDto);

        return filmDto;
    }

    //CONVERSORES DE FILM FIM
    //CONVERSORES FIM
}
