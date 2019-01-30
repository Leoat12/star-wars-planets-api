package br.com.leoat.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * DTO que representa a resposta da API do Star Wars
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarwarsApiResponseDTO {

    private int count;
    private String next;
    private String previous;
    private List<StarwarsApiPlanetDTO> results;

}
