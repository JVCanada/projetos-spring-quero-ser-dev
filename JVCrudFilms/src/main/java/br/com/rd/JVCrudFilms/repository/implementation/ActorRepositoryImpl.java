package br.com.rd.JVCrudFilms.repository.implementation;

import br.com.rd.JVCrudFilms.model.entity.Actor;
import br.com.rd.JVCrudFilms.repository.contract.ActorRepository;
import br.com.rd.JVCrudFilms.repository.contract.ActorRepositoryCustom2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ActorRepositoryImpl implements ActorRepositoryCustom2 {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Actor> myFindAllByActorsName(String name) {
        Query sql = this.entityManager.createNativeQuery(
                "Select * from actor where first_name = :name ", Actor.class
        );
        sql.setParameter("name", name);

        List<Actor> actorList = sql.getResultList();
        return actorList;
    }
}
