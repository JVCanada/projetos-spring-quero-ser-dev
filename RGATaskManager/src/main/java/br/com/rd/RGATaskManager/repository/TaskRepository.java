package br.com.rd.RGATaskManager.repository;

import br.com.rd.RGATaskManager.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>,
        TaskRepositoryCustom, TaskRepositoryCustom2 {
    //parte 2
    List<Task> findByDoneTrueOrderByUpdateDate();
    //parte 1
    List<Task> findByOrderByUpdateDateDesc();
    List<Task> OrderByUpdateDateDesc();

    //List<Task> findByUpdateDateOrderByUpdateDateDesc(Date d);
    List<Task> findByUpdateDateBetweenOrderByUpdateDateDesc(Date begin, Date end);

}
