package com.achraf.videogames.repos;

import com.achraf.videogames.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
