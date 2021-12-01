package br.com.rd.MKCrudFilm.service;


import br.com.rd.MKCrudFilm.model.dto.LanguageCompositeKeyDTO;
import br.com.rd.MKCrudFilm.model.dto.LanguageDTO;
import br.com.rd.MKCrudFilm.model.embeddable.LanguageCompositeKey;
import br.com.rd.MKCrudFilm.model.entity.Language;
import br.com.rd.MKCrudFilm.repository.contract.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    public LanguageDTO add(LanguageDTO dto){
        Language newLang = dtoToBusiness(dto);
        newLang.setLastUpdate(new Date());
        newLang = languageRepository.save(newLang);
        return businessToDto(newLang);
    }

    public LanguageDTO update(String name, String country, LanguageDTO dto){
        List<Long> filmIds = languageRepository.searchFilmIdByLanguageCK(country, name);
        if(filmIds.size() > 0){
            System.out.println("Impossível alterar. Informações usadas nos filmes com ID:");
            for(Long l : filmIds){
                System.out.println(l);
            }
            return null;
        }
        LanguageCompositeKey ck = paramsToCompositeKey(name, country);
        Optional<Language> op = languageRepository.findById(ck);

        if(op.isPresent()){
            Language updateLang = op.get();
            LanguageCompositeKey ckUpdate = updateLang.getLanguageCompositeKey();
            languageRepository.deleteById(ck);

            if(dto.getLanguageCompositeKey().getCountry() != null){
                ckUpdate.setCountry(dto.getLanguageCompositeKey().getCountry());
            }
            if(dto.getLanguageCompositeKey().getName() != null){
                ckUpdate.setName(dto.getLanguageCompositeKey().getName());
            }
            updateLang.setLastUpdate(new Date());
            updateLang.setLanguageCompositeKey(ckUpdate);

            languageRepository.save(updateLang);
            return businessToDto(updateLang);
        }
        return null;
    }

    public void delete(String name, String country){
        LanguageCompositeKey ck = paramsToCompositeKey(name, country);
        List<Long> filmIds = languageRepository.searchFilmIdByLanguageCK(country, name);
        if(filmIds.size() > 0){
            System.out.println("Impossível alterar campos de língua. Informações usadas nos filmes com ID:");
            for(Long l : filmIds){
                System.out.println(l);
            }
        } else {
            if(languageRepository.existsById(ck)){
                languageRepository.deleteById(ck);
            }
        }

    }

    public List<LanguageDTO> showAll(){
        List<Language> list = languageRepository.findAll();
        return listToDto(list);
    }

    public LanguageDTO findById(String name, String country){
        LanguageCompositeKey ck = paramsToCompositeKey(name, country);
        Optional<Language> op = languageRepository.findById(ck);

        if(op.isPresent()){
            return businessToDto(op.get());
        }
        return null;
    }

    private List<LanguageDTO> listToDto(List<Language> list){
        List<LanguageDTO> listDto = new ArrayList<LanguageDTO>();

        for (Language c : list){
            listDto.add(businessToDto(c));
        }
        return listDto;
    }

    private LanguageCompositeKey paramsToCompositeKey(String name, String country){
        LanguageCompositeKey ck = new LanguageCompositeKey();
        ck.setName(name);
        ck.setCountry(country);
        return ck;
    }

    private Language dtoToBusiness(LanguageDTO dto){
        Language language = new Language();
        LanguageCompositeKey ck = new LanguageCompositeKey();
        ck.setName(dto.getLanguageCompositeKey().getName());
        ck.setCountry(dto.getLanguageCompositeKey().getCountry());
        language.setLanguageCompositeKey(ck);
        return language;
    }

    private LanguageDTO businessToDto(Language language){
        LanguageDTO dto = new LanguageDTO();
        LanguageCompositeKeyDTO ckDto = new LanguageCompositeKeyDTO();
        ckDto.setCountry(language.getLanguageCompositeKey().getCountry());
        ckDto.setName(language.getLanguageCompositeKey().getName());
        dto.setLanguageCompositeKey(ckDto);
        dto.setLastUpdate(language.getLastUpdate());
        return dto;
    }
}
