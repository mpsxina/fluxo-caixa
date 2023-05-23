package api.service;

import api.entity.LancamentoEntity;
import api.entity.ProdutoEntity;
import api.enums.TipoLancamento;
import api.exception.ModelException;
import api.model.LancamentoModel;
import api.model.errors.LancamentoErrors;
import api.model.errors.ProdutoErrors;
import api.repository.LancamentoRepository;
import api.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public LancamentoModel listarLancamentoPorId(Long id) {
        LancamentoEntity lancamento = lancamentoRepository.findById(id).orElse(new LancamentoEntity());
        if (lancamento.getId() == null) {
            throw new ModelException(LancamentoErrors.NOT_FOUND);
        }
        return modelMapper.map(lancamento, LancamentoModel.class);
    }

    public List<LancamentoModel> listarLancamentoPorData(LocalDate data) {
        List<LancamentoEntity> lancamentos = lancamentoRepository.findByData(data);
        if (lancamentos.isEmpty()) {
            throw new ModelException(LancamentoErrors.NOT_FOUND);
        }
        return lancamentos.stream().map(entity -> modelMapper.map(entity, LancamentoModel.class)).collect(Collectors.toList());
    }

    public List<LancamentoModel> listarLancamentoPorTipo(TipoLancamento tipoLancamento) {
        List<LancamentoEntity> lancamentos = lancamentoRepository.findByTipoLancamento(tipoLancamento);
        if (lancamentos.isEmpty()) {
            throw new ModelException(LancamentoErrors.NOT_FOUND);
        }
        return lancamentos.stream().map(entity -> modelMapper.map(entity, LancamentoModel.class)).collect(Collectors.toList());
    }

    public List<LancamentoModel> listarLancamentos() {
        List<LancamentoEntity> lancamentos = lancamentoRepository.findAll();
        if (lancamentos.isEmpty()) {
            throw new ModelException(LancamentoErrors.NOT_FOUND);
        }
        return lancamentos.stream().map(entity -> modelMapper.map(entity, LancamentoModel.class)).collect(Collectors.toList());
    }

    public LancamentoModel criarLancamento(LancamentoModel lancamentoModel) {
        try {
            ProdutoEntity produto = produtoRepository.findById(lancamentoModel.getIdProduto()).orElse(new ProdutoEntity());
            lancamentoModel.setValor((lancamentoModel.getValor() == null) ? BigDecimal.ZERO : lancamentoModel.getValor());
            if (produto.getId() != null) {
                LancamentoEntity lancamentoEntity = modelMapper.map(lancamentoModel, LancamentoEntity.class);
                lancamentoEntity.setProduto(produto);
                lancamentoEntity = lancamentoRepository.save(lancamentoEntity);
                return modelMapper.map(lancamentoEntity, LancamentoModel.class);
            }
            throw new ModelException(ProdutoErrors.ERROR_CREATING);
        } catch (Exception e) {
            throw new ModelException(LancamentoErrors.ERROR_CREATING);
        }
    }

    public LancamentoModel alterarLancamento(Long id, LancamentoModel lancamentoModel) {
        if (lancamentoRepository.existsById(id)) {
            LancamentoEntity lancamentoEntity = lancamentoRepository.findById(id).orElse(new LancamentoEntity());
            ProdutoEntity produtoEntity = produtoRepository.findById(lancamentoModel.getIdProduto()).orElse(new ProdutoEntity());
            if (produtoEntity.getId() != null) {
                lancamentoEntity.setId(id);
                lancamentoEntity.setTipoLancamento(lancamentoModel.getTipoLancamento());
                lancamentoEntity.setProduto(produtoEntity);
                lancamentoEntity.setValor((lancamentoModel.getValor() == null) ? BigDecimal.ZERO : lancamentoModel.getValor());
                lancamentoEntity.setData(lancamentoModel.getData());
                lancamentoEntity.setCreatedAt(lancamentoEntity.getCreatedAt());
                lancamentoEntity = lancamentoRepository.save(lancamentoEntity);
                return modelMapper.map(lancamentoEntity, LancamentoModel.class);
            }
            throw new ModelException(ProdutoErrors.ERROR_CREATING);
        }
        throw new ModelException(LancamentoErrors.NOT_FOUND);
    }

    public String excluirLancamento(Long id) {
        if (lancamentoRepository.existsById(id)) {
            lancamentoRepository.deleteById(id);
            return "Lançamento excluído com sucesso.";
        }
        throw new ModelException(LancamentoErrors.NOT_FOUND);
    }
}