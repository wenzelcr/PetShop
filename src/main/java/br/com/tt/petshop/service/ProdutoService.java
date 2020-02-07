package br.com.tt.petshop.service;

import br.com.tt.petshop.model.Produto;
import br.com.tt.petshop.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> listar(BigDecimal valor, String nome) {
        if( valor==null && nome==null ){
            return (List<Produto>) produtoRepository.findAll();
        }else if( valor==null && nome!=null ){
            return produtoRepository.findByDescricaoStartingWith(nome);
        }else if(valor!=null && nome==null){
            return produtoRepository.findAllByValorGreaterThan(valor);
        }else{
            return produtoRepository.findAllByValorGreaterThanAndDescricaoStartingWith(valor, nome);
        }
    }
}
