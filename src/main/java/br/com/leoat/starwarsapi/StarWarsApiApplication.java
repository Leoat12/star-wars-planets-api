package br.com.leoat.starwarsapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@SpringBootApplication
public class StarWarsApiApplication {

    @Value("${starwars.api.path}")
    private String stawarsApiPath;

    public static void main(String[] args) {
        SpringApplication.run(StarWarsApiApplication.class, args);
    }

    @Bean
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(stawarsApiPath)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

}

