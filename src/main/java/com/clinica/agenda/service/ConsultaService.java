package com.clinica.agenda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clinica.agenda.model.Consulta;
import com.clinica.agenda.model.Medico;
import com.clinica.agenda.repository.ConsultaRepository;
import com.clinica.agenda.repository.MedicoRepository;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Integer id) {
        return consultaRepository.findById(id);
    }

    public List<Medico> listarTodosMedicos() {
        return medicoRepository.findAll();
    }

    @Transactional
    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @Transactional
    public Consulta atualizar(Integer id, Consulta consulta) {
        consulta.setId(id);
        return consultaRepository.save(consulta);
    }

    @Transactional
    public void excluir(Integer id) {
        consultaRepository.deleteById(id);
    }
}