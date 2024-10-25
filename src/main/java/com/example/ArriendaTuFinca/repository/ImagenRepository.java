package com.example.ArriendaTuFinca.repository;

import com.example.ArriendaTuFinca.models.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
}
