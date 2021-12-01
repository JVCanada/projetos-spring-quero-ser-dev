package br.com.rd.MKCrudFilm.repository.contract;

import br.com.rd.MKCrudFilm.model.entity.Category;
import br.com.rd.MKCrudFilm.model.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM TB_CATEGORY TC WHERE TC.NAME = :name", nativeQuery = true)
    Optional<Category> findCatIdByName(@Param("name") String name);

    @Query(value = "SELECT TF.ID FROM TB_FILM TF WHERE TF.CATEGORY_ID = :id",nativeQuery = true)
    List<Long> findFilmIdByCategoryId(@Param("id") Long catId);

}
