package br.com.rd.CrudCarrinho.service;

import br.com.rd.CrudCarrinho.model.dto.CarrinhoDTO;
import br.com.rd.CrudCarrinho.model.dto.ItemDTO;
import br.com.rd.CrudCarrinho.model.entity.Carrinho;
import br.com.rd.CrudCarrinho.model.entity.Item;
import br.com.rd.CrudCarrinho.repository.CarrinhoRepository;
import br.com.rd.CrudCarrinho.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository iR;

    @Autowired
    CarrinhoRepository cR;

    // Método de adição:

    public ItemDTO addItem(ItemDTO item) {
        Item newItem = this.dtoToBusiness(item);

        if (newItem.getCarrinho() != null) {
            Long id = newItem.getCarrinho().getId();
            Carrinho carr;
            if (id != null) {
                carr = this.cR.getById(id);
            } else {
                carr = this.cR.save(newItem.getCarrinho());
            }
            newItem.setCarrinho(carr);
        }

        newItem = iR.save(newItem);
        return businessToDto(newItem);
    }

    // Método de listar Itens:

    public List<ItemDTO> showListItem() {
        List<Item> allList = iR.findAll();
        return listToDto(allList);
    }

    // Método de encontrar por id:

    public ItemDTO findItemById(Long id) {
        Optional<Item> op = iR.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    // Método de atualizar por id:

    public ItemDTO updateById(ItemDTO dto, Long id) {
        Optional<Item> op = iR.findById(id);

        if(op.isPresent()) {

            Item obj = op.get();

            if(dto.getDescription() != null) {
                obj.setDescription(dto.getDescription());
            }

            if (dto.getCarrinho() != null) {
                Carrinho carr = new Carrinho();
                carr.setUser(dto.getCarrinho().getUser());
                obj.setCarrinho(carr);
            }

            iR.save(obj);
            return businessToDto(obj);

        }
        return null;
    }

    // Método de deletar:

    public void deleteById(Long id) {
        if(iR.existsById(id)) {
            iR.deleteById(id);
        }
    }

    // Métodos de conversão:

    private Item dtoToBusiness(ItemDTO dto) {
        Item business = new Item();

        business.setDescription(dto.getDescription());
        if (dto.getCarrinho() != null) {
            Carrinho carr = new Carrinho();
            if(dto.getCarrinho().getId() != null) {
                carr.setId(dto.getCarrinho().getId());
            } else {
                carr.setUser(dto.getCarrinho().getUser());
            }

            business.setCarrinho(carr);
        }
        return business;
    }

    private ItemDTO businessToDto(Item it) {
        ItemDTO dto = new ItemDTO();
        dto.setId(it.getId());
        dto.setDescription(it.getDescription());

        if (it.getCarrinho() != null) {
            CarrinhoDTO cdto = new CarrinhoDTO();
            cdto.setId(it.getCarrinho().getId());
            cdto.setUser(it.getCarrinho().getUser());
            dto.setCarrinho(cdto);
        }
        return dto;
    }

    private List<ItemDTO> listToDto(List<Item> list) {
        List<ItemDTO> listDto = new ArrayList<ItemDTO>();
        for (Item i: list) {
            listDto.add(this.businessToDto(i));
        }
        return listDto;
    }













}
