package com.peltikhin.atmos;

import com.peltikhin.atmos.auth.SecurityConfiguration;
import com.peltikhin.atmos.services.ServicesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ServicesConfiguration.class, SecurityConfiguration.class})
public class AtmosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmosApplication.class, args);
	}

}
