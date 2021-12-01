package br.com.rd.JVCrudFilms.repository.contract;

import br.com.rd.JVCrudFilms.model.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long>, ActorRepositoryCustom2{
}
