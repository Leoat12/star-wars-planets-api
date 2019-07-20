package br.com.leoat.starwarsapi.controller;

import br.com.leoat.starwarsapi.exception.ResourceNotFoundException;
import br.com.leoat.starwarsapi.model.Planet;
import br.com.leoat.starwarsapi.service.PlanetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
 * <p>Controller com endpoints da API responsável por enviar informações sobre os planets do Star Wars.</p>
 * <p>Serviços disponíveis:</p>
 * <ul>
 *     <li>Listar planetas</li>
 *     <li>Encontrar o planeta por nome</li>
 *     <li>Encontrar o planet por ID</li>
 *     <li>Salvar um planeta</li>
 *     <li>Deletar um planeta</li>
 * </ul>
 */
@RestController
@RequestMapping("/planets")
@Slf4j
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    /**
     * Endpoint utilizado para listar planets ou encontrar um planeta por nome.
     *
     * @param name Parâmetro opcional requerido para buscar por nome.
     * @return ResponseEntity com uma lista de planetas ou um planeta com o nome dado.
     */
    @GetMapping
    public ResponseEntity findAllWithParams(@RequestParam(required = false) String name) {
        return ResponseEntity.ok(
                StringUtils.isBlank(name) ?
                        planetService.findAll() :
                        planetService.findByName(name).orElseThrow(() -> new ResourceNotFoundException(name)));
    }

    /**
     * Encontrar um planeta por ID.
     *
     * @param id ID do planeta.
     * @return ReponseEntity com o planeta requisitado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Planet> findById(@PathVariable Long id) {
        log.info("Find by planet by id " + id);
        return ResponseEntity.ok(planetService.findById(id).orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    /**
     * Salva um planeta.
     *
     * @param planet Planeta a ser salvo.
     * @return ResponseEntity com o planeta salvo.
     * @throws IOException
     */
    @PostMapping
    public ResponseEntity<Planet> save(@Valid @RequestBody Planet planet) throws IOException {
        return ResponseEntity.ok(planetService.save(planet));
    }

    /**
     * Deletar um planeta dado o ID do planeta.
     *
     * @param id ID do planeta
     * @return ReponseEntity com status 200 indicando a finalização do processo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        planetService.delete(id);
        return ResponseEntity.ok().build();
    }
}
