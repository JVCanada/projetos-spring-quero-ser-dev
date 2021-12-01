package br.com.rd.MKCrudFilm.repository.implementation;


import br.com.rd.MKCrudFilm.model.entity.Film;
import br.com.rd.MKCrudFilm.repository.contract.FilmRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FilmRepositoryImpl implements FilmRepositoryCustom {
   @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<Film> findAllFilmsByActorId(Long id) {

        Query sql = entityManager.createNativeQuery("SELECT * " +
                "FROM (TB_FILM_ACTOR TFA INNER JOIN TB_FILM TF ON TF.ID = TFA.FILM_ID) " +
                "INNER JOIN TB_ACTOR TA ON TFA.ACTOR_ID = TA.ID WHERE TA.ID = :id " +
                        "ORDER BY TF.RELEASE_YEAR", Film.class);
        sql.setParameter("id", id);
        return sql.getResultList();
    }
}
