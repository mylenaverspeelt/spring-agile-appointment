# Agile Appointment

Este projeto consiste em uma aplicação de linha de comando para marcação/desmarcação de consultas médicas. Ele foi desenvolvido como parte do Processo Seletivo da Aceleradora Ágil onde pude demonstrar minhas habilidades com Spring e H2 database.

- A aplicação está hospedada na ** e pode ser acessada através do seguinte link:

### Funcionalidades

- Cadastro de Paciente: Permite cadastrar novos pacientes garantindo unicidade pelo número de telefone.
- Marcação de Consultas: Permite agendar consultas verificando disponibilidade de data e hora.
- Cancelamento de Consultas: Permite cancelar consultas agendadas, exibindo informações antes da confirmação.
- Tratamento de Erros: Evita duplicidade de cadastro pelo mesmo telefone, impede marcação em horários já agendados e não permite agendamento retroativo.
- Validação com Expressões Regulares (Regex): Utilizo expressões regulares para verificar e validar os inputs dos usuários, garantindo que as informações fornecidas estejam no formato correto e atendam aos critérios específicos de cada campo.
- Opção de Retornar ao Menu Principal: Durante qualquer etapa do processo, o usuário pode optar por abortar a operação atual e retornar ao menu principal do sistema.

### Tecnologias Utilizadas

- Spring Boot + Spring Boot DevTools
- Spring Boot Starter Data JPA: Utilizado para persistência de dados com o H2 Database
- H2 Database: Banco de dados embarcado para persistência dos dados da aplicação

### Estrutura do Projeto

```
agile-appointment
├── data
│   ├── appointments.mv.db
│   └── appointments.trace.db
├── src
│   ├── main
│   │   ├── java/com/project/agile_appointment
│   │   │   ├── main
│   │   │   │   ├── ExecuteMenu.java
│   │   │   ├── models
│   │   │   │   ├── Appointment.java
│   │   │   │   └── Patient.java
│   │   │   ├── repository
│   │   │   │   ├── AppointmentRepository.java
│   │   │   │   └── PatientRepository.java
│   │   │   ├── service
│   │   │   │   ├── AppointmentService.java
│   │   │   │   └── PatientService.java
│   │   │   └── AgileAppointmentApplication.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java/com/project/agile_appointment
│           └── AgileAppointmentApplicationTests.java
├── .gitignore
├── mvnw
├── mvnw.cmd
└── pom.xml

```

### Download do Projeto

Pré-requisitos:

- Git
- Java Development Kit (JDK) na versão 17
- Maven

1. Abra o terminal e clone o repositório:

```
git clone https://github.com/mylenaverspeelt/spring-agile-appointment
```

2. Compilar e Executar o Projeto:

- Navegue até o diretório raiz do projeto clonado
- Para compilar e executar o projeto usando Maven, utilize os comandos:

```
mvn clean install
mvn spring-boot:run
```

- A aplicação será inicializada e o menu será executado
