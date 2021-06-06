-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-06-2021 a las 21:01:56
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `uta`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `destinos`
--

CREATE TABLE `destinos` (
  `CIU_DES` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `destinos`
--

INSERT INTO `destinos` (`CIU_DES`) VALUES
('Riobamba'),
('Baños'),
('Ambato'),
('Quito'),
('Guayaquil'),
('Cuenca'),
('Manta'),
('Puyo'),
('Quevedo'),
('Tena');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajeros`
--

CREATE TABLE `pasajeros` (
  `PAS_CEDULA` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `CUR_ID` int(11) NOT NULL,
  `PAS_NOMBRE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `PAS_APELLIDO` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `PAS_SALIDA` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `PAS_DESTINO` char(15) COLLATE utf8_unicode_ci NOT NULL,
  `PAS_SEXO` char(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `pasajeros`
--

INSERT INTO `pasajeros` (`PAS_CEDULA`, `CUR_ID`, `PAS_NOMBRE`, `PAS_APELLIDO`, `PAS_SALIDA`, `PAS_DESTINO`, `PAS_SEXO`) VALUES
('12345', 12, 'christian', 'nuñez', 'ambato', 'Riobamba', 'Masculino'),
('234', 23, 'Rene', 'teran', 'Letamendi ser', 'Manta', 'Masculino'),
('567', 43, 'Ezequiel', 'Sanches', 'ambato', 'Baños', 'Masculino'),
('10', 9, 'Mario', 'Perez', 'Guayaquil', 'Quito', 'Masculino');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidades`
--

CREATE TABLE `unidades` (
  `NUM_UNI` int(11) NOT NULL,
  `NOM_UNI` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `NUM_PASS` int(11) NOT NULL,
  `OBSERVACION` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `unidades`
--

INSERT INTO `unidades` (`NUM_UNI`, `NOM_UNI`, `NUM_PASS`, `OBSERVACION`) VALUES
(1, 'PRIMERO', 1, ''),
(2, 'SEGUNDO', 2, ''),
(3, 'Tercero', 3, ''),
(4, 'CUARTO', 4, ''),
(1, 'Manta', 45, ''),
(34, 'Puyo', 45, ''),
(12, 'Quevedo', 45, ''),
(5, 'Tena', 45, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `USU_CEDULA` char(10) COLLATE utf8_unicode_ci NOT NULL,
  `USU_NOMBRE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `USU_APELLIDO` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `USU_CLAVE` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `USU_PERFIL` varchar(15) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`USU_CEDULA`, `USU_NOMBRE`, `USU_APELLIDO`, `USU_CLAVE`, `USU_PERFIL`) VALUES
('0101', 'Maria', 'Reyes', '0101', 'Secretaria'),
('0202', 'Juana', 'Carrera', '0202', 'Secretaria'),
('1', 'Kevin', 'Ruiz', '1234', 'Administrador'),
('123', 'Rene', 'Teran', '1234', 'Secretaria'),
('18044', 'Christian', 'Nuñez', '0000', 'Administrador'),
('180444', 'Christian', 'Nuñez', '0000', 'Administrador'),
('1850294875', 'Pablo', 'Lopez', '1234', 'Administrador'),
('456', 'Ezequiel', 'Sanchez', '1111', 'Administrador'),
('56', 'Daniel', 'Perez', '312', 'Administrador');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`USU_CEDULA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
