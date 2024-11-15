create database clinicmanagerdb;

USE clinicmanagerdb;

CREATE TABLE usuarios (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(15) DEFAULT NULL,
    email VARCHAR(100) DEFAULT NULL,
    login VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    tipo_usuario ENUM('Medico', 'Atendente', 'ADMIN') NOT NULL,
    cadastrado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    editado DATETIME DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE medicos (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    crm VARCHAR(20) UNIQUE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_medicos_usuarios FOREIGN KEY (id)
        REFERENCES usuarios (id)
);

CREATE TABLE pacientes (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(15) DEFAULT NULL,
    cidade VARCHAR(50) DEFAULT NULL,
    estado VARCHAR(50) DEFAULT NULL,
    numero VARCHAR(15) DEFAULT NULL,
    cadastrado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    editado DATETIME DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE consultas (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    medico_id INT UNSIGNED NOT NULL,
    paciente_id INT UNSIGNED NOT NULL,
    descricao VARCHAR(255) DEFAULT NULL,
    cadastrado DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_inicio DATETIME DEFAULT NULL,
    data_fim DATETIME DEFAULT NULL,
    data_agendamento DATETIME NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_consultas_medicos FOREIGN KEY (medico_id)
        REFERENCES medicos (id),
    CONSTRAINT fk_consultas_pacientes FOREIGN KEY (paciente_id)
        REFERENCES pacientes (id)
);

INSERT INTO `clinicmanagerdb`.`usuarios`
(`nome`, `cpf`, `login`, `password`, `tipo_usuario`, `cadastrado`)
VALUES ('Admin', '57432795860', 'admin', 'admin', 'Admin', NOW());