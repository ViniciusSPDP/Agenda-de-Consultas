package com.clinica.agenda.dto;

public record WebhookPayload(String cpfUsuario, String loginUsuario, String whatsapp) {
}