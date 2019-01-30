package br.com.leoat.starwarsapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * DTO que representa o planeta retornado pela API do Star Wars.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StarwarsApiPlanetDTO {

    private String name;
    private List<String> films;
    private String url;

}
