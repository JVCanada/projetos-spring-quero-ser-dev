package br.com.rd.RGAProjetoServico.service;

import br.com.rd.RGAProjetoServico.model.dto.MediaDTO;
import br.com.rd.RGAProjetoServico.model.entity.Media;
import br.com.rd.RGAProjetoServico.repository.contract.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaService {
    @Autowired
    MediaRepository mediaRepository;

    public MediaDTO create(MediaDTO media){
        Media model = dtoToBusiness(media);
        model = mediaRepository.save(model);
        return businessToDto(model);
    }

    public List<MediaDTO> findAll(){
        List<Media> responseList = mediaRepository.findAll();
        return getMediaDTOS(responseList);
    }

    public MediaDTO findById(Long id){
        Optional<Media> media = mediaRepository.findById(id);
        MediaDTO response = null;

        if (media.isPresent()){
            response = businessToDto(media.get());
        }

        return response;
    }

    private List<MediaDTO> getMediaDTOS(List<Media> list) {
        List<MediaDTO> dtoList = new ArrayList<>();
        for (Media c : list) {
            dtoList.add(businessToDto( c));
        }
        return dtoList;
    }

    public MediaDTO updateById(Long id, MediaDTO dto) {
        Optional<Media> find = mediaRepository.findById(id); //Vai retornar o objeto relativo com o tipo que foi cadastrado
        if (find.isPresent()) {
            if (find.get() instanceof Media) {
                Media c = find.get();
                c.setId(id);
                if (dto.getDescription() != null) {
                    c.setDescription(dto.getDescription());
                }
                if (dto.getTouch() != null) {
                    c.setTouch(dto.getTouch());
                }
                mediaRepository.save(c);
                return this.businessToDto(c);
            }
        }
        return null;
    }

    public void deleteById(Long id) {
        if (mediaRepository.existsById(id)){
            mediaRepository.deleteById(id);
        }
    }

    private MediaDTO businessToDto(Media media){
        MediaDTO dto = new MediaDTO();
        dto.setId(media.getId());
        dto.setDescription(media.getDescription());
        dto.setTouch(media.getTouch());
        return dto;
    }

    private Media dtoToBusiness(MediaDTO mediaDTO){
        Media business = new Media();
        business.setId(mediaDTO.getId());
        business.setDescription(mediaDTO.getDescription());
        business.setTouch(mediaDTO.getTouch());
        return business;
    }

}
