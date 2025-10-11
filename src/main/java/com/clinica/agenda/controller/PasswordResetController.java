package com.clinica.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.clinica.agenda.service.UsuarioService;

@Controller
public class PasswordResetController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/esqueci-senha")
    public String showForgotPasswordForm() {
        return "usuario/esqueci-senha";
    }

    @PostMapping("/esqueci-senha")
    public String processForgotPassword(@RequestParam("cpf") String cpf, @RequestParam("whatsapp") String whatsapp,
            RedirectAttributes redirectAttributes) {

        boolean success = usuarioService.resetPassword(cpf, whatsapp);

        if (success) {
            redirectAttributes.addFlashAttribute("success",
                    "Verifique seu WhatsApp sua senha foi enviada por la.");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error",
                    "CPF ou WhatsApp inválidos. Verifique os dados e tente novamente.");
            return "redirect:/esqueci-senha";
        }
    }
}