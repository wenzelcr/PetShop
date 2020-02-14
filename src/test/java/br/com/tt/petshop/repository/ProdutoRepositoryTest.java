package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Sql(value="classpath:/popula_produtos.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value="classpath:/limpa_produtos.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    void findAllByValorGreaterThan() {
        //preparação

        //ação
        BigDecimal vl = BigDecimal.valueOf(50);
        List<Produto> resultado = produtoRepository.findAllByValorGreaterThan(vl);


        //verificação
        assertEquals(1,resultado.size());
        Produto banho = resultado.get(0);
        assertEquals("Banho e Tosa", banho.getDescricao());
        assertEquals(3, banho.getId());
        assertEquals(BigDecimal.valueOf(75).setScale(2, RoundingMode.HALF_DOWN), banho.getValor());


    }
}