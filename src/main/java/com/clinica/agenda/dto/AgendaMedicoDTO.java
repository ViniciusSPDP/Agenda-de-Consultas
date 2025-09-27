package com.clinica.agenda.dto;

import java.util.List;

import com.clinica.agenda.model.Consulta;
import com.clinica.agenda.model.Medico;

public record AgendaMedicoDTO(
    String nome,
    String especialidade,
    String dataConsulta,
    List<Consulta> consultas
) {
    public AgendaMedicoDTO(Medico medico){
        this(
            medico.getNome(),
            medico.getEspecialidade(),
            null,
            medico.getConsultas()
        );
    }
}
