package br.com.rd.RGAProjetoServico.repository.contract;

import br.com.rd.RGAProjetoServico.model.entity.Car;
import br.com.rd.RGAProjetoServico.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByBrandAndModelAndYear(String brand, String model, Integer year);
}
