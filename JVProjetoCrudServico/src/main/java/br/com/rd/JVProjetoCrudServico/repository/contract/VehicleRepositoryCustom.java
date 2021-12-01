package br.com.rd.JVProjetoCrudServico.repository.contract;

import br.com.rd.JVProjetoCrudServico.model.Entity.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepositoryCustom {

    @Query(value = "Select * from vehicle v where v.year = :year", nativeQuery = true)
    List<Vehicle> suco(@Param("year") Integer year);
    // suco nesse caso é o método para se diferenciar do Spring.

    @Query(value = "select * from vehicle v where v.brand = :brand and v.model = :model and v.year = :year", nativeQuery = true)
    List<Vehicle> pegaTudo(@Param("year")Integer year, @Param("brand")String brand, @Param("model")String model);


}
