package com.clinica.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinica.agenda.model.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

    
} 
