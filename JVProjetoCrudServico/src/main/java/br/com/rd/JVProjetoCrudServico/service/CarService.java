package br.com.rd.JVProjetoCrudServico.service;

import br.com.rd.JVProjetoCrudServico.model.Entity.Car;
import br.com.rd.JVProjetoCrudServico.model.Entity.Media;
import br.com.rd.JVProjetoCrudServico.model.Entity.Vehicle;
import br.com.rd.JVProjetoCrudServico.model.dto.CarDTO;
import br.com.rd.JVProjetoCrudServico.model.dto.MediaDTO;
import br.com.rd.JVProjetoCrudServico.model.dto.VehicleDTO;
import br.com.rd.JVProjetoCrudServico.repository.contract.CarRepository;
import br.com.rd.JVProjetoCrudServico.repository.contract.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    CarRepository cP;

    @Autowired
    MediaRepository mR;

    // Método de adição:

    public CarDTO addCar(CarDTO cr) {
        Car newCar = this.dtoToBusiness(cr);

        if (newCar.getCarMedia() != null) {
            Long id = newCar.getCarMedia().getId();
            Media m;
            if (id != null) {
                m = this.mR.getById(id);
            } else {
                m = this.mR.save(newCar.getCarMedia());
            }
            newCar.setCarMedia(m);
        }

        newCar = cP.save(newCar);
        return businessToDto(newCar);
    }

    // Método de listar carros:

    public List<CarDTO> showListCar() {
        List<Car> allList = cP.findAll();
        return listToDto(allList);
    }

    // Método de encontrar por id:

    public CarDTO findCarById(Long id) {
        Optional<Car> op = cP.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    // Método de atualizar por id:

    public CarDTO updateById(CarDTO dto, Long id) {
        Optional<Car> op = cP.findById(id);

        if(op.isPresent()) {
            Car obj = op.get();

            if(dto.getBrand() != null) {
                obj.setBrand(dto.getBrand());
            }

            if(dto.getModel() != null) {
                obj.setModel(dto.getModel());
            }

            if(dto.getYear() != null) {
                obj.setYear(dto.getYear());
            }

            if(dto.getColor() != null) {
                obj.setColor(dto.getColor());
            }

            if (dto.getMedia() != null) {
                Media m = new Media();
                m.setDescription(dto.getMedia().getDescription());
                m.setTouch(dto.getMedia().getTouch());
                obj.setCarMedia(m);
            }

            cP.save(obj);
            return businessToDto(obj);
        }
        return null;
    }

    // Método de deletar:

    public void deleteById(Long id) {
        if(cP.existsById(id)) {
            cP.deleteById(id);
        }
    }

    // Métodos de conversão:

    private Car dtoToBusiness(CarDTO dto) {
        Car business = new Car();
        business.setBrand(dto.getBrand());
        business.setModel(dto.getModel());
        business.setYear(dto.getYear());
        business.setColor(dto.getColor());
        if (dto.getMedia() != null) {
            Media m = new Media();
            if (dto.getMedia().getId() != null) {
                m.setId(dto.getMedia().getId());
            } else {
                m.setDescription(dto.getMedia().getDescription());
                m.setTouch(dto.getMedia().getTouch());
            }

            business.setCarMedia(m);
        }
        return business;
    }

    private CarDTO businessToDto(Car cr) {
        CarDTO dto = new CarDTO();
        dto.setId(cr.getId());
        dto.setBrand(cr.getBrand());
        dto.setModel(cr.getModel());
        dto.setYear(cr.getYear());
        dto.setColor(cr.getColor());

        if (cr.getCarMedia() != null) {
            MediaDTO mdto = new MediaDTO();
            mdto.setId(cr.getCarMedia().getId());
            mdto.setDescription(cr.getCarMedia().getDescription());
            mdto.setTouch(cr.getCarMedia().getTouch());
            dto.setMedia(mdto);
        }
        return dto;
    }

    public List<CarDTO> listToDto(List<Car> list) {
        List<CarDTO> listDto = new ArrayList<CarDTO>();
        for (Car c: list) {
            listDto.add(this.businessToDto(c));
        }
        return listDto;
    }

}
