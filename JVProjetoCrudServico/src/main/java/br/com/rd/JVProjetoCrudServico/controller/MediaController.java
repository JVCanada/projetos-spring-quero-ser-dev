package br.com.rd.JVProjetoCrudServico.controller;


import br.com.rd.JVProjetoCrudServico.model.dto.MediaDTO;
import br.com.rd.JVProjetoCrudServico.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    MediaService mS;

    @PostMapping
    public MediaDTO create(@RequestBody MediaDTO dto) {
        return mS.addMedia(dto);
    }

    @GetMapping
    public List<MediaDTO> showList() {
        return mS.showListMedia();
    }

    @GetMapping("/{id}")
    public MediaDTO find(@PathVariable("id") Long id) {    // Caminho dinamico!
        return mS.findMediaById(id);
    }

    @PutMapping("/{id}")
    public MediaDTO updateById(@RequestBody MediaDTO dto, @PathVariable("id") Long id) {
        return mS.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        mS.deleteById(id);
    }

}
