Create TABLE paciente (
  `idPaciente` INTEGER NOT NULL ,
  `dni` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `edad` int NOT NULL,
  `profesion` varchar(45) NOT NULL,
  `baja` tinyint NOT NULL,
  PRIMARY KEY (`idPaciente` AUTOINCREMENT)
);

CREATE TABLE `medico` (
  `idmedico` INTEGER NOT NULL ,
  `numColegiado` varchar(45) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `fechaNaci` date NOT NULL,
  `fechaContratacion` date NOT NULL,
  `especialidad` varchar(45) NOT NULL,
  `baja` tinyint NOT NULL,
  PRIMARY KEY (`idmedico` AUTOINCREMENT)
);

CREATE TABLE `hospital` (
  `idhospital` INTEGER NOT NULL ,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` int NOT NULL,
  `fechaApertura` date NOT NULL,
  PRIMARY KEY (`idhospital` AUTOINCREMENT)
);

CREATE TABLE `consulta` (
  `idconsulta` INTEGER NOT NULL ,
  `sala` int NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `medicoInterviniente` int NOT NULL,
  `paciente` int NOT NULL,
  `coste` double NOT NULL,
  `analisisComplementarios` varchar(45) NOT NULL,
  `realizada` tinyint NOT NULL,
  `medicamentos` varchar(45) NOT NULL,
  PRIMARY KEY (`idconsulta` AUTOINCREMENT),
  CONSTRAINT `medico_fk_Medico` FOREIGN KEY (`medicoInterviniente`) REFERENCES `medico` (`idmedico`) ON DELETE CASCADE,
  CONSTRAINT `paciente_fk_Paciente` FOREIGN KEY (`paciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE CASCADE
);


