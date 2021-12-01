package br.com.rd.MKCrudFilm.repository.contract;

import br.com.rd.MKCrudFilm.model.entity.Film;

import java.util.List;

public interface FilmRepositoryCustom {
    List<Film> findAllFilmsByActorId(Long id);
}
