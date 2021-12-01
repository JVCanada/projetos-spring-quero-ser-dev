package br.com.rd.RGAProjetoServico.service;

import br.com.rd.RGAProjetoServico.model.entity.Vehicle;
import br.com.rd.RGAProjetoServico.model.dto.VehicleDTO;
import br.com.rd.RGAProjetoServico.repository.contract.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    Repositorio repositorio;

    public VehicleDTO addVehicle(VehicleDTO vehicle){
        Vehicle newVehicle = this.dtoToBusiness(vehicle);
        newVehicle = repositorio.save(newVehicle);
        return this.businessToDto(newVehicle);
    }

    public List<VehicleDTO> findAllVehicle(){
        List<Vehicle> allList = repositorio.findAll();
        return this.listToDto(allList);
    }

    private List<VehicleDTO> listToDto(List<Vehicle> list){
        List<VehicleDTO> listDto = new ArrayList<VehicleDTO>();
        for (Vehicle v: list) {
            listDto.add(this.businessToDto(v));
        }
        return listDto;
    }

    public VehicleDTO searchVehicleById(Long id){
        Optional<Vehicle> op = repositorio.findById(id);

        if (op.isPresent()){
            return businessToDto(op.get());
        }
        return null;
    }

    public List<VehicleDTO> searchAllByYear(Integer year){
        List<Vehicle> vehicleList = repositorio.abobrinha(year);
        return listToDto(vehicleList);
    }

    public List<VehicleDTO> searchAllByParameters (String brand, String model, Integer year){
        List<Vehicle> vehicleList = repositorio.abobrinha2(brand, model, year);
        return listToDto(vehicleList);
    }

    public VehicleDTO updateById(VehicleDTO dto, Long id){
        Optional<Vehicle> op = repositorio.findById(id);

        if (op.isPresent()){
            Vehicle obj = op.get();

            if (dto.getBrand() != null){
                obj.setBrand(dto.getBrand());
            }

            if (dto.getModel() != null){
                obj.setModel(dto.getModel());
            }

            if (dto.getYear() != null){
                obj.setYear(dto.getYear());
            }

            repositorio.save(obj);
            return businessToDto(obj);
        }
        return null;
    }

    public void deleteById(Long id){
        if(repositorio.existsById(id)){
            repositorio.deleteById(id);
        }
    }

    public VehicleDTO deleteByIdReturningDTO(Long id){
        VehicleDTO retorno = this.searchVehicleById(id);
        if(repositorio.existsById(id)){
            repositorio.deleteById(id);
        }
        return retorno;
    }

    private Vehicle dtoToBusiness(VehicleDTO dto){
        Vehicle business = new Vehicle();
        business.setBrand(dto.getBrand());
        business.setModel(dto.getModel());
        business.setYear(dto.getYear());
        return business;
    }

    private VehicleDTO businessToDto(Vehicle business){
        VehicleDTO dto = new VehicleDTO();
        dto.setId(business.getId());
        dto.setBrand(business.getBrand());
        dto.setModel(business.getModel());
        dto.setYear(business.getYear());
        return dto;
    }
}
