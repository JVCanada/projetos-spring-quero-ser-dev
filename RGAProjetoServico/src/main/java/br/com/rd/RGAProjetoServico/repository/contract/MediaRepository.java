package br.com.rd.RGAProjetoServico.repository.contract;

import br.com.rd.RGAProjetoServico.model.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
}
