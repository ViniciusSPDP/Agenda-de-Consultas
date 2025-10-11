package com.clinica.agenda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clinica.agenda.dto.AgendaMedicoDTO;
import com.clinica.agenda.model.Medico;
import com.clinica.agenda.repository.MedicoRepository;



@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping
    public String listarMedicos(Model model){
        model.addAttribute("medicos", medicoRepository.findAll());
        return "medicos/lista";
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model){
        model.addAttribute("medico", new Medico());
        return "medicos/formulario";
    }

    @PostMapping
    public String salvarMedico(@ModelAttribute("medico") Medico medico){
        medicoRepository.save(medico);
        return "redirect:/medicos";
    }

    @DeleteMapping("/{id}")
    public String excluirMedico(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            medicoRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Médico excluído com sucesso!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Não é possível excluir o médico, pois ele está associado a uma ou mais consultas.");
        }
        return "redirect:/medicos";
    }

    @GetMapping("/{id}/editar")
    public String exibirFormularioEdicao(@PathVariable Integer id, Model model) {
        Optional<Medico> medico = medicoRepository.findById(id);
        if (medico.isPresent()) {
            model.addAttribute("medico", medico.get());
            return "medicos/formulario";
        } else {
            return "redirect:/medicos";
        }
    }

    @PutMapping("/{id}")
    public String atualizarMedico(@PathVariable Integer id, @ModelAttribute("medico") Medico medico) {
        medico.setId(id);
        medicoRepository.save(medico);
        return "redirect:/medicos";
    }

    @GetMapping("/agenda")
    public String exibirAgendaCompleta(Model model) {
        List<Medico> medicos = medicoRepository.findAllWithConsultas();
        List<AgendaMedicoDTO> agendas = medicos.stream()
                .map(AgendaMedicoDTO::new)
                .toList();
        model.addAttribute("agenda", agendas);
        return "medicos/agenda";
    }
    
}
