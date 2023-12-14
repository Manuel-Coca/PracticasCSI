-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-12-2023 a las 12:19:56
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `cinderella`
--
CREATE DATABASE IF NOT EXISTS `cinderella` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `cinderella`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipotrabajador`
--

CREATE TABLE `tipotrabajador` (
  `id` int(32) NOT NULL,
  `nombre` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `tipotrabajador`
--

INSERT INTO `tipotrabajador` (`id`, `nombre`) VALUES
(1, 'Gestor'),
(3, 'Logística'),
(2, 'Supervisor');

--
-- Disparadores `tipotrabajador`
--
DELIMITER $$
CREATE TRIGGER `TipoTrabajador_bi` BEFORE INSERT ON `tipotrabajador` FOR EACH ROW begin
	if NEW.nombre = '' then
		signal sqlstate '45000' set 
			message_text = 'nombre no puede ser cadena vacía.';
	end if;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `TipoTrabajador_bu` BEFORE UPDATE ON `tipotrabajador` FOR EACH ROW begin
	if NEW.nombre = '' then
		signal sqlstate '45000' set 
			message_text = 'nombre no puede ser cadena vacía.';
	end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE `trabajador` (
  `id` int(32) NOT NULL,
  `tipotrabajador_id` int(32) NOT NULL,
  `nombre` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`id`, `tipotrabajador_id`, `nombre`) VALUES
(1, 2, 'Javier'),
(2, 3, 'Pablo'),
(3, 1, 'Antonio');

--
-- Disparadores `trabajador`
--
DELIMITER $$
CREATE TRIGGER `Trabajador_bi` BEFORE INSERT ON `trabajador` FOR EACH ROW begin
	if NEW.nombre = '' then
		signal sqlstate '45000' set 
			message_text = 'nombre no puede ser cadena vacía.';
	end if;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Trabajador_bu` BEFORE UPDATE ON `trabajador` FOR EACH ROW begin
	if NEW.nombre = '' then
		signal sqlstate '45000' set 
			message_text = 'nombre no puede ser cadena vacía.';
	end if;
end
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tipotrabajador`
--
ALTER TABLE `tipotrabajador`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `tipotrabajador_id` (`tipotrabajador_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tipotrabajador`
--
ALTER TABLE `tipotrabajador`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  MODIFY `id` int(32) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD CONSTRAINT `trabajador_ibfk_1` FOREIGN KEY (`tipotrabajador_id`) REFERENCES `tipotrabajador` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
