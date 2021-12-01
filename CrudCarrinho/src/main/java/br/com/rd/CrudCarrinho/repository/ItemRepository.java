package br.com.rd.CrudCarrinho.repository;

import br.com.rd.CrudCarrinho.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
