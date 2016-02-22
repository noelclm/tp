-- phpMyAdmin SQL Dump
-- version 3.5.8.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:3306
-- Tiempo de generación: 26-01-2016 a las 11:27:45
-- Versión del servidor: 5.0.95
-- Versión de PHP: 5.3.29

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `sercaib_website`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int(11) NOT NULL auto_increment,
  `nombre` varchar(100) character set utf8 NOT NULL,
  `direccion` varchar(100) character set utf8 NOT NULL,
  `telefono` varchar(10) character set utf8 NOT NULL,
  `email` varchar(50) character set utf8 NOT NULL,
  `pass` varchar(50) character set utf8 NOT NULL,
  `id_pdf` int(11) NOT NULL,
  `borrado` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=95 ;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `nombre`, `direccion`, `telefono`, `email`, `pass`, `id_pdf`, `borrado`) VALUES
(2, 'prueba', 'Prueba', '123456789', 'metsuke@gmail.com', 'prueba', 0, 0),
(3, 'SAN EULOGIO', 'MADRID', '913321515', '', 'EUL001', 0, 0),
(4, 'SAGRADO CORAZON', 'MADRID', '', '', 'COR002', 0, 0),
(6, 'CUQUITOS', 'MADRID', '', '', 'CUQ004', 0, 0),
(7, 'AFUERA', '', '', '', 'AFU005', 0, 0),
(8, 'AFUERA GUARDERIA', '', '', '', 'AFG006', 0, 0),
(9, 'SANTA ELIZABETH', '', '', '', 'ELI007', 0, 0),
(10, 'CASTILLA', '', '', '', 'CAS008', 0, 0),
(11, 'SANTA MARIA', '', '', '', 'MAR009', 0, 0),
(12, 'PIPOS', '', '', '', 'PIP010', 0, 0),
(13, 'LUIS FEITO', '', '', '', 'LUI011', 0, 0),
(14, 'VIRGEN DEL PUERTO', '', '', '', 'VIR012', 0, 0),
(15, 'CHIQUIPATIO', '', '', '', 'CHI013', 0, 0),
(16, 'PAIS DE LA FANTASIA', '', '', '', 'FAN014', 0, 0),
(17, 'SAGRADA FAMILIA', 'ARANJUEZ', '', '', 'SAF015', 0, 0),
(18, 'VICENTE ALEIXANDRE', 'ARANJUEZ', '', '', 'ALE016', 0, 0),
(20, 'SALESIANOS LOYOLA', 'ARANJUEZ', '', '', 'LOY018', 0, 0),
(23, 'GREGORIO CANELLA', 'VILLALBILLA', '', '', 'CAN021', 0, 0),
(24, 'AYUNTAMIENTO ANCHUELO', 'ANCHUELO', '', '', 'ANC022', 0, 0),
(25, 'VENECIA', 'ALCALA DE HENARES', '', '', 'VEN023', 0, 0),
(26, 'CRISTOBAL COLON', 'ALCALA DE HENARES', '', '', 'COL024', 0, 0),
(28, 'NURSERY SCHOOL', 'ALCALA DE HENARES', '', '', 'NUR026', 0, 0),
(29, 'SAN JUAN BOSCO', 'TORREJON DE ARDOZ', '', '', 'BOS027', 0, 0),
(30, 'COLORIN COLORADO', 'AZUQUECA', '', '', 'COL028', 0, 0),
(31, 'ANGEL CASTRO', 'VALDEAVERO', '', '', 'ANG029', 0, 0),
(32, 'MILOU', 'GUADALAJARA', '', '', 'MIL030', 0, 0),
(33, 'TINTIN', 'GUADALAJARA', '', '', 'TIN031', 0, 0),
(34, 'VITALIA', 'ALCALA DE HENARES', '', '', 'VIT032', 0, 0),
(35, 'LAS SETITAS', 'QUER', '', '', 'SET033', 0, 0),
(36, 'CARTERPILLAR', 'MARCHAMALO', '', '', 'CAR034', 0, 0),
(37, 'CAMINO REAL', 'TORREJON DE ARDOZ', '', '', 'CAM035', 0, 0),
(38, 'APSA', 'ARGANDA DEL REY', '', '', 'APS036', 0, 0),
(39, 'CASTILLO', 'PERALES DE TAJUÃƒÂ‘A', '', '', 'PER037', 0, 0),
(40, 'CARLOS RUIZ', 'TIELMES', '', '', 'TIE038', 0, 0),
(42, 'BELMONTE', 'BELMONTE DE TAJO', '', '', 'BEL040', 0, 0),
(43, 'COLORINES', 'VILLAREJO DE SALVANES', '', '', 'COL041', 0, 0),
(44, 'VICTORIA', 'VILLAREJO DE SALVANES', '', '', 'VIC042', 0, 0),
(45, 'SANTA ELENA', 'VILLAREJO DE SALVANES', '', '', 'ELE043', 0, 0),
(46, 'CARLOS RUIZ', 'ESTREMERA', '', '', 'EST044', 0, 0),
(47, 'ORUSCO', 'ORUSCO', '', '', 'ORU045', 0, 0),
(48, 'VILLAR DEL OLMO', 'VILLAR DE OLMO', '', '', 'OLM046', 0, 0),
(49, 'CINCO ESTRELLAS', 'MADRID', '', '', 'CIN047', 0, 0),
(50, 'IRIS', 'MADRID', '', '', 'IRI048', 0, 0),
(52, 'JUAN RAMON JIMENEZ', 'MADRID', '', '', 'JIM050', 0, 0),
(53, 'GENIOS', 'MADRID', '', '', 'GEN051', 0, 0),
(54, 'ESAB', 'SAN FERNANDO DE HENARES', '', '', 'ESA052', 0, 0),
(55, 'BICHEJOS', 'MEJORADA DEL CAMPO', '', '', 'BIC053', 0, 0),
(56, 'BICHINES', 'SAN FERNANDO DE HENARES', '', '', 'BICH54', 0, 0),
(57, 'LOS REMEDIOS', 'ALCORCON', '', '', 'REM055', 0, 0),
(59, 'BOSQUE ENCANTADO', 'SESEÃƒÂ‘A', '', '', 'BOS057', 0, 0),
(60, 'SAN JOSE LUCERO', 'MADRID', '', '', 'LUC058', 0, 0),
(61, 'ADDIS', 'MADRID', '', '', 'ADD059', 0, 0),
(62, 'LOPE DE VEGA', 'ALCALA DE HENARES', '', '', 'LOP060', 0, 0),
(63, 'PENAS ALBAS', 'VILLALBILLA', '', '', 'PEN061', 0, 0),
(64, 'LA GARENA', 'ALCALA DE HENARES', '', '', 'GAR062', 0, 0),
(66, 'LICEO CONSUL', 'MADRID', '', '', 'LIC064', 0, 0),
(67, 'CARLOS SAINZ', 'MADRID', '', '', 'CAR065', 0, 0),
(68, 'HISPANIDAD', 'MADRID', '', '', 'HIS066', 0, 0),
(69, 'LAS NIEVES', 'MADRID', '', '', 'NIE067', 0, 0),
(70, 'PABLO NERUDA', 'COSLADA', '', '', 'PAB068', 0, 0),
(71, 'ALFREDO LANDA', 'COSLADA', '', '', 'ALF069', 0, 0),
(72, 'REINA VICTORIA', '', '', '', 'REI070', 0, 0),
(73, 'FRUTIS', 'Torrelodones', '', '', 'FRU200', 0, 0),
(74, 'MIGUEL HERNANDEZ', 'C/ MAGDALENA S/N TORREJON DE ARDOZ', '', '', 'MIGU72', 0, 0),
(75, 'DAOIZ Y VELARDE', 'C/ INFANTADO, 2 ALCALA DE HENARES', '', '', 'DAOI73', 0, 0),
(76, 'SHAKESPEARE', 'C/PUERTO DE BILBAO COSLADA', '', '', 'SHAK74', 0, 0),
(77, 'LUIS BELLO', 'C/ DE LUIS CABRERA, 66 MADRID', '', '', 'LUIS75', 0, 0),
(78, 'PRAVIA', 'ALCALA DE HENARES', '', '', 'PRAV76', 0, 0),
(79, 'ANTONIO NEBRIJA', '', '', '', 'NEB77', 0, 0),
(80, 'LOS SANTOS', 'AV. DE LO QUEMADO, S/N', '', '', 'LOSS78', 0, 0),
(81, 'JUAN GOYENECHE', 'AV. GLASGOW, S/N', '', '', 'GOYE79', 0, 0),
(82, 'ALARILLA', '', '', '', 'FUEN80', 0, 0),
(83, 'LEGANES', '', '', '', 'LEGA81', 0, 0),
(84, 'LORANCA', '', '', '', 'LOR082', 0, 0),
(85, 'E.I. TARABILLA', '', '', '', 'TAR083', 0, 0),
(86, 'YAKI', '', '', '', 'YAK084', 0, 0),
(87, 'CHARLES DICKENS', '', '', '', 'CHAR085', 0, 0),
(88, 'ALBA', '', '', '', 'ALB083', 0, 0),
(89, 'BAMBIS', '', '', '', 'BAM084', 0, 0),
(90, 'LAS ESCUELAS', '', '', '', 'ESC085', 0, 0),
(91, 'SANTORCAZ', '', '', '', 'SANT086', 0, 0),
(92, 'SANTA LUCÃƒÂA', '', '', '', 'CARA090', 0, 0),
(93, 'SANCHINARRO', '', '', '', 'SANC092', 0, 0),
(94, 'LITTLE NURSERY', '', '', '', 'LIT085', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Contenido`
--

CREATE TABLE IF NOT EXISTS `Contenido` (
  `id` int(11) NOT NULL auto_increment,
  `clave` varchar(50) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `contenido` text NOT NULL,
  `borrado` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Volcado de datos para la tabla `Contenido`
--

INSERT INTO `Contenido` (`id`, `clave`, `descripcion`, `contenido`, `borrado`) VALUES
(1, 'QUIENES_SOMOS', 'Quienes Somos', '<p>SERCAIB ofrece un servicio de calidad en la elaboraci&oacute;n de men&uacute;s para empresas, centros de ense&ntilde;anza, guarder&iacute;as infantiles, colegios mayores, cafeter&iacute;as, hospitales, campamentos, albergues, residencias para la tercera edad&hellip; avalado por un capital t&eacute;cnico y humano inigualable.</p>\r\n\r\n<p>SERCAIB CATERING IB&Eacute;RICA es una empresa dedicada a la restauraci&oacute;n colectiva en lo que se refiere a la elaboraci&oacute;n y servicio de men&uacute;s.</p>\r\n\r\n<p>Bajo las premisas de &quot;Calidad y Servicio&quot; hemos logrado consolidar esta empresa, manteniendo un gran esp&iacute;ritu de servicio hacia nuestros clientes, as&iacute; como una estrecha relaci&oacute;n con nuestros proveedores, basada en el trato individual y personalizado hacia todos y cada uno de ellos.</p>\r\n\r\n<p>Apostamos por la integraci&oacute;n y potenciaci&oacute;n de nuestro &quot;Capital Humano&quot; mediante Planes de Formaci&oacute;n Continua, para ofrecerle la excelencia que merece.</p>\r\n\r\n<p><os capital="" de="" la="" mediante="" n="" nuestro="" p="" planes="" por="" y=""> </os></p>\r\n\r\n<p>Adem&aacute;s, para dotar de uniformidad a nuestro personal, tenemos establecidos uniformes para cada puesto de trabajo: Cocina, Limpieza, Monitoras/es, Servicio al p&uacute;blico, etc.</p>\r\n', 0),
(2, 'SERVICIOS_MAIN', 'Servicios > Texto Principal', '<p>Nuestra actividad principal es la elaboraci&oacute;n y servicio de comidas en los siguientes sectores:</p>\r\n\r\n<ul>\r\n	<li>Empresas</li>\r\n	<li>Centros de Ense&ntilde;anza (P&uacute;blicos y Privados)</li>\r\n	<li>Guarder&iacute;as Infantiles y Jardines de Infancia</li>\r\n	<li>Colegios Mayores</li>\r\n	<li>Cafeter&iacute;as</li>\r\n	<li>Hospitales</li>\r\n	<li>Campamentos y Albergues</li>\r\n	<li>Residencias para la Tercera Edad y Centros de D&iacute;a</li>\r\n</ul>\r\n', 0),
(3, 'SERVICIOS_TRADICIONAL', 'Servicios > Linea Tradicional', '<p>La comida pasa por un proceso de cocci&oacute;n y, a continuaci&oacute;n, se sirve o se mantiene en caliente hasta el momento de servicio</p>\r\n\r\n<ul>\r\n	<li>Periodo de retenci&oacute;n limitado.</li>\r\n	<li>Dificulta la disociaci&oacute;n espacial entre el lugar de elaboraci&oacute;n y el de consumo.</li>\r\n	<li>La l&iacute;nea de utilizaci&oacute;n m&aacute;s universal en el sector.</li>\r\n</ul>\r\n', 0),
(4, 'SERVICIOS_FRIA', 'Servicios > Linea Fria', '<p>En este caso, la comida, una vez sometida a la operaci&oacute;n de cocci&oacute;n, se enfr&iacute;a r&aacute;pidamente y se mantiene en fr&iacute;o hasta el momento de su posterior calentamiento y servicio en caliente.</p>\r\n\r\n<ul>\r\n	<li>Amplia gama de posibilidades.</li>\r\n	<li>Periodo de mantenimiento en refrigeraci&oacute;n var&iacute;a desde unos pocos d&iacute;as a varias semanas.</li>\r\n	<li>Sometida a una congelaci&oacute;n permite conservarla varios meses.</li>\r\n</ul>\r\n', 0),
(5, 'MEDIOSTECNICOS_SUPERIOR', 'Medios Tecnicos > Parte Superior', '<h1>MEDIOS T&Eacute;CNICOS COMUNES A LAS DOS L&Iacute;NEAS DE ELABORACI&Oacute;N</h1>\r\n\r\n<h2>COCINA CENTRAL</h2>\r\n\r\n<p>Nuestras instalaciones cuentan con una superficie de 400 m2 equipada con la tecnolog&iacute;a m&aacute;s avanzada y los adelantos t&eacute;cnicos m&aacute;s sofisticados del sector.</p>\r\n\r\n<h2>ALMAC&Eacute;N Y C&Aacute;MARAS</h2>\r\n\r\n<p>En nuestras c&aacute;maras frigor&iacute;ficas, los alimentos se almacenan a la temperatura id&oacute;nea para su conservaci&oacute;n y posterior utilizaci&oacute;n.</p>\r\n\r\n<ul>\r\n	<li>Zona de elaboraci&oacute;n</li>\r\n	<li>Office</li>\r\n</ul>\r\n\r\n<h2>SERVICIO EN EL PROPIO CENTRO</h2>\r\n\r\n<p>Para aquellos sectores que dispongan de cocina in situ, proporcionamos un servicio de elaboraci&oacute;n de las comidas en el propio centro, siendo SERCAIB la encargada de la compra y elaboraci&oacute;n de materias primas, servicio de limpieza, etc.</p>\r\n', 0),
(6, 'MEDIOSTECNICOS_MEDIO', 'Medios Tecnicos > Parte Intermedia', '<h2>OFICINAS</h2>\r\n\r\n<p>Est&aacute;n totalmente equipadas con los medios m&aacute;s adecuados para atender y satisfacer todas las necesidades.</p>\r\n\r\n<h2>FLOTA DE VEH&Iacute;CULOS</h2>\r\n\r\n<p>En SERCAIB disponemos de una amplia flota de veh&iacute;culos con la que podemos cubrir perfectamente las necesidades de nuestros clientes.</p>\r\n\r\n<h2>ZONA DE ELABORACI&Oacute;N</h2>\r\n\r\n<p>Nuestros profesionales disponen de una zona propia donde pueden iniciar los preparativos de los ingredientes que ser&aacute;n utilizados en la elaboraci&oacute;n de los men&uacute;s en l&iacute;nea tradicional.</p>\r\n\r\n<h2>ZONA DE ENVASADO</h2>\r\n\r\n<p>Es la zona destinada a trasvasar los alimentos a los termos designados a tal fin.</p>\r\n\r\n<h2>TRANSPORTE</h2>\r\n\r\n<p>Durante el transporte, los termos son ubicados en orden y seg&uacute;n rutas para facilitar el trabajo a nuestros conductores. Los veh&iacute;culos disponen del certificado isotermo para garantizar una temperatura adecuada tanto en transporte caliente como en fr&iacute;o.</p>\r\n\r\n<h1>MEDIOS T&Eacute;CNICOS COMUNES A LAS DOS L&Iacute;NEAS DE ELABORACI&Oacute;N</h1>\r\n\r\n<h2>ZONA DE ELABORACI&Oacute;N</h2>\r\n\r\n<p>Zona totalmente independiente dotada con lo &uacute;ltimo en tecnolog&iacute;a de l&iacute;nea fr&iacute;a, para la elaboraci&oacute;n de los men&uacute;s producidos a trav&eacute;s de la l&iacute;nea fr&iacute;a.</p>\r\n', 0),
(7, 'MEDIOSTECNICOS_INFERIOR', 'Medios Tecnicos > Parte INferior', '<h2>ZONA DE ENVASADO</h2>\r\n\r\n<p>El envasado de los distintos tipos de men&uacute;s es llevado a cabo en un sector aislado del resto de la cocina, y el personal encargado de la elaboraci&oacute;n de estos men&uacute;s es distinto al personal responsable en l&iacute;nea tradicional, para evitar posibles cruces de alimentos.</p>\r\n\r\n<h2>TRANSPORTE</h2>\r\n\r\n<p>En el transporte de los men&uacute;s elaborados a trav&eacute;s de la l&iacute;nea fr&iacute;a, los termos son ubicados en orden y seg&uacute;n rutas para facilitar el trabajo a nuestros conductores. Los veh&iacute;culos disponen del certificado isotermo para garantizar una temperatura adecuada tanto en transporte fr&iacute;o como en caliente.</p>\r\n', 0),
(8, 'NUTRICION_MAIN', 'Nutricion y Calidad > Principal', '<p>SERCAIB cree en la importancia del trabajo en equipo del departamento de Calidad y el departamento de Nutrici&oacute;n, ya que para una correcta alimentaci&oacute;n es de vital importancia que tanto la materia prima como la elaboraci&oacute;n y distribuci&oacute;n de los alimentos sean correctas.</p>\r\n\r\n<p>D&ntilde;a. Raquel Contreras Asenjo, Diplomada en Nutrici&oacute;n Humana y Diet&eacute;tica, con la colaboraci&oacute;n de Sergio Recio Sanz, Director de Producci&oacute;n, son los encargados de la elaboraci&oacute;n de los men&uacute;s.</p>\r\n\r\n<p>El Departamento de Calidad orienta su pol&iacute;tica hacia la plena satisfacci&oacute;n del cliente. Dicha pol&iacute;tica est&aacute; soportada por el Sistema de Gesti&oacute;n de la Calidad UNE&ndash;EN-ISO 9001:2008, que tiene como objetivo evitar la generaci&oacute;n de productos no conformes en todos los procesos de comercializaci&oacute;n.</p>\r\n', 0),
(9, 'NUTRICION_MENU', 'Nutricion y Calidad > Menu', '<p>SERCAIB elabora sus men&uacute;s atendiendo a normas internacionales de alimentaci&oacute;n, as&iacute; como respetando las directrices que nos exigen desde el Ministerio de Sanidad y Consumo.</p>\r\n\r\n<p>No se consigue una dieta eficaz si la comida no est&aacute; bien complementada con una cena adecuada, por eso, SERCAIB ofrece en el mismo folleto divulgativo las cenas complementarias a los men&uacute;s de las comidas.</p>\r\n\r\n<p><a href="./pdf/piramide_de_frecuencia.pdf">Ver pir&aacute;mide de frecuencia</a></p>\r\n\r\n<p>Adem&aacute;s, la pol&iacute;tica de adquisici&oacute;n de las materias primas se fundamenta en una rigurosa selecci&oacute;n de proveedores, con el objetivo de conseguir productos de primera calidad y marcas l&iacute;deres en su gama.</p>\r\n\r\n<h1>CARACTER&Iacute;STICAS</h1>\r\n\r\n<p>Los men&uacute;s que SERCAIB ofrece diariamente a sus clientes est&aacute;n compuestos por:</p>\r\n', 0),
(10, 'NUTRICION_CONTROL', 'Nutricion y Calidad > Control', '<p>SERCAIB puede ofrecer un men&uacute; espec&iacute;fico para cada tipo de intolerancia alimenticia, siendo una de las m&aacute;s comunes la &quot;Intolerancia al Gluten&quot;, tambi&eacute;n conocida como &quot;Celiaqu&iacute;a&quot;. Formamos parte de la Asociaci&oacute;n de Celiacos de Madrid y todo nuestro personal est&aacute; instruido en este tipo de intolerancias.</p>\r\n\r\n<p>Con una pol&iacute;tica orientada a la plena satisfacci&oacute;n del cliente, el departamento de calidad trabaja por el cumplimiento de la norma <strong>UNE-EN ISO 9001:2008</strong>, que es de car&aacute;cter internacional y se utiliza como referencia para el desarrollo de un Sistema de Gesti&oacute;n de Calidad.</p>\r\n\r\n<p>Adem&aacute;s de llevar a cabo las actividades de autocontrol necesarias para asegurar la higiene de los productos elaborados, Sercaib tiene contratados los servicios de un departamento de Bromatolog&iacute;a que, mediante an&aacute;lisis precisos, asegura el correcto estado, tanto de los alimentos como de los manipuladores y superficies.</p>\r\n\r\n<p>Asimismo, nuestras instalaciones, equipos de cocina y comedor, est&aacute;n dise&ntilde;ados para facilitar el trabajo y disminuir a su vez los riesgos sanitarios, seg&uacute;n las Normas Higi&eacute;nico-Sanitarias actuales.</p>\r\n\r\n<p>SERCAIB CATERING IB&Eacute;RICA S.L. est&aacute; certificada en la norma UNE-EN-ISO 9001:2008 desde el mes de junio del a&ntilde;o 2004.</p>\r\n\r\n<ul>\r\n	<li>Certificado de la Norma Iso.</li>\r\n	<li>N&uacute;mero de Registro Sanitario.</li>\r\n	<li>Clasificaci&oacute;n expedida por Hacienda.</li>\r\n</ul>\r\n', 0),
(11, 'MEDIO_AMBIENTE', 'Medio Ambiente', '<h1>SERCAIB, RESPONSABLE CON EL MEDIO AMBIENTE</h1>\r\n\r\n<p>Sercaib es una empresa dedicada a la restauraci&oacute;n, que apuesta por el respeto del medio ambiente como componente esencial en el desarrollo de todas nuestras actividades, de acuerdo a los siguientes principios:</p>\r\n\r\n<ol>\r\n	<li>Cumplimiento de la legislaci&oacute;n ambiental y requisitos propios de Sercaib.</li>\r\n	<li>Planificaci&oacute;n de actividades y aplicaci&oacute;n de actuaciones para minimizar los posibles impactos.</li>\r\n	<li>Aprovechamiento racional de los recursos naturales y compromiso de prevenci&oacute;n de la contaminaci&oacute;n.</li>\r\n	<li>Utilizaci&oacute;n de la tecnliog&iacute;a adecuada para mejorar la eficiencia energ&eacute;tica de la instalaci&oacute;n y disminuir los efectos sobre el medio ambiente.</li>\r\n	<li>Comunicaci&oacute;n permanente y colaboraci&oacute;n con los principales grupos de inter&eacute;s.</li>\r\n	<li>Implicaci&oacute;n de los proveedores en el cumplimiento de los requisitos ambientales asumidos por Sercaib.</li>\r\n	<li>Formaci&oacute;n y sensibilizaci&oacute;n ambiental del personal. La concienciaci&oacute;n ambiental de Sercaib implica a toda la organizaci&oacute;n en su compromiso con la protecci&oacute;n del medio ambiente.</li>\r\n</ol>\r\n\r\n<p>M&ordf; Mar Llanos Alonso - Gerente</p>\r\n', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ContenidoClientes`
--

CREATE TABLE IF NOT EXISTS `ContenidoClientes` (
  `id` int(11) NOT NULL auto_increment,
  `categoria_id` int(11) NOT NULL,
  `contenido` varchar(200) NOT NULL,
  `borrado` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=76 ;

--
-- Volcado de datos para la tabla `ContenidoClientes`
--

INSERT INTO `ContenidoClientes` (`id`, `categoria_id`, `contenido`, `borrado`) VALUES
(1, 1, 'C.P, JUAN RAMON JIMENEZ (Madrid)', 0),
(2, 1, 'C.P, CARLOS RUIZ (Estremera)', 0),
(3, 1, 'C.P, VILLAR DEL OLMO (Villar del Olmo)', 0),
(4, 1, 'C.P, CARLOS RUIZ (Tielmes)', 0),
(5, 1, 'C.P, LOS OLIVOS (Brea de Tajo)', 0),
(6, 1, 'C.P, BELMONTE (Belmonte de Tajo)', 0),
(7, 1, 'C.P, BELMONTE (Belmonte de Tajo)', 0),
(8, 1, 'C.P, GREGORIO CAN ELLA (Villalbilla)', 0),
(9, 1, 'C.P, VIRGEN DEL AMOR HERMOSO (Coslada)', 0),
(10, 1, 'C.P, NUESTRA SEÑORA DE LA VICTORIA (Villarejo de Salvanés)', 0),
(11, 1, 'C.P, ÁNGEL CASTRO (Valdeavero)', 0),
(12, 1, 'C.P, SANTA ELENA (Villarejo de Salvanés)', 0),
(13, 1, 'C.P, Nº 30 LA GARENA (Alcalá de Henares)', 0),
(14, 1, 'C.P, NUESTRA SEÑORA DEL CASTILLO (Perales de Tajuña)', 0),
(15, 1, 'C.P, CARLOS SAINZ DE LOS TERREROS (Madrid)', 0),
(16, 1, 'C.P. SANTA MARIA (Madrid)', 0),
(17, 1, 'C.P. REINA VICTORIA (Madrid)', 0),
(18, 1, 'C.P. ZULEMA (Alcalá de Henares)', 0),
(19, 1, 'C.P. PEÑAS ALBAS (Villalbilla)', 0),
(20, 1, 'C.P. MIGUEL PUERTA (Aranjuez)', 0),
(21, 1, 'C.P. VICENTE ALEIXANDRE (Aranjuez)', 0),
(22, 1, 'C.P. PABLO NERUDA (Coslada)', 0),
(23, 1, 'C.P. CRISTÓBAL COLÓN (Alcalá de Henares)', 0),
(24, 2, 'COLEGIO SAGRADA FAMILIA (Aranjuez)', 0),
(25, 2, 'COLEGIO SANTA MARIA DE LA HISPANIDAD (Madrid)', 0),
(26, 2, 'COLEGIO LUIS FEITO (Madrid)', 0),
(27, 2, 'COLEGIO NUESTRA SEÑORA DE LOS REMEDIOS (Madrid)', 0),
(28, 2, 'COLEGIO SAN PASCUAL (Aranjuez)', 0),
(29, 2, 'COLEGIO CAMINO REAL (Torrejón de Ardoz)', 0),
(30, 2, 'COLEGIO SAGRADO CORAZÓN (Madrid)', 0),
(31, 2, 'COMEDOR ÑAM ÑAM (Madrid)', 0),
(32, 2, 'COLEGIO SALESIANOS LOYOLA (Aranjuez)', 0),
(33, 2, 'COLEGIO VIRGEN DEL PUERTO (Aranjuez)', 0),
(34, 2, 'COLEGIO LICEO CONSUL (Madrid)', 0),
(35, 2, 'COLEGIO LOPE DE VEGA (Alcalá de Henares)', 0),
(36, 2, 'COLEGIO NUESTRA SEÑORA DE LAS NIEVES (Madrid)', 0),
(37, 2, 'COLEGIO ADDIS (Madrid)', 0),
(38, 2, 'COLEGIO AFUERA (Madrid)', 0),
(39, 2, 'COLEGIO CASTILLA (Madrid)', 0),
(40, 2, 'COLEGIO SAN JOSÉ LUCERO (Madrid)', 0),
(41, 2, 'COLEGIO SANTA ELlZABETH (Madrid)', 0),
(42, 2, 'COLEGIO SAN EULOGIO (Madrid)', 0),
(43, 3, 'IRIS (Madrid)', 0),
(44, 3, 'BICHEJOS (Mejorada del Campo)', 0),
(45, 3, 'BICHINES (San Fernando de Henares)', 0),
(46, 3, 'EL BOSQUE ENCANTADO DE SESEÑA (Seseña)', 0),
(47, 3, 'CHIQUIPATIO (Aranjuez)', 0),
(48, 3, 'CINCO ESTRELLAS (Madrid)', 0),
(49, 3, 'CUQUITOS (Madrid)', 0),
(50, 3, 'EL PAIS DE LA FANTASíA (Aranjuez)', 0),
(51, 3, 'EL JARDIN DE LOS GENIOS (Boadilla)', 0),
(52, 3, 'PIPO´S (Madrid)', 0),
(53, 3, 'GRUMETE (Madrid)', 0),
(54, 3, 'PIQUIÑUELOS (Alcala de Henares)', 0),
(55, 3, 'ARCO IRIS (Madrid)', 0),
(56, 4, 'VENECIA (Alcala de Henares)', 0),
(57, 4, 'GREGORIO SANCHEZ (Villaconejos)', 0),
(58, 4, 'ASOCIACION APSA (Arganda del Rey)', 0),
(59, 4, 'MARIA ZULUETA (Rivas Vaciamadrid)', 0),
(60, 5, 'CARTERPILLAR (Guadalajara)', 0),
(61, 5, 'ESAB IBERICA (San Fernando de Henares)', 0),
(62, 5, 'INVERSIONES ROIGES (Torres de la Alameda)', 0),
(63, 5, 'ASSEMSA (Madrid)', 0),
(64, 5, 'EFI (Torrejón de Ardoz)', 0),
(65, 5, 'PIAZZA COSTA DEL SOL, SL (Illescas)', 0),
(66, 5, 'SANITARIA Y SOCIAL (Villanueva del Pardillo)', 0),
(67, 5, 'AUTOCARES ALONSO, SL (Alcala de Henares)', 0),
(68, 5, 'ABELlA, SL (Madrid)', 0),
(69, 5, 'VICMAR, SL (Madrid)', 0),
(70, 6, 'TORRES DE LA ALAMEDA', 0),
(71, 6, 'ANCHUELO', 0),
(72, 6, 'RECAS', 0),
(73, 6, 'YEPES', 0),
(75, 3, 'ESCUELA INFANTIL LAS SETITAS', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ContenidoClientesCategoria`
--

CREATE TABLE IF NOT EXISTS `ContenidoClientesCategoria` (
  `id` int(11) NOT NULL auto_increment,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `ContenidoClientesCategoria`
--

INSERT INTO `ContenidoClientesCategoria` (`id`, `nombre`) VALUES
(1, 'Colegios Publicos'),
(2, 'Colegios Privados'),
(3, 'Escuelas Infantiles'),
(4, 'Resiencias y Centros de Dia'),
(5, 'Empresas'),
(6, 'Ayuntamientos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pdf`
--

CREATE TABLE IF NOT EXISTS `pdf` (
  `id` int(11) NOT NULL auto_increment,
  `id_cliente` int(11) NOT NULL,
  `pdf` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=153 ;

--
-- Volcado de datos para la tabla `pdf`
--

INSERT INTO `pdf` (`id`, `id_cliente`, `pdf`) VALUES
(9, 2, 'recetas.pdf'),
(10, 36, 'MENU EMPRESA.pdf'),
(11, 3, 'SAN EULOGIO ENERO 2016.pdf'),
(12, 4, 'SAGRADO CORAZÃ“N ENERO 2016.pdf'),
(15, 7, 'AFUERA ENERO 2016.pdf'),
(16, 8, 'AFUERA ENERO 2016.pdf'),
(17, 9, 'STA ELIZABETH.pdf'),
(18, 10, 'CASTILLA.pdf'),
(19, 11, 'SANTA MARIA.pdf'),
(20, 12, 'PIPOS.pdf'),
(21, 13, 'LUIS FEITO.pdf'),
(26, 18, 'VICENTE ALEIXANDRE.pdf'),
(28, 20, 'LOYOLA ENERO 16.pdf'),
(31, 23, 'GREGORIO CANELLA ENERO 2016.pdf'),
(32, 24, 'ANCHUELO ENERO 2016.pdf'),
(34, 26, 'CRISTOBAL COLON ENERO 16.pdf'),
(36, 28, 'NURSERY SCHOOL ENERO 16.pdf'),
(39, 31, 'ANGEL CASTRO ENERO 16.pdf'),
(42, 34, 'VITALIA ENERO 2016.pdf'),
(46, 39, 'PERALES ENERO 2016.pdf'),
(47, 40, 'TIELMES ENERO 16.pdf'),
(50, 44, 'NUESTRA SRA. DE LA VICTORIA ENERO 16.pdf'),
(51, 45, 'SANTA ELENA ENERO 2016.pdf'),
(52, 46, 'ESTREMERA ENERO 2016.pdf'),
(53, 47, 'ORUSCO ENERO 2016.pdf'),
(55, 49, 'CINCO ESTRELLAS.pptx'),
(58, 52, 'JUAN RAMON JIMENEZ ENERO 16.pdf'),
(60, 55, 'BICHEJOS.pdf'),
(62, 57, 'NTRA. SRA. DE LOS REMEDIOS ENERO 2016.pdf'),
(65, 60, 'SAN JOSÃ‰ LUCERO.pdf'),
(66, 61, 'ADDIS ENERO 2016.pdf'),
(67, 63, 'PEÃ‘AS ALBAS.pdf'),
(68, 64, 'LA GARENA.pptx'),
(71, 67, 'CARLOS SAINZ.pptx'),
(72, 68, 'HISPANIDAD.pdf'),
(73, 70, 'PABLO NERUDA enero 2016.pdf'),
(74, 71, 'ALFREDO LANDA enero 2016.pdf'),
(75, 54, 'MENU EMPRESA.pdf'),
(76, 62, 'LOPE DE VEGA ENERO  2016.pdf'),
(77, 66, 'LICEO CONSUL ENERO 2016.pdf'),
(78, 72, 'REINA VICTORIA.pptx'),
(81, 69, 'NTRA. SRA. DE LAS NIEVES ENERO 2016.pdf'),
(108, 59, 'BOSQUE ENCANTADO.pdf'),
(110, 56, 'BICHINES ENERO 2016.pdf'),
(111, 53, 'JARDIN DE LOS GENIOS ENERO 16.pdf'),
(113, 50, 'IRIS ENERO 2016.pdf'),
(114, 43, 'COLORINES ENERO 16.pdf'),
(115, 38, 'APSA C.E.E.pdf'),
(116, 37, 'CAMINO REAL ENERO 16.pdf'),
(117, 35, 'LAS SETITAS ENERO 2016.pdf'),
(118, 33, 'TINTIN ENERO 2016.pdf'),
(119, 32, 'MILU ENERO 2016.pdf'),
(120, 30, 'COLORIN COLORADO.pdf'),
(121, 29, 'SAN JUAN BOSCO 2.pdf'),
(123, 25, 'VENECIA.pdf'),
(124, 17, 'SAGRADA FAMILIA ENERO 16.pdf'),
(125, 16, 'PAIS DE LA FANTASIA ENERO 2016.pdf'),
(126, 15, 'CHIQUIPATIO ENERO 2016.pdf'),
(127, 14, 'VIRGEN DEL PUERTO ENERO 16.pdf'),
(130, 6, 'CUQUITOS.pdf'),
(131, 73, 'FRUTIS ENERO 2016.pdf'),
(132, 74, 'MIGUEL HERNANDEZ ENERO 2016.pdf'),
(133, 75, 'daoiz y velarde enero 2016 correcto.pdf'),
(134, 76, 'SHAKESPEARE ENERO 2016.pdf'),
(135, 77, 'LUIS BELLO.pptx'),
(136, 78, 'PRAVIA ENERO 16.pdf'),
(137, 79, 'ANTONIO NEBRIJA ENERO 16.pdf'),
(143, 86, 'YAKI ENERO 2016.pdf'),
(144, 93, 'SANCHINARRO ENERO 2016.pdf'),
(145, 42, 'BELMONTE  ENERO 2016.pdf'),
(146, 81, 'JUAN DE GOYENECHE ENERO 2016.pdf'),
(147, 80, 'LOS SANTOS ENERO 2016.pdf'),
(148, 85, 'TARABILLA ENERO 2016.pdf'),
(149, 82, 'FUENTIDUEÃ‘A ENERO 2016.pdf'),
(150, 88, 'COLEGIO ALBA ENERO 2016.pdf'),
(151, 94, 'LITTLE NURSERY ENERO 2016.pdf'),
(152, 89, 'BAMBIS ENERO 2016.pdf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pdf_calendar`
--

CREATE TABLE IF NOT EXISTS `pdf_calendar` (
  `id` int(11) NOT NULL auto_increment,
  `id_cliente` int(11) NOT NULL,
  `pdf` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=64 ;

--
-- Volcado de datos para la tabla `pdf_calendar`
--

INSERT INTO `pdf_calendar` (`id`, `id_cliente`, `pdf`) VALUES
(4, 2, ''),
(5, 3, 'GILBERT SAN EULOGIO.pdf'),
(6, 49, 'CINCO ESTRELLAS.pdf'),
(7, 71, 'GILBERT ALFREDO LANDA.pdf'),
(8, 27, 'C. COLON.pdf'),
(9, 26, 'GILBERT CRISTOBAL COLON.pdf'),
(10, 30, 'COLORIN COLORADO.pdf'),
(11, 10, 'GILBERT CASTILLA.pdf'),
(12, 11, 'GILBERT STA MARIA.pdf'),
(13, 7, 'AFUERA.pdf'),
(14, 8, 'AFUERA.pdf'),
(15, 31, 'ANGEL CASTRO.pdf'),
(16, 62, 'LOPE VEGA.pdf'),
(17, 46, 'C.RUIZ-TIELMES.pdf'),
(18, 39, 'CASTILLO.pdf'),
(19, 20, 'LOYOLA.pdf'),
(20, 28, 'NURSERY.pdf'),
(21, 64, 'GILBERT LA GARENA.pdf'),
(22, 51, 'GRUMETE.pdf'),
(23, 66, 'LICEO CONSUL.pdf'),
(24, 33, 'TIN TIN.pdf'),
(25, 13, 'GILBERT LUIS FEITO.pdf'),
(26, 29, 'GILBERT SAN JUAN BOSCO.pdf'),
(27, 42, 'belmonte.pdf'),
(28, 40, 'estremera.pdf'),
(29, 15, 'chiquipatio.pdf'),
(30, 17, 'sagrada familiao.pdf'),
(31, 32, 'milou.pdf'),
(32, 70, 'pablo neruda.pdf'),
(33, 16, 'pais de la fantasia.pdf'),
(34, 18, 'viente aleixandre.pdf'),
(35, 48, 'villar del olmo.pdf'),
(36, 63, 'GILBERT PENAS ALBAS.pdf'),
(38, 21, 'cai fuentearriba.pdf'),
(39, 57, 'NTRA.SRA REMEDIOS.pdf'),
(40, 35, 'las setitas.pdf'),
(41, 72, 'GILBERT REINA VICTORIA.pdf'),
(42, 47, 'orusco.pdf'),
(43, 23, 'gregorio canella.pdf'),
(44, 14, 'virgen del puerto.pdf'),
(45, 19, 'miguel puerta.pdf'),
(46, 45, 'SANTA ELENA.pdf'),
(47, 44, 'NTRA.SRA. VICTORIA.pdf'),
(48, 12, 'PIPOS.pdf'),
(49, 9, 'SANTA ELIZABETH.pdf'),
(50, 53, 'JARDIN DE LOS GENIOS.pdf'),
(51, 60, 'GILBERT SAN JOSE LUCERO.pdf'),
(52, 4, 'GILBERT  SAGRADO CORAZON.pdf'),
(53, 50, 'IRIS.pdf'),
(54, 52, 'GILBERT JUAN RAMON JIMENEZ.pdf'),
(55, 56, 'BICHINES.pdf'),
(56, 65, 'ZULEMA.pdf'),
(57, 38, 'APSA.pdf'),
(58, 67, 'GILBERT CARLOS SAINZ TERREROS.pdf'),
(59, 61, 'GILBERT ADDIS.pdf'),
(60, 76, 'GILBERT WILLIAN SHAKESPEARE.pdf'),
(61, 68, 'GILBERT  HISPANIDAD.pdf'),
(62, 75, 'GILBERT DAOIZ Y VELARDE.pdf'),
(63, 74, 'GILBERT MIGUEL HERNANDEZ.pdf');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(2) NOT NULL auto_increment,
  `usuario` varchar(50) character set utf8 NOT NULL,
  `pass` varchar(50) character set utf8 NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `usuario`, `pass`) VALUES
(1, 'admin', 'sercaib2012');

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `view_pdf`
--
CREATE TABLE IF NOT EXISTS `view_pdf` (
`id` int(11)
,`nombre` varchar(100)
,`pdf` varchar(100)
);
-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `view_pdf_calendar`
--
CREATE TABLE IF NOT EXISTS `view_pdf_calendar` (
`id` int(11)
,`nombre` varchar(100)
,`pdf` varchar(100)
);
-- --------------------------------------------------------

--
-- Estructura para la vista `view_pdf`
--
DROP TABLE IF EXISTS `view_pdf`;

CREATE ALGORITHM=UNDEFINED DEFINER=`sercaib_website`@`localhost` SQL SECURITY DEFINER VIEW `view_pdf` AS select `p`.`id` AS `id`,`c`.`nombre` AS `nombre`,`p`.`pdf` AS `pdf` from (`pdf` `p` join `clientes` `c` on((`p`.`id_cliente` = `c`.`id`)));

-- --------------------------------------------------------

--
-- Estructura para la vista `view_pdf_calendar`
--
DROP TABLE IF EXISTS `view_pdf_calendar`;

CREATE ALGORITHM=UNDEFINED DEFINER=`sercaib_website`@`localhost` SQL SECURITY DEFINER VIEW `view_pdf_calendar` AS select `p`.`id` AS `id`,`c`.`nombre` AS `nombre`,`p`.`pdf` AS `pdf` from (`pdf_calendar` `p` join `clientes` `c` on((`p`.`id_cliente` = `c`.`id`)));

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
