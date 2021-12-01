package br.com.rd.JVProjetoCrudServico.repository.contract;

import br.com.rd.JVProjetoCrudServico.model.Entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // FORMA MAIS SIMPLES DE EXECUTAR O SELECT ESPECIFICO!
//    List<Car> findByBrandAndModelAndYear(String brand, String model, Integer year);
}
