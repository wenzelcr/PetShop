package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    List<Produto> findAllByValorGreaterThan(BigDecimal valor);
    List<Produto> findByDescricaoStartingWith(String nome);
    List<Produto> findAllByValorGreaterThanAndDescricaoStartingWith(BigDecimal valor, String nome);
}
