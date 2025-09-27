package com.clinica.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clinica.agenda.model.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    @Query("SELECT m FROM Medico m LEFT JOIN FETCH m.consultas")
    List<Medico> findAllWithConsultas();

} 
