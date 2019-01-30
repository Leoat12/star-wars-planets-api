package br.com.leoat.starwarsapi.client;

import br.com.leoat.starwarsapi.dto.StarwarsApiResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Cliente que conecta com API de Star Wars (https://swapi.co) utilizando Retrofit
 */
public interface StarwarsApiClient {

    /**
     * Endpoint GET para retirar uma página de planetas.
     *
     * @param page Número da página requisitada.
     * @return Chamada a ser executada.
     */
    @GET("planets")
    Call<StarwarsApiResponseDTO> getPlanets(@Query("page") Integer page);

}
