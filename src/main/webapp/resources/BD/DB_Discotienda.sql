-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-11-2019 a las 21:19:52
-- Versión del servidor: 10.4.6-MariaDB
-- Versión de PHP: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `discotienda`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_CANCIONES` (`_id` INT)  SELECT id, nombre, duracion, precio, cantidad_stock FROM cancionesdos WHERE cancionesdos.id_disco = _id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_CANTIDADES` (`_id` INT)  SELECT cantidad_stock FROM discosdos WHERE id = _id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_DISCOS` (`_id` INT)  SELECT id, album, foto, cantidad_stock, precio FROM discosdos WHERE discosdos.id_artista = _id$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `artistas`
--

CREATE TABLE `artistas` (
  `id` int(5) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `nacimiento` date NOT NULL,
  `nacionalidad` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `artistas`
--

INSERT INTO `artistas` (`id`, `nombre`, `apellido`, `nacimiento`, `nacionalidad`) VALUES
(1, 'Antonio', 'Bose', '1985-11-05', 'Español'),
(3, 'Ricardo', 'Montaner', '1991-11-11', 'Venezolano');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cancionesdos`
--

CREATE TABLE `cancionesdos` (
  `id` int(5) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `duracion` int(2) NOT NULL,
  `id_disco` int(5) NOT NULL,
  `precio` float NOT NULL,
  `cantidad_stock` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cancionesdos`
--

INSERT INTO `cancionesdos` (`id`, `nombre`, `duracion`, `id_disco`, `precio`, `cantidad_stock`) VALUES
(2, 'NUEVO COMIENZO', 2, 2, 2000, 40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `id` int(5) NOT NULL,
  `nombreCliente` varchar(30) NOT NULL,
  `producto` text NOT NULL,
  `cantidad` int(5) NOT NULL,
  `precioTotal` float NOT NULL,
  `fechaCompra` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `discosdos`
--

CREATE TABLE `discosdos` (
  `id` int(5) NOT NULL,
  `album` varchar(30) NOT NULL,
  `foto` varchar(30) NOT NULL,
  `id_artista` int(5) NOT NULL,
  `cantidad_stock` int(5) NOT NULL,
  `precio` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `discosdos`
--

INSERT INTO `discosdos` (`id`, `album`, `foto`, `id_artista`, `cantidad_stock`, `precio`) VALUES
(2, 'No se', 'foto.jpg', 3, 50, 50000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artistas`
--
ALTER TABLE `artistas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cancionesdos`
--
ALTER TABLE `cancionesdos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_canciones_discos` (`id_disco`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `discosdos`
--
ALTER TABLE `discosdos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_discos_artistas` (`id_artista`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `artistas`
--
ALTER TABLE `artistas`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `cancionesdos`
--
ALTER TABLE `cancionesdos`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `discosdos`
--
ALTER TABLE `discosdos`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cancionesdos`
--
ALTER TABLE `cancionesdos`
  ADD CONSTRAINT `fk_canciones_discos` FOREIGN KEY (`id_disco`) REFERENCES `discosdos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `discosdos`
--
ALTER TABLE `discosdos`
  ADD CONSTRAINT `fk_discos_artistas` FOREIGN KEY (`id_artista`) REFERENCES `artistas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
