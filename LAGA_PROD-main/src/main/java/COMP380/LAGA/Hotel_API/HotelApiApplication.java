package COMP380.LAGA.Hotel_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@code HotelApiApplication} is the main entry point for the Hotel API application.
 * <p>
 * It is responsible for starting and configuring the Spring Boot application.
 * <p>
 * The {@link SpringBootApplication} annotation enables auto-configuration, component scanning,
 * and configuration property binding for the application.
 */
@SpringBootApplication
public class HotelApiApplication {

    /**
     * The main method serves as the entry point for the Java application.
     * It starts the Spring Boot application by invoking {@link SpringApplication#run(Class, String...)}.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(HotelApiApplication.class, args);
    }
}

