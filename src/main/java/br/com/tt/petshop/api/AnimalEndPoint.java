package br.com.tt.petshop.api;

import br.com.tt.petshop.dto.AnimalOutDto;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/animais")
public class AnimalEndPoint {

    private AnimalService animalService;

    public AnimalEndPoint(AnimalService animalService) {
        this.animalService = animalService;
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

}
