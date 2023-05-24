package api.service;

import api.entity.ProdutoEntity;
import api.exception.ModelException;
import api.model.ProdutoModel;
import api.model.errors.ProdutoErrors;
import api.repository.ProdutoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ProdutoModel listarLacamentoPorId(Long id) {
        ProdutoEntity produto = produtoRepository.findById(id).orElse(new ProdutoEntity());
        if (produto.getId() == null) {
            throw new ModelException(ProdutoErrors.NOT_FOUND);
        }
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> listarProdutos() {
        List<ProdutoEntity> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            throw new ModelException(ProdutoErrors.NOT_FOUND);
        }
        return produtos.stream().map(entity -> modelMapper.map(entity, ProdutoModel.class)).collect(Collectors.toList());
    }

    public ProdutoModel criarProduto(ProdutoModel produtoModel) {
        try {
            ProdutoEntity produtoEntity = modelMapper.map(produtoModel, ProdutoEntity.class);
            produtoEntity = produtoRepository.save(produtoEntity);
            return modelMapper.map(produtoEntity, ProdutoModel.class);
        } catch (Exception e) {
            throw new ModelException(ProdutoErrors.ERROR_CREATING);
        }
    }

    public ProdutoModel alterarProduto(Long id, ProdutoModel produtoModel) {
        if (produtoRepository.existsById(id)) {
            ProdutoEntity produtoEntity = produtoRepository.findById(id).orElse(new ProdutoEntity());
            produtoEntity.setId(id);
            produtoEntity.setNome(produtoModel.getNome());
            produtoEntity.setDescricao(produtoModel.getDescricao());
            //produtoEntity.setCreatedAt(produtoEntity.getCreatedAt());
            produtoEntity = produtoRepository.save(produtoEntity);
            return modelMapper.map(produtoEntity, ProdutoModel.class);
        }
        throw new ModelException(ProdutoErrors.NOT_FOUND);
    }

    public String excluirProduto(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return "Produto excluído com sucesso.";
        }
        throw new ModelException(ProdutoErrors.NOT_FOUND);
    }
}