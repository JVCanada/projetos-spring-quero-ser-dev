package br.com.rd.JVProjetoCrudServico.repository.contract;

import br.com.rd.JVProjetoCrudServico.model.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>, VehicleRepositoryCustom{
    // List<Vehicle> findAllByAno(Integer year);
}
