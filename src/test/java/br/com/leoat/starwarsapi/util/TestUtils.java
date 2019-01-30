package br.com.leoat.starwarsapi.util;

import br.com.leoat.starwarsapi.model.Planet;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TestUtils {

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    public static Planet createPlanet(Long id, String name, String climate, String terrain) {
        Planet planet = new Planet();
        planet.setId(id);
        planet.setName(name);
        planet.setClimate(climate);
        planet.setTerrain(terrain);
        planet.setAppearances(0);

        return planet;
    }
}
