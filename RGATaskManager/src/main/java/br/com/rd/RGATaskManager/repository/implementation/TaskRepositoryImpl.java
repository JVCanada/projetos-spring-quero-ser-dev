package br.com.rd.RGATaskManager.repository.implementation;

import br.com.rd.RGATaskManager.model.entity.Task;
import br.com.rd.RGATaskManager.repository.TaskRepositoryCustom;
import br.com.rd.RGATaskManager.repository.TaskRepositoryCustom2;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepositoryCustom2 {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Task> myFindAllByDescriptionDone2(String descrip) {
        Query sql = this.entityManager.createNativeQuery(
                "SELECT * FROM tb_task WHERE description LIKE '%' :desc '%' AND done = true", Task.class
        );
        sql.setParameter("desc", descrip);

        List<Task> taskList = sql.getResultList();
        return taskList;
    }
}
