package com.fs.sistemadenotas.repository;

import com.fs.sistemadenotas.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByIdRol(Integer idRol);
}
