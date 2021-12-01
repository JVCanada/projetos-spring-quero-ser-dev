package br.com.rd.MKCrudFilm.service;

import br.com.rd.MKCrudFilm.model.dto.ActorDTO;
import br.com.rd.MKCrudFilm.model.entity.Actor;
import br.com.rd.MKCrudFilm.model.entity.Film;
import br.com.rd.MKCrudFilm.repository.contract.ActorRepository;
import br.com.rd.MKCrudFilm.repository.contract.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {
    @Autowired
    ActorRepository actorRepository;

    @Autowired
    FilmRepository filmRepository;

    public ActorDTO add(ActorDTO dto){
        Actor newActor = dtoToBusiness(dto);
        newActor.setLastUpdate(new Date());
        newActor = actorRepository.save(newActor);

        return businessToDto(newActor);
    }

    public ActorDTO update(Long id, ActorDTO dto){
        Optional<Actor> op = actorRepository.findById(id);

        if(op.isPresent()){
            Actor updateActor = op.get();

            if(dto.getFirstName() != null){
                updateActor.setFirstName(dto.getFirstName());
            }
            if(dto.getLastName() != null){
                updateActor.setLastName(dto.getLastName());
            }
            updateActor.setLastUpdate(new Date());
            updateActor = actorRepository.save(updateActor);
            return businessToDto(updateActor);
        }
        return null;
    }

    public List<ActorDTO> showAll(){
        List<Actor> list = actorRepository.findAll();
        return listToDto(list);
    }

    public ActorDTO findById(Long id){
        Optional<Actor> op = actorRepository.findById(id);

        if(op.isPresent()){
            Actor actor = op.get();
            return businessToDto(actor);
        }
        return null;
    }


    public void deleteById(Long id){
        if(actorRepository.existsById(id)){
            Actor removeActor = actorRepository.getById(id);
            List<Film> listFilms = filmRepository.findAllFilmsByActorId(id);
            if(listFilms != null){
                for(Film film : listFilms){
                    film.getActors().remove(removeActor);
                    film.setLastUpdate(new Date());
                    filmRepository.save(film);
                }
            }
            actorRepository.deleteById(id);
        }
    }

    public List<ActorDTO> findByName(String search){
        List<Actor> list = actorRepository.findByFirstOrLastNameLike(search);
        return listToDto(list);
    }

    //CONVERSOES

    private ActorDTO businessToDto(Actor actor){
        ActorDTO dto = new ActorDTO();

        dto.setId(actor.getId());
        dto.setFirstName(actor.getFirstName());
        dto.setLastName(actor.getLastName());
        dto.setLastUpdate(actor.getLastUpdate());

        return dto;
    }

    private Actor dtoToBusiness(ActorDTO dto){
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

    private List<ActorDTO> listToDto(List<Actor> list){
        List<ActorDTO> listDto = new ArrayList<ActorDTO>();
        for (Actor a : list){
            listDto.add(businessToDto(a));
        }
        return listDto;
    }
}
