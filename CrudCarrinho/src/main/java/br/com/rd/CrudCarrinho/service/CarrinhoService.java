package br.com.rd.CrudCarrinho.service;

import br.com.rd.CrudCarrinho.model.dto.CarrinhoDTO;
import br.com.rd.CrudCarrinho.model.entity.Carrinho;
import br.com.rd.CrudCarrinho.repository.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    @Autowired
    CarrinhoRepository cP;

    // Método de adição:

    public CarrinhoDTO addCarrinho(CarrinhoDTO dto) {
        Carrinho newCarrinho = this.dtoToBusiness(dto);
        newCarrinho = cP.save(newCarrinho);
        return businessToDto(newCarrinho);
    }

    // Método de listar:

    public List<CarrinhoDTO> showListCarrinho() {
        List<Carrinho> allList = cP.findAll();
        return listToDto(allList);
    }

    // Método de listar por id:

    public CarrinhoDTO findCarrinhoById(Long id) {
        Optional<Carrinho> op = cP.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    // Método para atualizar:

    public CarrinhoDTO updateById(CarrinhoDTO dto, Long id) {
        Optional<Carrinho> op = cP.findById(id);

        if(op.isPresent()) {
            Carrinho obj = op.get();

            if(dto.getUser() != null) {
                obj.setUser(dto.getUser());
            }

            cP.save(obj);
            return businessToDto(obj);
        }
        return null;
    }

    // Método para deletar:

    public void deleteById(Long id) {
        if(cP.existsById(id)) {
            cP.deleteById(id);
        }
    }

    // Métodos de conversão:

    private Carrinho dtoToBusiness(CarrinhoDTO dto) {
        Carrinho business = new Carrinho();
        business.setUser(dto.getUser());
        return business;
    }

    private CarrinhoDTO businessToDto(Carrinho cr) {
        CarrinhoDTO dto = new CarrinhoDTO();
        dto.setId(cr.getId());
        dto.setUser(cr.getUser());
        return dto;
    }

    public List<CarrinhoDTO> listToDto(List<Carrinho> list) {
        List<CarrinhoDTO> listDto = new ArrayList<CarrinhoDTO>();
        for (Carrinho c: list) {
            listDto.add(this.businessToDto(c));
        }
        return listDto;
    }


}
