package br.com.rd.RGAProjetoServico.controller;


import br.com.rd.RGAProjetoServico.model.dto.VehicleDTO;
import br.com.rd.RGAProjetoServico.model.entity.Vehicle;
import br.com.rd.RGAProjetoServico.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @PostMapping
    public VehicleDTO create(@RequestBody VehicleDTO vehicle){
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping
    public List<VehicleDTO> findAll(){
        return vehicleService.findAllVehicle();
    }

    @GetMapping("/{id}")
    public VehicleDTO searchById(@PathVariable("id") Long id){
        return vehicleService.searchVehicleById(id);
    }

    @GetMapping("/searchByYear/{year}")
    public List<VehicleDTO> searchByYear(@PathVariable("year") Integer year){
        return vehicleService.searchAllByYear(year);
    }

    @GetMapping("/search")
    public List<VehicleDTO> searchAllByParameters(@RequestParam(value = "brand") String brand,
                                                  @RequestParam(value = "model") String model,
                                                  @RequestParam(value = "year") Integer year){
        return vehicleService.searchAllByParameters(brand, model, year);
    }

    @GetMapping("/search/{brand}/{model}/{year}")
    public List<VehicleDTO> searchAllByParametersPath(@PathVariable("brand") String brand,
                                                      @PathVariable("model") String model,
                                                      @PathVariable("year") Integer year){
        return vehicleService.searchAllByParameters(brand, model, year);
    }

    @PutMapping("/{id}")
    public VehicleDTO updateById(@RequestBody VehicleDTO dto, @PathVariable("id") Long id){
        return vehicleService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT) //NO_CONTENT não permite retorno de objeto
    public void deleteById(@PathVariable("id") Long id){
        vehicleService.deleteById(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public VehicleDTO deleteByIdReturningDTO(@PathVariable("id") Long id){
        return vehicleService.deleteByIdReturningDTO(id);
    }

}
