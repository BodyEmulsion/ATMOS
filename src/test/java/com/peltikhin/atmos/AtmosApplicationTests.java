package com.peltikhin.atmos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AtmosApplicationTests extends AbstractIntegrationTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        //I think this assertion isn't necessary but SonarLint going crazy without it
        assertNotNull(applicationContext);
    }

}
