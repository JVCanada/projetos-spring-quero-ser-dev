package br.com.rd.RGATaskManager.repository;

import br.com.rd.RGATaskManager.model.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepositoryCustom {
    @Query(value = "SELECT * FROM tb_task WHERE description LIKE %:desc% AND done = true",
            nativeQuery = true)
    List<Task> myFindAllByDescriptionDone(@Param("desc") String desc);
}
