package br.com.rd.CrudCarrinho.controller;

import br.com.rd.CrudCarrinho.model.dto.ItemDTO;
import br.com.rd.CrudCarrinho.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService iS;

    @PostMapping
    public ItemDTO create(@RequestBody ItemDTO dto) {
        return iS.addItem(dto);
    }

    @GetMapping
    public List<ItemDTO> showList() {
        return iS.showListItem();
    }

    @GetMapping("/{id}")
    public ItemDTO find(@PathVariable("id") Long id) {    // Caminho dinamico!
        return iS.findItemById(id);
    }

    @PutMapping("/{id}")
    public ItemDTO updateById(@RequestBody ItemDTO dto, @PathVariable("id") Long id) {
        return iS.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        iS.deleteById(id);
    }

}
