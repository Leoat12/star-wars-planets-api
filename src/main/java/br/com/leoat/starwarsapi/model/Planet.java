package br.com.leoat.starwarsapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * Classe que representa a entidade do planeta.
 */
@Data
@NoArgsConstructor
@Document(collection = "planets")
public class Planet {

    @Transient
    public static final String SEQUENCE_NAME = "planets_sequence";

    @Id
    private Long id;

    @NotBlank @Indexed(unique = true)
    private String name;

    @NotBlank private String climate;
    @NotBlank private String terrain;
    private Integer appearances;

}
