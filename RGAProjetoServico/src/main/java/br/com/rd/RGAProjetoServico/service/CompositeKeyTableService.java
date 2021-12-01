package br.com.rd.RGAProjetoServico.service;

import br.com.rd.RGAProjetoServico.model.dto.CompositeKeyDTO;
import br.com.rd.RGAProjetoServico.model.dto.CompositeKeyTableDTO;
import br.com.rd.RGAProjetoServico.model.embeddable.CompositeKey;
import br.com.rd.RGAProjetoServico.model.entity.CompositeKeyTable;
import br.com.rd.RGAProjetoServico.repository.contract.CompositeKeyTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompositeKeyTableService {
    @Autowired
    CompositeKeyTableRepository compositeKeyTableRepository;

    public CompositeKeyTableDTO add(CompositeKeyTableDTO model) throws SQLIntegrityConstraintViolationException {
        CompositeKeyTable comp = dtoToBusiness(model);
        if (compositeKeyTableRepository.existsById(comp.getCompositeKey())){
            throw new SQLIntegrityConstraintViolationException("Primary Key already exists!");
        }
        comp = compositeKeyTableRepository.save(comp);
        return businessToDto(comp);
    }

    public List<CompositeKeyTableDTO> findAll(){
        List<CompositeKeyTable> list = compositeKeyTableRepository.findAll();
        return listToDto(list);
    }

    public CompositeKeyTableDTO searchId(Long id, String name){
        CompositeKey key = new CompositeKey();
        key.setId(id);
        key.setName(name);
        Optional<CompositeKeyTable> vehicle = compositeKeyTableRepository.findById(key);

        if (vehicle.isPresent()){
            return businessToDto(vehicle.get());
        }

        return null;
    }

    private List<CompositeKeyTableDTO> listToDto(List<CompositeKeyTable> list){
        List<CompositeKeyTableDTO> listDto = new ArrayList<CompositeKeyTableDTO>();
        for (CompositeKeyTable v: list) {
            listDto.add(this.businessToDto(v));
        }
        return listDto;
    }

    public List<CompositeKeyTableDTO> searchAllById (Long id){
        List<CompositeKeyTable> list = compositeKeyTableRepository.findByCompositeKeyId(id);
        return listToDto(list);
    }

    private CompositeKeyTableDTO businessToDto(CompositeKeyTable comp){
        CompositeKeyTableDTO dto = new CompositeKeyTableDTO();
        CompositeKeyDTO key = new CompositeKeyDTO();

        key.setId(comp.getCompositeKey().getId());
        key.setName(comp.getCompositeKey().getName());

        dto.setCompositeKey(key);
        dto.setAnotherName(comp.getAnotherName());
        return dto;
    }

    private CompositeKeyTable dtoToBusiness(CompositeKeyTableDTO compDTO){
        CompositeKeyTable business = new CompositeKeyTable();
        CompositeKey key = new CompositeKey();

        key.setName(compDTO.getCompositeKey().getName());
        key.setId(compDTO.getCompositeKey().getId());

        business.setCompositeKey(key);
        business.setAnotherName(compDTO.getAnotherName());
        return business;
    }

}
