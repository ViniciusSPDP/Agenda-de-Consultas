package com.clinica.agenda.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clinica.agenda.model.Consulta;
import com.clinica.agenda.service.ConsultaService;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public String listarConsultas(Model model) {
        model.addAttribute("consultas", consultaService.listarTodas());
        return "consultas/lista";
    }
    
    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("medicos", consultaService.listarTodosMedicos());
        model.addAttribute("consulta", new Consulta());
        return "consultas/formulario";
    }

    @PostMapping
    public String salvarConsulta(@ModelAttribute("consulta") Consulta consulta) {
        consultaService.salvar(consulta);
        return "redirect:/consultas";
    }

    @DeleteMapping("/{id}")
    public String excluirConsulta(@PathVariable Integer id) {
        consultaService.excluir(id);
        return "redirect:/consultas";
    }

    @GetMapping("/{id}/editar")
    public String exibirFormularioEdicao(@PathVariable Integer id, Model model) {
        Optional<Consulta> consulta = consultaService.buscarPorId(id);
        if (consulta.isPresent()) {
            model.addAttribute("medicos", consultaService.listarTodosMedicos());
            model.addAttribute("consulta", consulta.get());
            return "consultas/formulario";
        } else {
            return "redirect:/consultas";
        }
    }

    @PutMapping("/{id}")
    public String atualizarConsulta(@PathVariable Integer id, @ModelAttribute("consulta") Consulta consulta) {
        consultaService.atualizar(id, consulta);
        return "redirect:/consultas";
    }
}