package com.clinica.agenda.controller;

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

import com.clinica.agenda.model.Medico;
import com.clinica.agenda.service.MedicoService;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public String listarMedicos(Model model) {
        model.addAttribute("medicos", medicoService.listarTodos());
        return "medicos/lista";
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("medico", new Medico());
        return "medicos/formulario";
    }

    @PostMapping
    public String salvarMedico(@ModelAttribute("medico") Medico medico) {
        medicoService.salvar(medico);
        return "redirect:/medicos";
    }

    @DeleteMapping("/{id}")
    public String excluirMedico(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            medicoService.excluir(id);
            redirectAttributes.addFlashAttribute("success", "Médico excluído com sucesso!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Não é possível excluir o médico, pois ele está associado a uma ou mais consultas.");
        }
        return "redirect:/medicos";
    }

    @GetMapping("/{id}/editar")
    public String exibirFormularioEdicao(@PathVariable Integer id, Model model) {
        Optional<Medico> medico = medicoService.buscarPorId(id);
        if (medico.isPresent()) {
            model.addAttribute("medico", medico.get());
            return "medicos/formulario";
        } else {
            return "redirect:/medicos";
        }
    }

    @PutMapping("/{id}")
    public String atualizarMedico(@PathVariable Integer id, @ModelAttribute("medico") Medico medico) {
        medicoService.atualizar(id, medico);
        return "redirect:/medicos";
    }

    @GetMapping("/agenda")
    public String exibirAgendaCompleta(Model model) {
        model.addAttribute("agenda", medicoService.buscarAgendaCompleta());
        return "medicos/agenda";
    }
}