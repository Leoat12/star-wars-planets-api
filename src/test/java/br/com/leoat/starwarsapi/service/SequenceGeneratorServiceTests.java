package br.com.leoat.starwarsapi.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataMongoTest
public class SequenceGeneratorServiceTests {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @TestConfiguration
    static class sequenceGeneratorServiceTestConfiguration {

        @Autowired
        private MongoOperations operations;

        @Bean
        public SequenceGeneratorService sequenceGeneratorService() {
            return new SequenceGeneratorService(operations);
        }
    }

    @Test
    public void shouldGenerateSequenceAndReturnId() {
        Long id = sequenceGeneratorService.generateSequence("Test");

        assertThat(id, is(1L));
    }

}
