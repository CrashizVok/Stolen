-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2026. Feb 23. 22:48
-- Kiszolgáló verziója: 10.4.32-MariaDB
-- PHP verzió: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `stolen`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `assets`
--

CREATE TABLE `assets` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(10) UNSIGNED DEFAULT 0,
  `is_stolen` tinyint(1) NOT NULL DEFAULT 0,
  `owner_id` int(10) UNSIGNED DEFAULT NULL,
  `thief_id` int(10) UNSIGNED DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `assets`
--

INSERT INTO `assets` (`id`, `name`, `price`, `is_stolen`, `owner_id`, `thief_id`, `image`) VALUES
(1, 'XIAOMI Electric Scooter 4 Lite Gen2 EU', 122999, 1, 1, 2, '/images/scooter.webp'),
(2, 'magánnyugdíj pénztári egyenleg', 6000000, 1, 3, 4, '/images/nyugdij.png'),
(3, 'Kecskeméti Egyetem vagyona', NULL, 1, 6, 5, '/images/kecskemeti-egyetem.jpg'),
(4, 'Wordpress szüzesség', NULL, 1, 1, NULL, '/images/wordpress.avif'),
(5, 'Intercity WC kefe', 300, 0, 2, NULL, '/images/wc-kefe.jpg');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `people`
--

CREATE TABLE `people` (
  `id` int(10) UNSIGNED NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `category` varchar(255) DEFAULT 'C',
  `image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `people`
--

INSERT INTO `people` (`id`, `first_name`, `last_name`, `birthday`, `category`, `image`) VALUES
(1, 'Ronald', 'Vánczó', '2006-02-12', NULL, '/images/vanczo-ronald.jpg'),
(2, 'Odüsszeusz', 'Kolompár', '2008-12-01', 'C', '/images/kolompar-odusszeusz.jpg'),
(3, 'József', 'Víz', '2007-06-07', NULL, '/images/viz-jozsef.jpg'),
(4, 'Gabriella', 'Selmeci', NULL, NULL, '/images/selmeci-gabriella.jpg'),
(5, 'Klaudia', 'Sz.-né P.', NULL, NULL, '/images/sznepklaudia.jpg'),
(6, 'Béni', 'Neumannos', '2008-01-06', NULL, '/images/neumannos-beni.jpg');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `assets`
--
ALTER TABLE `assets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `assets_owner_idx` (`owner_id`),
  ADD KEY `assets_thief_idx` (`thief_id`);

--
-- A tábla indexei `people`
--
ALTER TABLE `people`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `assets`
--
ALTER TABLE `assets`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `people`
--
ALTER TABLE `people`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `assets`
--
ALTER TABLE `assets`
  ADD CONSTRAINT `assets_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `people` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION,
  ADD CONSTRAINT `assets_ibfk_2` FOREIGN KEY (`thief_id`) REFERENCES `people` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
