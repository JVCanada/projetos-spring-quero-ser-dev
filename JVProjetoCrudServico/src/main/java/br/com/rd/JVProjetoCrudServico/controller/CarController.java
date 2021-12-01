package br.com.rd.JVProjetoCrudServico.controller;

import br.com.rd.JVProjetoCrudServico.model.dto.CarDTO;
import br.com.rd.JVProjetoCrudServico.model.dto.VehicleDTO;
import br.com.rd.JVProjetoCrudServico.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService cS;

    @PostMapping
    public CarDTO create(@RequestBody CarDTO dto) {
        return cS.addCar(dto);
    }

    @GetMapping
    public List<CarDTO> showList() {
        return cS.showListCar();
    }

    @GetMapping("/{id}")
    public CarDTO find(@PathVariable("id") Long id) {    // Caminho dinamico!
        return cS.findCarById(id);
    }

    @PutMapping("/{id}")
    public CarDTO updateById(@RequestBody CarDTO dto, @PathVariable("id") Long id) {
        return cS.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        cS.deleteById(id);
    }


}
