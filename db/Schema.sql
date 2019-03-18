CREATE TABLE `Materia` ( 
`idmateria` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
 `nombreMateria` TEXT NOT NULL 
 );
 
 CREATE TABLE `Tema` ( 
 `idtema` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 
 `nombreTema` TEXT NOT NULL, 
 `Materia` INTEGER NOT NULL, 
 `explicacionTema` TEXT NOT NULL, 
 FOREIGN KEY(`Materia`) REFERENCES `Materia`(`idmateria`) 
 );

CREATE TABLE `Ejercicios` ( 
`idejercicio` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
 `ejercicio` TEXT NOT NULL,
 `Tema` INTEGER NOT NULL,
 `propiedades` TEXT,
 FOREIGN KEY(`Tema`) REFERENCES `Tema`(`idtema`) 
 );