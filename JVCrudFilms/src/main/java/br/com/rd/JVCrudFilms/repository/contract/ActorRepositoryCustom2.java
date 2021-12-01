package br.com.rd.JVCrudFilms.repository.contract;

import br.com.rd.JVCrudFilms.model.entity.Actor;

import java.util.List;

public interface ActorRepositoryCustom2 {

    List<Actor> myFindAllByActorsName(String name);
}
