package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig
{
    @Bean
    CommandLineRunner commandLineRunner(StudentRepositorty repositorty)
    {
        return args -> {
            Student mariam = new Student(
                "Mariam",
                "Mariam.jamal@gmail.com",
                LocalDate.of(2000, Month.JANUARY, 5)
            );
            Student jose = new Student(
                "José",
                "jose.teste@gmail.com",
                LocalDate.of(2002, Month.JANUARY, 10)
            );
            repositorty.saveAll(Arrays.asList(mariam, jose));
        };
    }
}
