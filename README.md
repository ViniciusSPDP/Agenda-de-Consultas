# Clínica Agenda - Sistema de Agendamento de Consultas

## 📄 Sobre o Projeto

Clínica Agenda é uma aplicação web desenvolvida para simplificar o gerenciamento de uma clínica médica. O sistema permite o cadastro de médicos, o agendamento de consultas e a visualização da agenda completa, tudo isso protegido por um sistema de autenticação de usuários com recuperação de senha.

A interface foi construída com foco na simplicidade e usabilidade, utilizando tecnologias modernas para garantir uma experiência fluida e responsiva.

## ✨ Funcionalidades Principais

### Autenticação de Usuários
- Cadastro de novas contas de usuário.
- Login seguro com criptografia de senhas (BCrypt).
- Funcionalidade de "Esqueci a Senha" com notificação via WhatsApp (através de um webhook).

### Gerenciamento de Médicos (CRUD)
- Adicionar, editar e remover médicos do sistema.
- Listagem completa de todos os médicos cadastrados.

### Gerenciamento de Consultas (CRUD)
- Agendar novas consultas, associando um paciente a um médico e uma data.
- Editar detalhes de consultas existentes.
- Excluir agendamentos.

### Visualização de Agendas
- Painel de controle com acesso rápido às principais funcionalidades.
- Página de "Agenda Completa" que exibe todas as consultas agrupadas por médico.

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

### Backend
- Java 21
- Spring Boot 3
- Spring Security (para autenticação e autorização)
- Spring Data JPA / Hibernate (para persistência de dados)
- Maven (gerenciador de dependências)

### Frontend
- Thymeleaf (motor de templates do lado do servidor)
- Tailwind CSS (framework CSS para estilização moderna)
- HTML5

### Banco de Dados
- PostgreSQL

### Serviços Externos
- Webhook (n8n) para notificações via WhatsApp na recuperação de senha.

## 🚀 Como Executar o Projeto

Para executar o projeto em seu ambiente local, siga os passos abaixo.

### Pré-requisitos

- JDK 21 ou superior.
- Maven 3.9 ou superior.
- Uma instância do PostgreSQL em execução.

### Instalação e Configuração

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/agenda-de-consultas.git
cd agenda-de-consultas
```

2. **Configure o banco de dados:**
   - Na pasta `src/main/resources/`, renomeie o arquivo `application.properties.example` para `application.properties`.
   - Abra o arquivo `application.properties` e insira as suas credenciais do PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

3. **Execute a aplicação:**
   - Utilize o Maven Wrapper para iniciar o servidor Spring Boot.

```bash
# No Linux ou macOS
./mvnw spring-boot:run

# No Windows
./mvnw.cmd spring-boot:run
```

4. **Acesse o sistema:**
   - Abra o seu navegador e acesse `http://localhost:8080`.

## 📂 Estrutura do Projeto

```
.
├── src
│   ├── main
│   │   ├── java/com/clinica/agenda
│   │   │   ├── controller   # Controladores (endpoints da aplicação)
│   │   │   ├── dto          # Data Transfer Objects
│   │   │   ├── model        # Entidades JPA (representação do banco)
│   │   │   ├── repository   # Interfaces do Spring Data JPA
│   │   │   ├── security     # Configurações do Spring Security
│   │   │   └── service      # Lógica de negócio
│   │   └── resources
│   │       ├── static       # Arquivos estáticos (JS, CSS, imagens)
│   │       ├── templates    # Templates HTML com Thymeleaf
│   │       └── application.properties # Configurações da aplicação
│   └── test                 # Testes unitários
└── pom.xml                  # Dependências e build do Maven
```

## ✒️ Autor

**Vinícius Soares**

- LinkedIn: [https://www.linkedin.com/in/vinicius-saraiva-55255a199/](https://www.linkedin.com/in/vinicius-saraiva-55255a199/)
- GitHub: [https://github.com/viniciusspdp](https://github.com/viniciusspdp)