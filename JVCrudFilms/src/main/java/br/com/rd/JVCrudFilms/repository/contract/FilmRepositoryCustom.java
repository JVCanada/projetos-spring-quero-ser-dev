package br.com.rd.JVCrudFilms.repository.contract;

import br.com.rd.JVCrudFilms.model.entity.Film;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmRepositoryCustom {

    @Query(value = "Select * from film order by release_year asc", nativeQuery = true)
    List<Film> myFindAllReleaseYear();
}
