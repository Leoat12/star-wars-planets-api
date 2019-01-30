package br.com.leoat.starwarsapi.repository;

import br.com.leoat.starwarsapi.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repositório que realiza a comunicação com o banco de dados para gerenciar os planetas armazenados.
 */
public interface PlanetRepository extends MongoRepository<Planet, Long> {

    Optional<Planet> findByName(String name);

}
