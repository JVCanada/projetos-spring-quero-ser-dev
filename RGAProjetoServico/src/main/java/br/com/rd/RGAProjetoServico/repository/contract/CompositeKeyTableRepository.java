package br.com.rd.RGAProjetoServico.repository.contract;

import br.com.rd.RGAProjetoServico.model.embeddable.CompositeKey;
import br.com.rd.RGAProjetoServico.model.entity.CompositeKeyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompositeKeyTableRepository extends JpaRepository<CompositeKeyTable, CompositeKey> {
    List<CompositeKeyTable> findByCompositeKeyId(Long id); //CompositeKey é a classe!!!!
    List<CompositeKeyTable> findByCompositeKeyName(String name);
    List<CompositeKeyTable> findByAnotherName(String name);
}