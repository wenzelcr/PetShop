package br.com.tt.petshop.repository;
import br.com.tt.petshop.model.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteRowMapper implements RowMapper<Cliente> {

    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpf(rs.getString("cpf"));
        if( rs.getDate("nascimento")  != null ) {
            cliente.setNascimento(rs.getDate("nascimento").toLocalDate());
        }
        return cliente;
    }
}
