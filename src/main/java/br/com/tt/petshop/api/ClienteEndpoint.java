package br.com.tt.petshop.api;

import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import br.com.tt.petshop.api.exception.NomeInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteEndpoint {
    private final ClienteService clienteService;

    public ClienteEndpoint(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("X-SITUACAO","Ativos")
                .body(clienteService.listar());
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id){
        return clienteService.listarPorId(id);
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody Cliente cliente) throws NomeInvalidoException {
        Cliente clientesalvo = clienteService.criar(cliente);
        clienteService.criar(cliente);
        URI uri = URI.create("/clientes/"+clientesalvo.getId());
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
