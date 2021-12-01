package br.com.rd.CrudCarrinho.repository;

import br.com.rd.CrudCarrinho.model.entity.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

}
