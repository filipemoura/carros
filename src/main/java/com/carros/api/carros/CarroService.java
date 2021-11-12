package com.carros.api.carros;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> getCarros(Pageable pageable) {
        return carroRepository.findAll(pageable).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO getCarroById(Long id) {
        return carroRepository.findById(id).map(CarroDTO::create).get();
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return carroRepository.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO insert(Carro carro) {
        Assert.isNull(carro.getId(),"Não foi possível inserir o registro");

        return CarroDTO.create(carroRepository.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Carro> optional = carroRepository.findById(id);
        if(optional.isPresent()) {
            Carro carroDB = optional.get();
            // Copiar as propriedades
            carroDB.setNome(carro.getNome());
            carroDB.setTipo(carro.getTipo());
            System.out.println("Carro id " + carroDB.getId());

            // Atualiza o carro
            carroRepository.save(carroDB);

            return CarroDTO.create(carroDB);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        carroRepository.deleteById(id);
    }
}