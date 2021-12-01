package br.com.rd.RGAProjetoServico.service;

import br.com.rd.RGAProjetoServico.model.dto.CarDTO;
import br.com.rd.RGAProjetoServico.model.dto.MediaDTO;
import br.com.rd.RGAProjetoServico.model.entity.Car;
import br.com.rd.RGAProjetoServico.model.entity.Media;
import br.com.rd.RGAProjetoServico.repository.contract.CarRepository;
import br.com.rd.RGAProjetoServico.repository.contract.MediaRepository;
import br.com.rd.RGAProjetoServico.repository.contract.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    @Autowired
    MediaRepository mediaRepository;

    public CarDTO addCar(CarDTO car){
        Car newCar = this.dtoToBusiness(car);

        if (newCar.getCarMedia() != null) {
            //Sem CascadeType
            Long id = newCar.getCarMedia().getId();
            Media m;
            if (id != null) {
                    m = this.mediaRepository.getById(id);
            }else{
                m = this.mediaRepository.save(newCar.getCarMedia());
            }
            newCar.setCarMedia(m);

            //CascadeType.PERSIST
//            Long id = newCar.getCarMedia().getId();
//            if (id != null) {
//                Media m = this.mediaRepository.getById(id);
//                newCar.setCarMedia(m);
//            }
        }

        newCar = carRepository.save(newCar);
        return this.businessToDto(newCar);
    }

    public List<CarDTO> findAllCar(){
        List<Car> allList = carRepository.findAll();
        return this.listToDto(allList);
    }

    private List<CarDTO> listToDto(List<Car> list){
        List<CarDTO> listDto = new ArrayList<CarDTO>();
        for (Car v: list) {
            listDto.add(this.businessToDto(v));
        }
        return listDto;
    }

    public CarDTO searchCarById(Long id){
        Optional<Car> op = carRepository.findById(id);

        if (op.isPresent()){
            return businessToDto(op.get());
        }
        return null;
    }

    public CarDTO updateById(CarDTO dto, Long id){
        Optional<Car> op = carRepository.findById(id);

        if (op.isPresent()){
            Car obj = op.get();

            if (dto.getBrand() != null){
                obj.setBrand(dto.getBrand());
            }

            if (dto.getModel() != null){
                obj.setModel(dto.getModel());
            }

            if (dto.getYear() != null){
                obj.setYear(dto.getYear());
            }

            carRepository.save(obj);
            return businessToDto(obj);
        }
        return null;
    }

    public void deleteById(Long id){
        if(carRepository.existsById(id)){
            carRepository.deleteById(id);
        }
    }

    private Car dtoToBusiness(CarDTO dto){
        Car business = new Car();
        business.setBrand(dto.getBrand());
        business.setModel(dto.getModel());
        business.setYear(dto.getYear());
        business.setTurbine(dto.getTurbine());
        if (dto.getMedia() != null) {
            Media m = new Media();
            if (dto.getMedia().getId() != null){
                m.setId(dto.getMedia().getId());
            }else {
                m.setDescription(dto.getMedia().getDescription());
                m.setTouch(dto.getMedia().getTouch());
            }

            business.setCarMedia(m);
        }
        return business;
    }

    private CarDTO businessToDto(Car business){
        CarDTO dto = new CarDTO();
        dto.setId(business.getId());
        dto.setBrand(business.getBrand());
        dto.setModel(business.getModel());
        dto.setYear(business.getYear());
        dto.setTurbine(business.getTurbine());
        if (business.getCarMedia() != null){
            MediaDTO mediaDTO = new MediaDTO();
            mediaDTO.setId(business.getCarMedia().getId());
            mediaDTO.setDescription(business.getCarMedia().getDescription());
            mediaDTO.setTouch(business.getCarMedia().getTouch());
            dto.setMedia(mediaDTO);
        }
        return dto;
    }
}
