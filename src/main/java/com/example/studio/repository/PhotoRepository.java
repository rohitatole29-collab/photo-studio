package com.example.studio.repository;

import com.example.studio.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Optional<Photo> findBySlugAndPublishedTrue(String slug);
    List<Photo> findAllByPublishedTrueOrderByIdDesc();
}
