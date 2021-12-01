package br.com.rd.CrudTask.repository.contract;

import br.com.rd.CrudTask.model.entity.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepositoryCustom {

    @Query(value = "Select * from task t where t.descricao LIKE %:descricao% and t.feito = :feito", nativeQuery = true)
    List<Task> myFindAllByDescriptionDone(@Param("descricao") String descricao, @Param("feito") Boolean feito);
}
