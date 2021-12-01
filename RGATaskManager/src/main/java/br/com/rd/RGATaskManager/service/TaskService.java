package br.com.rd.RGATaskManager.service;

import br.com.rd.RGATaskManager.model.dto.TaskDTO;
import br.com.rd.RGATaskManager.model.entity.Task;
import br.com.rd.RGATaskManager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public TaskDTO create (TaskDTO dto){
        Task task = dtoToBusiness(dto);
        task.setUpdateDate(new Date());

        task = this.taskRepository.save(task);
        return this.businessToDto(task);
    }

    public List<TaskDTO> findAllTasks(){
        List<Task> allTasks = this.taskRepository.findAll();
        return listToDto(allTasks);
    }

    public TaskDTO findById(Long id){
        Optional<Task> optional = this.taskRepository.findById(id);
        if (optional.isPresent()){
            return businessToDto(optional.get());
        }
        return null;
    }

    public TaskDTO updateById(TaskDTO update, Long id){
        Optional<Task> optional = this.taskRepository.findById(id);
        if (optional.isPresent()){
            Task bolacha = optional.get();

            if(update.getDone() != null){
                bolacha.setDone(update.getDone());
            }

            if(update.getDescription() != null){
                bolacha.setDescription(update.getDescription());
            }

            if(update.getPercentage() != null){
                bolacha.setPercentage(update.getPercentage());
            }

            bolacha.setUpdateDate(new Date());
            bolacha = this.taskRepository.save(bolacha);
            return businessToDto(bolacha);
        }
        return null;
    }

    public void deleteById(Long id){
        if (this.taskRepository.existsById(id)){
            this.taskRepository.deleteById(id);
        }
    }

    public List<TaskDTO> tomate(String description){
        List<Task> taskList = this.taskRepository.myFindAllByDescriptionDone(description);
        return listToDto(taskList);
    }

    public List<TaskDTO> findByDescriptionCustom2(String description){
        List<Task> taskList = this.taskRepository.myFindAllByDescriptionDone2(description);
        return listToDto(taskList);
    }

    public List<TaskDTO> findAllTaskDone(){
        List<Task> taskList = this.taskRepository.findByDoneTrueOrderByUpdateDate();
        return listToDto(taskList);
    }

    public List<TaskDTO> findAllTaskOrdered(){
        List<Task> taskList = this.taskRepository.findByOrderByUpdateDateDesc();
        return listToDto(taskList);
    }

    public List<TaskDTO> findAllTaskOrdered2(){
        List<Task> taskList = this.taskRepository.OrderByUpdateDateDesc();
        return listToDto(taskList);
    }

    public List<TaskDTO> findAllTaskBetween(String date){
        //LocalDateTime day = LocalDateTime.parse(date);

        try {
            SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
            Date day = fomat.parse(date);

            List<Task> taskList = this.taskRepository.findByUpdateDateBetweenOrderByUpdateDateDesc(day, new Date(day.getTime() + (60 * 60 * 24 * 1000)));
            return listToDto(taskList);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }

    private List<TaskDTO> listToDto(List<Task> list){
        List<TaskDTO> listDto = new ArrayList<TaskDTO>();
        for(Task t : list){
            listDto.add(businessToDto(t));
        }
        return listDto;
    }

    private Task dtoToBusiness (TaskDTO batatinhaD){
        Task batatinhaB = new Task();
        batatinhaB.setDescription(batatinhaD.getDescription());
        batatinhaB.setDone(batatinhaD.getDone());
        batatinhaB.setPercentage(batatinhaD.getPercentage());
        return batatinhaB;
    }

    private TaskDTO businessToDto(Task tomatinhoB){
        TaskDTO tomatinhoD = new TaskDTO();
        tomatinhoD.setId(tomatinhoB.getId());
        tomatinhoD.setDone(tomatinhoB.getDone());
        tomatinhoD.setPercentage(tomatinhoB.getPercentage());
        tomatinhoD.setDescription(tomatinhoB.getDescription());
        tomatinhoD.setUpdateDate(tomatinhoB.getUpdateDate());
        return tomatinhoD;
    }


}
