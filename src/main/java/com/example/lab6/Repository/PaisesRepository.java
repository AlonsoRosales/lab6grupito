package com.example.lab6.Repository;

import com.example.lab6.Entity.Paises;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisesRepository extends JpaRepository<Paises, Integer> {
}