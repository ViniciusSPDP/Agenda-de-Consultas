package com.clinica.agenda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUsuario;

    @Column(nullable = false, length = 40)
    private String nomeUsuario;

    @Column(nullable = false, length = 12, unique = true)
    private String cpfUsuario;

    @Column(nullable = false, length = 40, unique = true)
    private String loginUsuario;

    @Column(nullable = false, length = 200)
    private String senhaUsuario;

    @Column(nullable = false, length = 30)
    private String whatsappUsuario;

    private String role = "ROLE_USER";
    
}
