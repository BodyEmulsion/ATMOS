package com.peltikhin.atmos;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * It's superclass for all integration tests that want to use TestContainers PostgreSQL (In fact for all)
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class AbstractIntegrationTest {
    static final PostgreSQLContainer<?> postgreSQLContainer;

    static {
        //I think 'try'-with-resources isn't necessary here, but intellij doesn't think so, so I suppress warning (I'm ashamed no matter what)
        //noinspection resource
        postgreSQLContainer =
                new PostgreSQLContainer<>(DockerImageName.parse("postgres"))
                        .withDatabaseName("atmos")
                        .withUsername("username")
                        .withPassword("password")
                        .withReuse(true);

        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void datasourceConfig(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
    }
}
