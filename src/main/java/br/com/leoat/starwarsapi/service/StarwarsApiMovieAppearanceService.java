package br.com.leoat.starwarsapi.service;

import br.com.leoat.starwarsapi.client.StarwarsApiClient;
import br.com.leoat.starwarsapi.dto.StarwarsApiPlanetDTO;
import br.com.leoat.starwarsapi.dto.StarwarsApiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Objects;

/**
 * Serviço utilizado para se comunicar com a API de Star Wars (https://swapi.co)
 */
@Service
@RequiredArgsConstructor
public class StarwarsApiMovieAppearanceService {

    private final Retrofit retrofit;
    private Logger logger = LoggerFactory.getLogger(StarwarsApiMovieAppearanceService.class);

    /**
     * Método que retorna o número de aparições, ou null caso o planeta não exista.
     *
     * @param planetName Nome do planeta sendo buscado
     * @return Integer com o número de aparições ou null se o planeta não for encontrado
     * @throws IOException
     */
    public Integer getPlanetAppearancesCount(String planetName) throws IOException {

        StarwarsApiClient starwarsApiClient = retrofit.create(StarwarsApiClient.class);

        Integer page = 1;
        int appearances = 0;
        boolean planetFound = false;

        while(Objects.nonNull(page)) {
            Response<StarwarsApiResponseDTO> response = starwarsApiClient
                    .getPlanets(page).execute();

            StarwarsApiResponseDTO dto = null;

            if(response.isSuccessful()) {
                dto = response.body();
            } else {
                logger.error(response.errorBody().string());
            }

            if(Objects.nonNull(dto)) {

                StarwarsApiPlanetDTO planetDTO = dto.getResults().stream()
                        .filter(p -> StringUtils.equals(p.getName(), planetName)).findAny().orElse(null);

                if(Objects.nonNull(planetDTO)) {
                    appearances = planetDTO.getFilms().size();
                    planetFound = true;
                    page = null;
                } else {
                    page = StringUtils.isBlank(dto.getNext()) ? null : page + 1;
                }

            } else {
                page = null;
            }
        }

        if(planetFound)
            return appearances;
        else
            return null;
    }
}
