package br.com.rd.CrudTask.service;


import br.com.rd.CrudTask.model.dto.TaskDTO;
import br.com.rd.CrudTask.model.entity.Task;
import br.com.rd.CrudTask.repository.contract.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository tR;

    // Método de adição:

    public TaskDTO addTask(TaskDTO dto) {
        Task newTask = this.dtoToBusiness(dto);
        newTask = tR.save(newTask);
        return businessToDto(newTask);
    }

    // Método de listar tasks:

    public List<TaskDTO> showListTask() {
        List<Task> allList = tR.findAll();
        return listToDto(allList);
    }

    // Método de encontrar por id:

    public TaskDTO findTaskById(Long id) {
        Optional<Task> op = tR.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    // Método de atualizar por id:

    public TaskDTO updateById(TaskDTO dto, Long id) {
        Optional<Task> op = tR.findById(id);

        if(op.isPresent()) {
            Task obj = op.get();

            if(dto.getDescricao() != null) {
                obj.setDescricao(dto.getDescricao());
            }

            if(dto.getDataAtualizacao() != null) {
                obj.setDataAtualizacao(dto.getDataAtualizacao());
            }

            if(dto.getFeito() != null) {
                obj.setFeito(dto.getFeito());
            }

            if(dto.getPorcentagem() != null) {
                obj.setPorcentagem(dto.getPorcentagem());
            }

            tR.save(obj);
            return businessToDto(obj);
        }
        return null;
    }

    // Método de deletar:

    public void deleteById(Long id) {
        if(tR.existsById(id)) {
            tR.deleteById(id);
        }
    }

    // Método de procura de descricao e feitas:

    public List<TaskDTO> searchDescricaoFeita(String descricao, Boolean feito) {
        List<Task> taskList = tR.myFindAllByDescriptionDone(descricao, feito);
        return listToDto(taskList);
    }

    // Método de seleciona decrescente de data:

    public List<TaskDTO> selecionaDataDesc() {
        List<Task> taskList = tR.findByOrderByDataAtualizacaoDesc();
        return listToDto(taskList);
    }

    // Método que seleciona decrescente de data com tarefa feita:

    public List<TaskDTO> selecionaDataDescTarefa(Boolean feito) {
        List<Task> taskList = tR.findByFeitoOrderByDataAtualizacaoDesc(feito);
        return listToDto(taskList);
    }

    // Métodos de conversão:

    // (business seta dto)
    private Task dtoToBusiness(TaskDTO dto) {
        Task business = new Task();
        business.setDescricao(dto.getDescricao());
        business.setFeito(dto.getFeito());
        business.setDataAtualizacao(dto.getDataAtualizacao());
        business.setPorcentagem(dto.getPorcentagem());
        return business;
    }

    // (dto seta business)
    private TaskDTO businessToDto(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setDescricao(task.getDescricao());
        dto.setFeito(task.getFeito());
        dto.setDataAtualizacao(task.getDataAtualizacao());
        dto.setPorcentagem(task.getPorcentagem());
        return dto;
    }

    public List<TaskDTO> listToDto(List<Task> list) {
        List<TaskDTO> listDto = new ArrayList<TaskDTO>();
        for (Task t: list) {
            listDto.add(this.businessToDto(t));
        }
        return listDto;
    }






}
