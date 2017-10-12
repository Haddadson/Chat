/*
Created: 24/09/2017
Modified: 28/09/2017
Model: PostgreSQL 9.4
Database: PostgreSQL 9.4
*/


-- Create tables section -------------------------------------------------

-- Table Mensagem

CREATE TABLE Mensagem(
 COD_remetente Bigint NOT NULL,
 COD_mensagem BigSerial NOT NULL,
 COD_destinatario Bigint,
 COD_salaDestino Bigint,
 DAT_msg Timestamp NOT NULL,
 TXT_conteudo Varchar NOT NULL
)
;

-- Create indexes for table Mensagem

CREATE INDEX IX_Relationship3 ON Mensagem (COD_salaDestino)
;

CREATE INDEX IX_Relationship4 ON Mensagem (COD_destinatario)
;

-- Add keys for table Mensagem

ALTER TABLE Mensagem ADD CONSTRAINT Key1 PRIMARY KEY (COD_remetente,COD_mensagem)
;

-- Table Usuario

CREATE TABLE Usuario(
 COD_usuario BigSerial NOT NULL,
 NOM_usuario Varchar NOT NULL
)
;

-- Add keys for table Usuario

ALTER TABLE Usuario ADD CONSTRAINT Key2 PRIMARY KEY (COD_usuario)
;

-- Table Sala

CREATE TABLE Sala(
 COD_sala BigSerial NOT NULL,
 NOM_sala Varchar NOT NULL,
 TXT_senha Varchar
)
;

-- Add keys for table Sala

ALTER TABLE Sala ADD CONSTRAINT Key3 PRIMARY KEY (COD_sala)
;

-- Table UsuarioSala

CREATE TABLE UsuarioSala(
 COD_sala Bigint NOT NULL,
 COD_usuario Bigint NOT NULL
)
;

-- Add keys for table UsuarioSala

ALTER TABLE UsuarioSala ADD CONSTRAINT Key4 PRIMARY KEY (COD_sala,COD_usuario)
;
-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE Mensagem ADD CONSTRAINT Relationship1 FOREIGN KEY (COD_remetente) REFERENCES Usuario (COD_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Mensagem ADD CONSTRAINT Relationship3 FOREIGN KEY (COD_salaDestino) REFERENCES Sala (COD_sala) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE Mensagem ADD CONSTRAINT Relationship4 FOREIGN KEY (COD_destinatario) REFERENCES Usuario (COD_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE UsuarioSala ADD CONSTRAINT Relationship5 FOREIGN KEY (COD_sala) REFERENCES Sala (COD_sala) ON DELETE NO ACTION ON UPDATE NO ACTION
;

ALTER TABLE UsuarioSala ADD CONSTRAINT Relationship6 FOREIGN KEY (COD_usuario) REFERENCES Usuario (COD_usuario) ON DELETE NO ACTION ON UPDATE NO ACTION
;
