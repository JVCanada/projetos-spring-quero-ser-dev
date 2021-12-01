package br.com.rd.JVProjetoCrudServico.service;


import br.com.rd.JVProjetoCrudServico.model.Entity.Media;
import br.com.rd.JVProjetoCrudServico.model.dto.MediaDTO;
import br.com.rd.JVProjetoCrudServico.repository.contract.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    @Autowired
    MediaRepository mr;

    // Método de adição:

    public MediaDTO addMedia(MediaDTO dto) {
        Media newMedia = this.dtoToBusiness(dto);
        newMedia = mr.save(newMedia);
        return businessToDto(newMedia);
    }

    // Método de listar media:

    public List<MediaDTO> showListMedia() {
        List<Media> allList = mr.findAll();
        return listToDto(allList);
    }

    // Método de encontrar por id:

    public MediaDTO findMediaById(Long id) {
        Optional<Media> op = mr.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    // Método de atualizar por id:

    public MediaDTO updateById(MediaDTO dto, Long id) {
        Optional<Media> op = mr.findById(id);

        if(op.isPresent()) {
            Media obj = op.get();

            if(dto.getDescription() != null) {
                obj.setDescription(dto.getDescription());
            }

            if(dto.getTouch() != null) {
                obj.setTouch(dto.getTouch());
            }

            mr.save(obj);
            return businessToDto(obj);
        }
        return null;
    }

    // Método de deletar:

    public void deleteById(Long id) {
        if(mr.existsById(id)) {
            mr.deleteById(id);
        }
    }

    // Métodos de conversão:

    private Media dtoToBusiness(MediaDTO dto) {
        Media business = new Media();
        business.setDescription(dto.getDescription());
        business.setTouch(dto.getTouch());
        return business;
    }

    private MediaDTO businessToDto(Media md) {
        MediaDTO dto = new MediaDTO();
        dto.setId(md.getId());
        dto.setDescription(md.getDescription());
        dto.setTouch(md.getTouch());
        return dto;
    }

    public List<MediaDTO> listToDto(List<Media> list) {
        List<MediaDTO> listDto = new ArrayList<MediaDTO>();
        for (Media m: list) {
            listDto.add(this.businessToDto(m));
        }
        return listDto;
    }

}
