package br.com.rd.JVCrudFilms.repository.contract;

import br.com.rd.JVCrudFilms.model.embeddable.CompositeKeyLanguage;
import br.com.rd.JVCrudFilms.model.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, CompositeKeyLanguage> {
}
