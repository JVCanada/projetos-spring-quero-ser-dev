package br.com.rd.CrudCarrinho.model.dto;

import br.com.rd.CrudCarrinho.model.entity.Carrinho;
import lombok.Data;

@Data
public class ItemDTO {

    private Long id;
    private String description;
    private CarrinhoDTO carrinho;

}
