Create TABLE `recuperacionad`.`paciente` (
  `idPaciente` int NOT NULL AUTO_INCREMENT,
  `dni` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `edad` int NOT NULL,
  `profesion` varchar(45) NOT NULL,
  `baja` tinyint NOT NULL,
  PRIMARY KEY (`idPaciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `medico` (
  `idmedico` int NOT NULL AUTO_INCREMENT,
  `numColegiado` varchar(45) NOT NULL,
  `dni` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `fechaNaci` date NOT NULL,
  `fechaContratacion` date NOT NULL,
  `especialidad` varchar(45) NOT NULL,
  `baja` tinyint NOT NULL,
  PRIMARY KEY (`idmedico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `consulta` (
  `idconsulta` int NOT NULL AUTO_INCREMENT,
  `sala` int NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `medicoInterviniente` int NOT NULL,
  `paciente` int NOT NULL,
  `coste` double NOT NULL,
  `analisisComplementarios` varchar(45) NOT NULL,
  `realizada` tinyint NOT NULL,
  PRIMARY KEY (`idconsulta`),
  KEY `medico_fk_Medico_idx` (`medicoInterviniente`),
  KEY `paciente_fk_Paciente_idx` (`paciente`),
  CONSTRAINT `medico_fk_Medico` FOREIGN KEY (`medicoInterviniente`) REFERENCES `medico` (`idmedico`) ON DELETE CASCADE,
  CONSTRAINT `paciente_fk_Paciente` FOREIGN KEY (`paciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


