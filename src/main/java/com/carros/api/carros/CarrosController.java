package com.carros.api.carros;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	
    @Autowired
    private CarroService carroService;

    @GetMapping()
    public ResponseEntity<List<CarroDTO>> get(@RequestParam(value = "page", defaultValue = "0") Integer page,
    										  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(carroService.getCarros(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(carroService.getCarroById(id));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> getCarrosByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDTO> carros = carroService.getCarrosByTipo(tipo);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> post(@RequestBody Carro carro) {

        CarroDTO carroDTO = carroService.insert(carro);

        URI location = getUri(carroDTO.getId());
        return ResponseEntity.created(location).build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody Carro carro) {

        carro.setId(id);

        CarroDTO carroDTO = carroService.update(carro, id);

        return carroDTO != null ?
                ResponseEntity.ok(carroDTO) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        carroService.delete(id);

        return ResponseEntity.ok().build();
    }
}