package com.example.studio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.studio.entity.Photo;
import com.example.studio.repository.PhotoRepository;

@SpringBootApplication
public class PhotoStudioApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoStudioApplication.class, args);
    }

    /**
     * Seeds initial photo data into the database if the table is empty.
     */
    @Bean
    public CommandLineRunner seedData(PhotoRepository photoRepository) {
        return args -> {
            if (photoRepository.count() == 0) {
                photoRepository.save(new Photo(
                        "Wedding Moments",
                        "wedding-moments",
                        300.0,
                        "/images/images134.jpg",
                        "uploads/full/images134.jpg",
                        true
                ));

                photoRepository.save(new Photo(
                        "Urban Night",
                        "urban-night",
                        250.0,
                        "/images/images167.jpg",
                        "uploads/full/images167.jpg",
                        true
                ));

                photoRepository.save(new Photo(
                        "Nature Bliss",
                        "nature-bliss",
                        220.0,
                        "/images/images78.jpg",
                        "uploads/full/images78.jpg",
                        true
                ));

                System.out.println("✅ Sample photos seeded successfully.");
            } else {
                System.out.println("ℹ️ Photo table already contains data.");
            }
        };
    }
}
