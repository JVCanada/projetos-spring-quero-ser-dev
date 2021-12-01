package br.com.rd.JVCrudFilms.service;

import br.com.rd.JVCrudFilms.model.dto.ActorDTO;
import br.com.rd.JVCrudFilms.model.entity.Actor;
import br.com.rd.JVCrudFilms.repository.contract.ActorRepository;
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

    // Método de adição:

    public ActorDTO addActor(ActorDTO dto) {
        Actor newActor = this.dtoToBusiness(dto);
        newActor.setLast_update(new Date());
        newActor = actorRepository.save(newActor);
        return businessToDto(newActor);
    }

    // Método de listar:

    public List<ActorDTO> showListActors() {
        List<Actor> allList = actorRepository.findAll();
        return listToDto(allList);
    }

    // Método de encontrar por id:

    public ActorDTO findActorById(Long id) {
        Optional<Actor> op = actorRepository.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    // Método de atualizar por id:

    public ActorDTO updateById(ActorDTO dto, Long id) {
        Optional<Actor> op = actorRepository.findById(id);

        if(op.isPresent()) {
            Actor obj = op.get();

            if(dto.getFirst_name() != null) {
                obj.setFirst_name(dto.getFirst_name());
            }

            if(dto.getLast_name() != null) {
                obj.setLast_name(dto.getLast_name());
            }

            obj.setLast_update(new Date());
            obj = this.actorRepository.save(obj);
            return businessToDto(obj);

        }

        return null;

    }

    // Método para deletar:

    public void deleteById(Long id) {
        if (this.actorRepository.existsById(id)) {
            this.actorRepository.deleteById(id);
        }
    }

    // Método para encontrar pelo nome:

    public List<ActorDTO> searchByName(String name) {
        List<Actor> actorList = this.actorRepository.myFindAllByActorsName(name);
        return listToDto(actorList);
    }

    // Métodos de conversão:

    private Actor dtoToBusiness(ActorDTO dto) {
        Actor business = new Actor();
        business.setFirst_name(dto.getFirst_name());
        business.setLast_name(dto.getLast_name());
        return business;
    }

    private ActorDTO businessToDto(Actor business) {
        ActorDTO dto = new ActorDTO();
        dto.setActor_id(business.getActor_id());
        dto.setFirst_name(business.getFirst_name());
        dto.setLast_name(business.getLast_name());
        dto.setLast_update(business.getLast_update());
        return dto;
    }

    private List<ActorDTO> listToDto(List<Actor> list) {
        List<ActorDTO> listDto = new ArrayList<>();
        for (Actor a: list) {
            listDto.add(this.businessToDto(a));
        }
        return listDto;
    }

}
