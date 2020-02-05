package br.com.tt.petshop.repository;
import br.com.tt.petshop.model.Unidade;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UnidadeRepository {

    private JdbcTemplate jdbcTemplate;
    private EntityManager entityManager;


    private List<Unidade> db = new ArrayList<>();

    public UnidadeRepository(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
    }

    public Unidade update(Unidade unidade){
        Unidade unidadeSalva = entityManager.merge(unidade);
        return unidadeSalva;
    }

    public Unidade save(Unidade unidade){
        //db.add(unidade);
        jdbcTemplate.update("insert into unidade(nome, endereco) values ( ?, ?);",
                                  unidade.getNome(), unidade.getEndereco());
        return unidade;
    }

    public void limpar(){
        this.db = null;
    }

    public List<Unidade> findAll(){
        //return this.db;
        return jdbcTemplate.query("select id, nome, endereco from unidade", new UnidadeRowMapper());
    }

    public void delete(String nome) {

        /*
        List<Unidade> listaAposExclusao = new ArrayList<>();

        Iterator<Unidade> iterator = db.iterator();
        while (iterator.hasNext()){
            Unidade unidade = iterator.next();
            if(!unidade.getNome().equalsIgnoreCase(nome)){
                listaAposExclusao.add(unidade);
            }
        }

        db = listaAposExclusao;*/


        jdbcTemplate.update("delete from unidade where nome = ?", nome);
    }

    public Unidade findById(Long id) {
        return (Unidade) entityManager.createQuery("select u from Unidade u Where u.id = :id ")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Modifying
    @Transactional
    public void deletarPorId(Long id){
        entityManager.createQuery("delete from Unidade Where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
