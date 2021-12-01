package br.com.rd.MKCrudFilm.repository.contract;

import br.com.rd.MKCrudFilm.model.embeddable.LanguageCompositeKey;
import br.com.rd.MKCrudFilm.model.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguageRepository extends JpaRepository<Language, LanguageCompositeKey> {
    @Query(value = "SELECT TF.ID FROM TB_FILM TF WHERE TF.LANGUAGE_COUNTRY = :country AND TF.LANGUAGE_NAME = :name ;", nativeQuery = true)
    List<Long> searchFilmIdByLanguageCK(@Param("country") String country, @Param("name") String name);
}
