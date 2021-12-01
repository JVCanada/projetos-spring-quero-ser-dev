package br.com.rd.RGAProjetoServico.controller;

import br.com.rd.RGAProjetoServico.model.dto.MediaDTO;
import br.com.rd.RGAProjetoServico.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/medias")
public class MediaController {
    @Autowired
    MediaService mediaService;

    @PostMapping
    public MediaDTO create(@RequestBody MediaDTO media){
        return mediaService.create(media);
    }

    @GetMapping
    public List<MediaDTO> findAll(){
        return mediaService.findAll();
    }

    @GetMapping("/{id}")
    public MediaDTO searchById(@PathVariable("id") Long id){
        return mediaService.findById(id);
    }

    @PutMapping("/{id}")
    public MediaDTO updateById(@PathVariable("id") Long id, @RequestBody MediaDTO dto){
        return mediaService.updateById(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
        mediaService.deleteById(id);
    }
}
