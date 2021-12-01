package br.com.rd.JVProjetoCrudServico.service;

import br.com.rd.JVProjetoCrudServico.model.Entity.Vehicle;
import br.com.rd.JVProjetoCrudServico.model.dto.VehicleDTO;
import br.com.rd.JVProjetoCrudServico.repository.contract.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vR;

    public VehicleDTO addVehicle(VehicleDTO vh) {
        Vehicle newVehicle = this.dtoToBusiness(vh);
        newVehicle = vR.save(newVehicle);
        return businessToDto(newVehicle);
    }

    public List<VehicleDTO> showListVehicle() {
        List<Vehicle> allList = vR.findAll();
        return listToDto(allList);
    }

    public List<VehicleDTO> listToDto(List<Vehicle> list) {
        List<VehicleDTO> listDto = new ArrayList<VehicleDTO>();
        for (Vehicle v: list) {
            listDto.add(this.businessToDto(v));
        }
        return listDto;

    }

    public VehicleDTO findVehicle(Long id) {
        Optional<Vehicle> op = vR.findById(id);
        if (op.isPresent()) {
            return businessToDto(op.get());
        }
        return null;
    }

    public VehicleDTO updateById(VehicleDTO dto, Long id) {
        Optional<Vehicle> op = vR.findById(id);

        if(op.isPresent()) {
            Vehicle obj = op.get();

            if(dto.getBrand() != null) {
                obj.setBrand(dto.getBrand());
            }

            if(dto.getModel() != null) {
                obj.setModel(dto.getModel());
            }

            if(dto.getYear() != null) {
                obj.setYear(dto.getYear());
            }

            vR.save(obj);
            return businessToDto(obj);
        }
        return null;
    }

    public void deleteById(Long id) {
        if(vR.existsById(id)) {
            vR.deleteById(id);
        }
    }

    public List<VehicleDTO> searchAllByYear(Integer year) {
        List<Vehicle> vehicleList = vR.suco(year);
        return listToDto(vehicleList);
    }

    // Método criado para retornar os três atributos de Vehicle.
    public List<VehicleDTO> searchTotal(Integer year, String brand, String model) {
        List<Vehicle> vehicleListAll = vR.pegaTudo(year, brand, model);
        return listToDto(vehicleListAll);
    }

    private Vehicle dtoToBusiness(VehicleDTO dto) {
        Vehicle business = new Vehicle();
        business.setBrand(dto.getBrand());
        business.setModel(dto.getModel());
        business.setYear(dto.getYear());
        return business;
    }

    private VehicleDTO businessToDto(Vehicle vh) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vh.getId());
        dto.setBrand(vh.getBrand());
        dto.setModel(vh.getModel());
        dto.setYear(vh.getYear());
        return dto;
    }






}
