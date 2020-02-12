package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.AnimalInDto;
import br.com.tt.petshop.dto.AnimalOutDto;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.service.AnimalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animais")
public class AnimalEndPoint {

    private AnimalService animalService;

    private ModelMapper modelMapper;

    public AnimalEndPoint(AnimalService animalService, ModelMapper modelMapper) {
        this.animalService = animalService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalOutDto> BuscarPorId(@PathVariable Long id) {
        Optional<Animal> animalOptional = animalService.buscarPorId(id);
        //if(animalOptional.isPresent()){
        //    Animal animal = animalOptional.get();
        //}

        //Animal animal = animalOptional.orElse(new Animal());

        AnimalOutDto animalOutDto = animalOptional
                .map(a -> new AnimalOutDto(a))
                .orElseThrow(
                        () -> new IllegalArgumentException("Animal não existe")
                );

        return ResponseEntity.ok(animalOutDto);

    }

    @GetMapping
    public ResponseEntity<List<AnimalOutDto>> BuscarTodos(){

        List<Animal> listaAnimais = animalService.buscarTodos();
        List<AnimalOutDto> listaNovaAnimais = new ArrayList<>();

        for (Animal animal:listaAnimais) {
            listaNovaAnimais.add(new AnimalOutDto(animal));
        }

        return ResponseEntity.status(HttpStatus.OK).body(listaNovaAnimais);
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid AnimalInDto animalDto){
        Animal animal = modelMapper.map( animalDto, Animal.class );
        Animal animalSalvo = animalService.salvar(animal);
        return ResponseEntity.created(URI.create("/animais/" + animalSalvo.getId())).build();
    }

}
