package br.com.leoat.starwarsapi.controller;

import br.com.leoat.starwarsapi.exception.ResourceNotFoundException;
import br.com.leoat.starwarsapi.model.Planet;
import br.com.leoat.starwarsapi.service.PlanetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.com.leoat.starwarsapi.util.TestUtils.convertObjectToJsonBytes;
import static br.com.leoat.starwarsapi.util.TestUtils.createPlanet;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanetController.class)
public class PlanetControllerTests {

    private List<Planet> planets;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanetService planetService;

    @Before
    public void setUp() {
        Planet planet1 = createPlanet(1L, "Earth", "Good", "ground, rocks and water");
        Planet planet2 = createPlanet(2L, "Mars", "Hard", "Red terrain");
        Planet planet3 = createPlanet(3L, "Mercury", "Very hot", "Dry terrain");

        planets = Arrays.asList(planet1, planet2, planet3);
    }


    @Test
    public void shouldFindAllWithStatus200() throws Exception {
        Mockito.when(planetService.findAll()).thenReturn(planets);


        mockMvc.perform(
                get("/planets")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldFindByNameWithStatus200() throws Exception {
        Planet planetEarth = planets.get(0);
        Mockito.when(planetService.findByName("Earth")).thenReturn(Optional.of(planetEarth));

        this.mockMvc.perform(
                get("/planets?name=Earth"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Earth")));
    }

    @Test
    public void shouldFindByIdWithStatus200() throws Exception {
        Planet planetEarth = planets.get(0);
        Mockito.when(planetService.findById(1L)).thenReturn(Optional.of(planetEarth));

        this.mockMvc.perform(
                get("/planets/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Earth")));

    }

    @Test
    public void shouldReturnStatus404ToNotFoundPlanet() throws Exception {
        Mockito.when(planetService.findById(4L)).thenReturn(Optional.empty());

        this.mockMvc.perform(
                get("/planets/{id}", 4L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldCreateANewPlanet() throws Exception {
        Planet newPlanet = createPlanet(null, "Hoth", "A lot of rings", "Spacial ground");
        Mockito.when(planetService.save(newPlanet)).thenReturn(createPlanet(4L, "Hoth", "A lot of rings", "Spacial ground"));

        this.mockMvc.perform(
                post("/planets")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(newPlanet)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(4)))
                .andExpect(jsonPath("$.name", is("Hoth")));
    }

    @Test
    public void shouldReturn404WithAPlanetNotFoundOnTheApi() throws Exception {
        Planet newPlanet = createPlanet(null, "PlanetTest", "A lot of rings", "Spacial ground");
        Mockito.when(planetService.save(newPlanet)).thenThrow(new ResourceNotFoundException("Planet not found on Star Wars stories!"));

        this.mockMvc.perform(
                post("/planets")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(convertObjectToJsonBytes(newPlanet)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnStatus400ToIncompleteBody() throws Exception{
        Planet incompletePlanet = createPlanet(null, null, "A lot of rings", "Spacial ground");

        this.mockMvc.perform(
                post("/planets")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(convertObjectToJsonBytes(incompletePlanet)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
