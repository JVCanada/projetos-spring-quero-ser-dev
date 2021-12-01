package br.com.rd.RGAProjetoServico.controller;


import br.com.rd.RGAProjetoServico.model.dto.CarDTO;
import br.com.rd.RGAProjetoServico.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping
    public CarDTO create(@RequestBody CarDTO car){
        return carService.addCar(car);
    }

    @GetMapping
    public List<CarDTO> findAll(){
        return carService.findAllCar();
    }

    @GetMapping("/{id}")
    public CarDTO searchById(@PathVariable("id") Long id){
        return carService.searchCarById(id);
    }

    @PutMapping("/{id}")
    public CarDTO updateById(@RequestBody CarDTO dto, @PathVariable("id") Long id){
        return carService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT) //NO_CONTENT não permite retorno de objeto
    public void deleteById(@PathVariable("id") Long id){
        carService.deleteById(id);
    }

}
