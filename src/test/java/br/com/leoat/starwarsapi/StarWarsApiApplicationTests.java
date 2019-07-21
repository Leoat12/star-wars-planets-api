package br.com.leoat.starwarsapi;

import br.com.leoat.starwarsapi.model.Planet;
import br.com.leoat.starwarsapi.service.PlanetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StarWarsApiApplicationTests {

    @Autowired
    private PlanetService planetService;

    @Test
    public void contextLoads() {

        List<Planet> planets = planetService.findAll();
        assertThat(planets, notNullValue());

    }

}

