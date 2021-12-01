package br.com.rd.CrudTask.repository.contract;

import br.com.rd.CrudTask.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom {

    List<Task> findByOrderByDataAtualizacaoDesc ();

    List<Task> findByFeitoOrderByDataAtualizacaoDesc(Boolean feito);
}
