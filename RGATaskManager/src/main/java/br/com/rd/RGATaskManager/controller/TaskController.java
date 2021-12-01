package br.com.rd.RGATaskManager.controller;

import br.com.rd.RGATaskManager.model.dto.TaskDTO;
import br.com.rd.RGATaskManager.model.entity.Task;
import br.com.rd.RGATaskManager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TaskDTO createTask (@RequestBody TaskDTO taskDTO){
        return this.taskService.create(taskDTO);
    }

    @GetMapping
    public List<TaskDTO> findAllTasks(){
        return this.taskService.findAllTasks();
    }

    @GetMapping("/{chuchu}")
    public TaskDTO findById(@PathVariable("chuchu") Long abobrinha){
        return this.taskService.findById(abobrinha);
    }

    @PutMapping("/{chuchu}")
    public TaskDTO updateById(@PathVariable("chuchu") Long abobrinha, @RequestBody TaskDTO dto){
        return this.taskService.updateById(dto, abobrinha);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id){
        this.taskService.deleteById(id);
    }

    @GetMapping("/done")
    public List<TaskDTO> searchByDescriptionDone(@RequestParam("description") String desc){
        return this.taskService.tomate(desc);
    }

    @GetMapping("/done2")
    public List<TaskDTO> searchByDescriptionDone2(@RequestParam("description") String desc){
        return this.taskService.findByDescriptionCustom2(desc);
    }

    @GetMapping("/alltaskdone")
    public List<TaskDTO> searchAllTaskDone(){
        return this.taskService.findAllTaskDone();
    }

    @GetMapping("/alltaskordered")
    public List<TaskDTO> searchAllTaskOrdered(){
        return this.taskService.findAllTaskOrdered();
    }

    @GetMapping("/alltaskordered2")
    public List<TaskDTO> searchAllTaskOrdered2(){
        return this.taskService.findAllTaskOrdered2();
    }

    @GetMapping("/between")
    public List<TaskDTO> searchAllTaskOrdered2(@RequestParam("date") String date){
        return this.taskService.findAllTaskBetween(date);
    }
}
