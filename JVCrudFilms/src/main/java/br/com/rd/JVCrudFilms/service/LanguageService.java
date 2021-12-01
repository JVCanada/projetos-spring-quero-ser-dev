package br.com.rd.JVCrudFilms.service;

import br.com.rd.JVCrudFilms.model.dto.CompositeKeyLanguageDTO;
import br.com.rd.JVCrudFilms.model.dto.LanguageDTO;
import br.com.rd.JVCrudFilms.model.embeddable.CompositeKeyLanguage;
import br.com.rd.JVCrudFilms.model.entity.Language;
import br.com.rd.JVCrudFilms.repository.contract.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    LanguageRepository languageRepository;

    // Método de adição:

    public LanguageDTO addLanguage(LanguageDTO dto) throws SQLIntegrityConstraintViolationException {
        Language newLang = this.dtoToBusiness(dto);
        if (languageRepository.existsById(newLang.getCompositeKeyLanguage())) {
            throw new SQLIntegrityConstraintViolationException("Primary Key already exists!");
        }
        newLang.setLast_update(new Date());
        newLang = languageRepository.save(newLang);
        return businessToDto(newLang);
    }

    // Método de listar todos:

    public List<LanguageDTO> showListLanguages() {
        List<Language> allList = languageRepository.findAll();
        return listToDto(allList);
    }

    // Método de procurar pelo id:

    public LanguageDTO searchByIds(String name, String country) {
        CompositeKeyLanguage key = new CompositeKeyLanguage();
        key.setName_language(name);
        key.setCountry(country);
        Optional<Language> op = languageRepository.findById(key);

        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    // Método para atualizar:

    public LanguageDTO updateByIds(LanguageDTO dto, String name, String country) {
        CompositeKeyLanguage key = new CompositeKeyLanguage();
        key.setName_language(name);
        key.setCountry(country);
        Optional<Language> op = languageRepository.findById(key);

        if(op.isPresent()) {

            Language obj = op.get();

            if(dto.getCompositeKeyLanguageDTO() != null) {
                CompositeKeyLanguage ckp = new CompositeKeyLanguage();
                ckp.setName_language(dto.getCompositeKeyLanguageDTO().getName_language());
                ckp.setCountry(dto.getCompositeKeyLanguageDTO().getCountry());
                obj.setCompositeKeyLanguage(ckp);
            }

            obj.setLast_update(new Date());
            obj = this.languageRepository.save(obj);
            return businessToDto(obj);

        }
        return null;
    }

    // Método para deletar:

    public void deleteById(String name, String country) {
        CompositeKeyLanguage key = new CompositeKeyLanguage();
        key.setName_language(name);
        key.setCountry(country);
        if(this.languageRepository.existsById(key)) {
            this.languageRepository.deleteById(key);
        }
    }

    // Métodos de conversão:

    private Language dtoToBusiness(LanguageDTO dto) {
        Language business = new Language();
        CompositeKeyLanguage key = new CompositeKeyLanguage();

        key.setCountry(dto.getCompositeKeyLanguageDTO().getCountry());
        key.setName_language(dto.getCompositeKeyLanguageDTO().getName_language());

        business.setCompositeKeyLanguage(key);

        return business;
    }

    private LanguageDTO businessToDto(Language business) {
        LanguageDTO dto = new LanguageDTO();
        CompositeKeyLanguageDTO key = new CompositeKeyLanguageDTO();

        key.setCountry(business.getCompositeKeyLanguage().getCountry());
        key.setName_language(business.getCompositeKeyLanguage().getName_language());
        dto.setCompositeKeyLanguageDTO(key);
        dto.setLast_update(business.getLast_update());

        return dto;
    }

    private List<LanguageDTO> listToDto(List<Language> list) {
        List<LanguageDTO> listDto = new ArrayList<>();
        for (Language l: list) {
            listDto.add(this.businessToDto(l));
        }
        return listDto;
    }




}
