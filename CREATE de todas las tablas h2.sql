CREATE TABLE paciente ( idPaciente int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  dni varchar(45) NOT NULL,
  nombre varchar(45) NOT NULL,
  apellidos varchar(45) NOT NULL,
  direccion varchar(45) NOT NULL,
  edad int NOT NULL,
  profesion varchar(45) NOT NULL,
  baja tinyint NOT NULL);

CREATE TABLE medico (
  idmedico int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  numColegiado varchar(45) NOT NULL,
  dni varchar(45) NOT NULL,
  nombre varchar(45) NOT NULL,
  apellidos varchar(45) NOT NULL,
  fechaNaci date NOT NULL,
  fechaContratacion date NOT NULL,
  especialidad varchar(45) NOT NULL,
  baja tinyint NOT NULL); 

CREATE TABLE hospital (
  idhospital int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  nombre varchar(45) NOT NULL,
  direccion varchar(45) NOT NULL,
  telefono int NOT NULL,
  fechaApertura date NOT NULL);

CREATE TABLE consulta (
  idconsulta int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  sala int NOT NULL,
  fecha date NOT NULL,
  hora time NOT NULL,
  medicoInterviniente int NOT NULL,
  paciente int NOT NULL,
  coste double NOT NULL,
  analisisComplementarios varchar(45) NOT NULL,
  realizada tinyint NOT NULL,
  medicamentos varchar(45) NOT NULL,
  CONSTRAINT `medico_fk_Medico` FOREIGN KEY (`medicoInterviniente`) REFERENCES `medico` (`idmedico`) ON DELETE CASCADE,
  CONSTRAINT `paciente_fk_Paciente` FOREIGN KEY (`paciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE CASCADE);