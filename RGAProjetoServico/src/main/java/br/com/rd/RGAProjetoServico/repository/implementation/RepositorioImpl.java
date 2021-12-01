package br.com.rd.RGAProjetoServico.repository.implementation;

import br.com.rd.RGAProjetoServico.model.entity.Vehicle;
import br.com.rd.RGAProjetoServico.repository.contract.VehicleRepositoryCustom;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
//Tem que conter o mesmo nome do Repository + Sufixo Impl
public class RepositorioImpl{//{ implements VehicleRepositoryCustom {

//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public List<Vehicle> abobrinha(Integer year) {
//        //SELECT * FROM TB_VEHICLE WHERE YEAR = ?
//        Query sql = entityManager.createNativeQuery("SELECT * FROM TB_VEHICLE WHERE YEAR = ?", Vehicle.class);
//        sql.setParameter(1, year);
//
//        List<Vehicle> list = sql.getResultList();
//        return list;
//    }
}
