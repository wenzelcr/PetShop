package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.AnimalOutDto;
import br.com.tt.petshop.dto.ProdutoInDto;
import br.com.tt.petshop.dto.ProdutoOutDto;
import br.com.tt.petshop.model.Produto;
import br.com.tt.petshop.service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoEndpoint {

    private ProdutoService produtoService;

    public ProdutoEndpoint(ProdutoService produtoService, ModelMapper modelMapper) {
        this.produtoService = produtoService;
        this.modelMapper = modelMapper;
    }

    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid ProdutoInDto produtoInDto){
        Produto produto = modelMapper.map(produtoInDto, Produto.class);
        Produto produtoSalvo = produtoService.salvar(produto);
        return ResponseEntity.created(URI.create("/produtos/" + produtoSalvo.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<ProdutoOutDto>> listar(@RequestParam(required = false) BigDecimal valorMaiorQue,
                                                      @RequestParam(required = false) String nomeIniciandoEm){
        return ResponseEntity.ok(produtoService.listar(valorMaiorQue, nomeIniciandoEm ).stream()
        .map(a -> modelMapper.map(a, ProdutoOutDto.class))
        .collect(Collectors.toList()));
    }

}
