package br.com.tt.petshop.service;

import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criar(Cliente cliente) throws NomeInvalidoException{
        if( cliente.getNome().trim().indexOf(" ") == -1 ){
            throw new NomeInvalidoException("Nome deve ser ao menos em duas partes.");
        }
        return this.clienteRepository.save(cliente);
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
