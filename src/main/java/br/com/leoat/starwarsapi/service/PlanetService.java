package br.com.leoat.starwarsapi.service;

import br.com.leoat.starwarsapi.exception.ResourceNotFoundException;
import br.com.leoat.starwarsapi.model.Planet;
import br.com.leoat.starwarsapi.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Serviço utilizado para centralizar as regras de negócios realioionadas à entidade Planet.
 */
@Service
@RequiredArgsConstructor
public class PlanetService {

    private final PlanetRepository planetRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final StarwarsApiMovieAppearanceService appearanceService;

    public List<Planet> findAll() {
        return planetRepository.findAll();
    }

    public Optional<Planet> findById(Long id) {
        return planetRepository.findById(id);
    }

    public Optional<Planet> findByName(String name) {
        return planetRepository.findByName(name);
    }

    public Planet save(Planet planet) throws IOException {

        Integer appearances = appearanceService.getPlanetAppearancesCount(planet.getName());

        if(appearances != null) {
            planet.setId(sequenceGeneratorService.generateSequence(Planet.SEQUENCE_NAME));
            planet.setAppearances(appearances);
            return planetRepository.save(planet);
        } else {
            throw new ResourceNotFoundException("Planet not found on Star Wars stories!");
        }
    }

    public void delete(Long id) {
        planetRepository.deleteById(id);
    }

}
