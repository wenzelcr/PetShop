package br.com.tt.petshop.api;

import br.com.tt.petshop.model.Unidade;
import br.com.tt.petshop.service.UnidadeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/unidades")
public class UnidadeEndpoint {

    private final UnidadeService unidadeService;

    public UnidadeEndpoint(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public List<Unidade> buscarTodos(){
        return unidadeService.listar();
    }

    @GetMapping("/{idUnidade}")
    public Unidade buscaPorId(@PathVariable("idUnidade") Long id){
        return unidadeService.buscarPorId(id);
    }

    @PostMapping
    public void criar(@RequestBody Unidade unidade){
        unidadeService.criar(unidade);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        unidadeService.excluirPorId(id);
    }
}
