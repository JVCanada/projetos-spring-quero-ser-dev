package br.com.rd.CrudTask.controller;

import br.com.rd.CrudTask.model.dto.TaskDTO;
import br.com.rd.CrudTask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService tS;

    @PostMapping
    public TaskDTO create(@RequestBody TaskDTO dto) {
        return tS.addTask(dto);
    }

    @GetMapping
    public List<TaskDTO> showList() {
        return tS.showListTask();
    }

    @GetMapping("/{id}")
    public TaskDTO find(@PathVariable("id") Long id) {    // Caminho dinamico!
        return tS.findTaskById(id);
    }

    @PutMapping("/{id}")
    public TaskDTO updateById(@RequestBody TaskDTO dto, @PathVariable("id") Long id) {
        return tS.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        tS.deleteById(id);
    }

    // Método da controller para verificar a descrição e o feito:

    @GetMapping("/descricaoCompleta/{descricao}/{feito}")
    public List<TaskDTO> searchAllVehicles(@PathVariable("descricao") String descricao, @PathVariable("feito") Boolean feito) {
        return tS.searchDescricaoFeita(descricao, feito);
    }

    // Método que seleciona decrescente de data:

    @GetMapping("/selecionaDatas")
    public List<TaskDTO> selecionaDatas() {
        return tS.selecionaDataDesc();
    }

    // Método que seleciona decrescente de data por tarefas feitas:
    @GetMapping("/selecionaDatasProntas/{feito}")
    public List<TaskDTO> selecionaDatasProntas(@PathVariable("feito") Boolean feito) {
        return tS.selecionaDataDescTarefa(feito);
    }


}
