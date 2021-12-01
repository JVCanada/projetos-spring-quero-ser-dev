package br.com.rd.MKCrudFilm.repository.contract;

import br.com.rd.MKCrudFilm.model.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor findByFirstNameAndLastName(String firstName, String lastName);

    @Query(value = "SELECT * FROM TB_ACTOR TA WHERE TA.FIRST_NAME LIKE %:search% OR TA.LAST_NAME LIKE %:search%", nativeQuery = true)
    List<Actor> findByFirstOrLastNameLike(@Param("search") String search);
}
