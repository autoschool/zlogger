package zlogger.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.mockito.Mockito.mock;

/**
 * Created by alexwyrm on 11/19/14.
 */
@Configuration
public class MockConfig {

    @Bean
    public EntityManager entityManager() {
        return mock(EntityManager.class);
    }

    @Bean
    public EntityManagerFactory emf() {
        return mock(EntityManagerFactory.class);
    }
}
