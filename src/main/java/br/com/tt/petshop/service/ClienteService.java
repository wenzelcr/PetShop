package br.com.tt.petshop.service;

import br.com.tt.petshop.api.exception.NomeInvalidoException;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private static final Logger LOG = LoggerFactory.getLogger(ClienteService.class);

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criar(Cliente cliente) throws NomeInvalidoException {
        validarNomeCliente(cliente);
        return this.clienteRepository.save(cliente);
    }

    private void validarNomeCliente(Cliente cliente) throws NomeInvalidoException {
        if( cliente.getNome().trim().indexOf(" ") == -1 ){
            LOG.info( "Nome incompleto: {}", cliente.getNome() );
            throw new NomeInvalidoException("Nome deve ser ao menos em duas partes.");
        }
    }

    public List<Cliente> listar(){
        return this.clienteRepository.findAll();
    }

    public void excluir(Long id) {
        this.clienteRepository.delete(id);
    }

    public Cliente listarPorId(Long id) {
        return this.clienteRepository.findById(id);
    }
}
