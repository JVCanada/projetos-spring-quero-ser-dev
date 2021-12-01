package br.com.rd.MKCrudFilm.repository.contract;

import br.com.rd.MKCrudFilm.model.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, FilmRepositoryCustom {

}
