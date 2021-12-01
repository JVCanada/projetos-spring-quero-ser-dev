package br.com.rd.RGAProjetoServico.repository.contract;

import br.com.rd.RGAProjetoServico.model.entity.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface VehicleRepositoryCustom {
//    public List<Vehicle> abobrinha(Integer year);
    @Query(value = "SELECT * FROM tb_vehicle v " +
                        "WHERE v.year = :year", nativeQuery = true)
    List<Vehicle> abobrinha(@Param("year")Integer year);

    @Query(value = "SELECT * FROM tb_vehicle v WHERE v.year = :year and v.cl_brand = :brand and v.model = :model",
            nativeQuery = true)
    List<Vehicle> abobrinha2(@Param("brand") String brand,
                             @Param("model") String model,
                             @Param("year") Integer year);

}

