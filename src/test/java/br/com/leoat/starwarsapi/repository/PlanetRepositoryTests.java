package br.com.leoat.starwarsapi.repository;

import br.com.leoat.starwarsapi.model.Planet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static br.com.leoat.starwarsapi.util.TestUtils.createPlanet;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PlanetRepositoryTests {


    @Autowired
    private PlanetRepository planetRepository;

    @Before
    public void prepareEntity() {
        Planet planet1 = createPlanet(1L, "Earth", "Good", "ground, rocks and water");
        Planet planet2 = createPlanet(2L, "Mars", "Hard", "Red terrain");
        Planet planet3 = createPlanet(3L, "Mercury", "Very hot", "Dry terrain");

        planetRepository.saveAll(Arrays.asList(planet1, planet2, planet3));
    }

    @Test
    public void findById() {
        Optional<Planet> planet = planetRepository.findById(1L);

        assertTrue(planet.isPresent());
        assertThat(planet.get().getId(), is(1L));
    }

    @Test
    public void findByName() {
        Optional<Planet> planet = planetRepository.findByName("Mars");

        assertTrue(planet.isPresent());
        assertThat(planet.get().getName(), is("Mars"));
    }

    @Test
    public void save() {
        Planet planet = planetRepository.save(createPlanet(4L, "Saturn", "A lot of rings", "Spacial ground"));

        assertThat(planet.getName(), is("Saturn"));
    }

    @Test
    public void delete() {
        planetRepository.deleteById(3L);

        Optional<Planet> noPlanet = planetRepository.findById(3L);

        assertFalse(noPlanet.isPresent());
    }
}
