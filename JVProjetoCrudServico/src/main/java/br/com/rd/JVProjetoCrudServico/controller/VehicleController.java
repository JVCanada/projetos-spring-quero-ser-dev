package br.com.rd.JVProjetoCrudServico.controller;


import br.com.rd.JVProjetoCrudServico.model.dto.VehicleDTO;
import br.com.rd.JVProjetoCrudServico.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    VehicleService vhS;

    @PostMapping
    public VehicleDTO create(@RequestBody VehicleDTO vh) {
        return vhS.addVehicle(vh);
    }

    @GetMapping
    public List<VehicleDTO> showList() {
        return vhS.showListVehicle();
    }

    @GetMapping("/{id}")
    public VehicleDTO find(@PathVariable("id") Long id) {    // Caminho dinamico!
        return vhS.findVehicle(id);
    }

    @GetMapping("/searchByYear/{year}")
    public List<VehicleDTO> findAllByYear(@PathVariable("year") Integer year) {
        return vhS.searchAllByYear(year);
    }

    @GetMapping("/searchTotal/{year}&{brand}&{model}")
    public List<VehicleDTO> searchAllVehicles(@PathVariable("year") Integer year, @PathVariable("brand") String brand, @PathVariable("model") String model) {
        return vhS.searchTotal(year, brand, model);
    }

//    Todos os atributos usando ResponseBody:
    @GetMapping("/searchTotal2")
    public @ResponseBody List<VehicleDTO> searchTotal2(@RequestParam(value = "year") Integer year, @RequestParam(value = "brand") String brand, @RequestParam(value = "model") String model) {
        return vhS.searchTotal(year, brand, model);
    }

    @PutMapping("/{id}")
    public VehicleDTO updateById(@RequestBody VehicleDTO dto, @PathVariable("id") Long id) {
        return vhS.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        vhS.deleteById(id);
    }













}
