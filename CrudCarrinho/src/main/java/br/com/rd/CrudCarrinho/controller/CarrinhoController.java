package br.com.rd.CrudCarrinho.controller;

import br.com.rd.CrudCarrinho.model.dto.CarrinhoDTO;
import br.com.rd.CrudCarrinho.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    CarrinhoService cS;

    @PostMapping
    public CarrinhoDTO create(@RequestBody CarrinhoDTO dto) {
        return cS.addCarrinho(dto);
    }

    @GetMapping
    public List<CarrinhoDTO> showList() {
        return cS.showListCarrinho();
    }

    @GetMapping("/{id}")
    public CarrinhoDTO find(@PathVariable("id") Long id) {    // Caminho dinamico!
        return cS.findCarrinhoById(id);
    }

    @PutMapping("/{id}")
    public CarrinhoDTO updateById(@RequestBody CarrinhoDTO dto, @PathVariable("id") Long id) {
        return cS.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        cS.deleteById(id);
    }

}
