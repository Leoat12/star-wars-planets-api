package br.com.leoat.starwarsapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
public class StarwarsApiMovieAppearanceServiceTests {


    @Autowired
    private StarwarsApiMovieAppearanceService service;

    @TestConfiguration
    static class starwarsApiMovieAppearanceServeiceTestConfiguration {

        private String stawarsApiPath = "https://swapi.co/api/";

        @Bean
        public Retrofit retrofit() {
            return new Retrofit.Builder()
                    .baseUrl(stawarsApiPath)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }

        @Bean
        public StarwarsApiMovieAppearanceService service() {
            return new StarwarsApiMovieAppearanceService(retrofit());
        }
    }

    @Test
    public void shouldReturnTheAppearanceCount() throws IOException {
        Integer count = service.getPlanetAppearancesCount("Alderaan");

        assertThat(count, greaterThanOrEqualTo(2));
    }

    @Test
    public void shouldReturnTheAppearanceCountOnPage2() throws IOException {
        Integer count = service.getPlanetAppearancesCount("Mustafar");

        assertThat(count, greaterThanOrEqualTo(1));
    }

    @Test
    public void shouldReturnNullIfPlanetDoesNotExist() throws IOException {
        Integer count = service.getPlanetAppearancesCount("PlanetTest");

        assertThat(count, is(nullValue()));
    }
}
