package br.com.rd.JVCrudFilms.repository.contract;

import br.com.rd.JVCrudFilms.model.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long>, FilmRepositoryCustom{

    List<Film> findAllByOrderByDescriptionDesc();
}
