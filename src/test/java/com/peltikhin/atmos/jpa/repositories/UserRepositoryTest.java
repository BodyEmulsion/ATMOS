package com.peltikhin.atmos.jpa.repositories;

import com.peltikhin.atmos.AbstractIntegrationTest;
import com.peltikhin.atmos.jpa.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        this.userRepository.deleteAll();
        //TODO Think about use @BeforeAll with @TestInstance(TestInstance.Lifecycle.PER_CLASS). It's may be useful
    }

    /**
     * It tests that DB is working
     */
    @Test
    void findByUsername() {
        String username = "username";
        User user = User.builder()
                .username(username)
                .password("password").build();
        this.userRepository.save(user);
        assertTrue(this.userRepository.findByUsername(username).isPresent());
    }
}