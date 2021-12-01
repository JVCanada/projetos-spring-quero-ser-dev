package br.com.rd.RGATaskManager.repository;

import br.com.rd.RGATaskManager.model.entity.Task;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepositoryCustom2 {
    List<Task> myFindAllByDescriptionDone2(String desc);
}
