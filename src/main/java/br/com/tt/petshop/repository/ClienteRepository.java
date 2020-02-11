package br.com.tt.petshop.repository;

import br.com.tt.petshop.model.Cliente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    private List<Cliente> db = new ArrayList<>();

    private JdbcTemplate jdbcTemplate;
    private EntityManager entityManager;

    public ClienteRepository(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
    }

    @Modifying
    @Transactional
    public Cliente save(Cliente cliente){
        entityManager.persist(cliente);
        return cliente;
    }

    public Cliente saveJdbc(Cliente cliente){
        //db.add(cliente);
        jdbcTemplate.update("insert into cliente(nome, cpf, nascimento) values (?, ?, ? );",
                cliente.getNome(), cliente.getCpf(), cliente.getNascimento());
        return cliente;
    }

    public List<Cliente> findAll(){
        //return jdbcTemplate.query("select id, nome, cpf, nascimento from cliente", new ClienteRowMapper());
        return entityManager.createQuery("from Cliente").getResultList();
    }

    public void delete(Long id) {

        jdbcTemplate.update("delete from cliente where id = ?", id);
    }

    public Cliente findById(Long id) {
        return (Cliente) entityManager.createQuery("from Cliente c Where c.id = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
