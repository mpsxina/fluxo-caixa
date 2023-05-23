package api.repository;

import api.entity.LancamentoEntity;
import api.enums.TipoLancamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LancamentoRepository extends CrudRepository<LancamentoEntity, Long> {

    List<LancamentoEntity> findAll();

    List<LancamentoEntity> findByData(LocalDate data);

    List<LancamentoEntity> findByTipoLancamento(TipoLancamento tipoLancamento);

}
