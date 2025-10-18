package com.clinica.agenda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinica.agenda.dto.AgendaMedicoDTO;
import com.clinica.agenda.model.Medico;
import com.clinica.agenda.repository.MedicoRepository;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarPorId(Integer id) {
        return medicoRepository.findById(id);
    }

    @Transactional
    public Medico salvar(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Transactional
    public Medico atualizar(Integer id, Medico medico) {
        medico.setId(id);
        return medicoRepository.save(medico);
    }

    @Transactional
    public void excluir(Integer id) throws DataIntegrityViolationException {
        medicoRepository.deleteById(id);
    }

    public List<AgendaMedicoDTO> buscarAgendaCompleta() {
        List<Medico> medicos = medicoRepository.findAllWithConsultas();
        return medicos.stream()
                .map(AgendaMedicoDTO::new)
                .toList();
    }
}