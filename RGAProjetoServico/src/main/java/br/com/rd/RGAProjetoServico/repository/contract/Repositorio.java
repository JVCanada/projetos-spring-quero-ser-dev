package br.com.rd.RGAProjetoServico.repository.contract;

import br.com.rd.RGAProjetoServico.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Repositorio extends JpaRepository<Vehicle, Long>, VehicleRepositoryCustom {
    //List<Vehicle> abobrinha(Integer year);
}
