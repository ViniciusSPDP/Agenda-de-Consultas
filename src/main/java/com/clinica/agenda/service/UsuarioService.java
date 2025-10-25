package com.clinica.agenda.service;

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate; 

import com.clinica.agenda.dto.WebhookPayload; 
import com.clinica.agenda.model.Usuario;
import com.clinica.agenda.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario) {
        usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public boolean resetPassword(String cpf, String whatsapp) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCpfUsuario(cpf);

        if (usuarioOpt.isEmpty() || !usuarioOpt.get().getWhatsappUsuario().equals(whatsapp)) {
            return false;
        }

        Usuario usuario = usuarioOpt.get();

        try {
            RestTemplate restTemplate = new RestTemplate();
            String webhookUrl = "https://app-n8n.v1dvzt.easypanel.host/webhook/cba57660-13a9-41e3-a91a-25f120def536";

            WebhookPayload payload = new WebhookPayload(usuario.getCpfUsuario(), usuario.getLoginUsuario(),
                    usuario.getWhatsappUsuario());

            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, payload, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                usuario.setSenhaUsuario(passwordEncoder.encode("123"));
                usuarioRepository.save(usuario);
                return true;
            }
            return false;

        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }
}