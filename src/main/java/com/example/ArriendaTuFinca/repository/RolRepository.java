package com.example.ArriendaTuFinca.repository;

import com.example.ArriendaTuFinca.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

}
