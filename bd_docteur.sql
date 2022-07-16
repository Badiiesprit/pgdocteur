-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 16 juil. 2022 à 13:45
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.2.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bd_mymedecin`
--

-- --------------------------------------------------------

--
-- Structure de la table `creneaux`
--

CREATE TABLE `creneaux` (
  `id_cre` int(2) NOT NULL,
  `crenau` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `creneaux`
--

INSERT INTO `creneaux` (`id_cre`, `crenau`) VALUES
(1, '8:00'),
(2, '8:30'),
(3, '9:00'),
(4, '9:30'),
(5, '10:00'),
(6, '10:30'),
(7, '11:00'),
(8, '11:30'),
(9, '12:00'),
(10, '12:30'),
(11, '14:00'),
(12, '14:30'),
(13, '15:00'),
(14, '15:30'),
(15, '16:00'),
(16, '16:30'),
(17, '17:00'),
(18, '17:30');

-- --------------------------------------------------------

--
-- Structure de la table `events`
--

CREATE TABLE `events` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `description` text NOT NULL,
  `date_deb` date NOT NULL,
  `date_fin` date NOT NULL,
  `nb_participant` int(11) NOT NULL,
  `id_specialite` int(11) NOT NULL,
  `id_gouvernerat` int(11) NOT NULL,
  `type_participant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `events`
--

INSERT INTO `events` (`id`, `name`, `description`, `date_deb`, `date_fin`, `nb_participant`, `id_specialite`, `id_gouvernerat`, `type_participant`) VALUES
(2, 'aaaaaaaaaa', 'aaaaaaaaaa', '2022-07-05', '2022-07-13', 12, 1, 1, 1),
(3, 'aaaaaaaaaa', 'aaaaaaaaaa', '2022-07-05', '2022-07-13', 12, 1, 1, 1),
(4, 'aaaaaaaaaa', 'aaaaaaaaaa', '2022-07-05', '2022-07-13', 12, 1, 1, 1),
(8, 'aaaaaaaaaa', 'aaaaaaaaaa', '2022-07-05', '2022-07-13', 12, 1, 1, 1),
(9, 'aaaaaaaaaa', 'aaaaaaaaaa', '2022-07-05', '2022-07-13', 12, 1, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `gouvernorats`
--

CREATE TABLE `gouvernorats` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `gouvernorats`
--

INSERT INTO `gouvernorats` (`id`, `name`) VALUES
(1, 'Ariana'),
(2, 'Beja'),
(3, 'Ben arous'),
(4, 'Bizerte'),
(5, 'Ben arous'),
(6, 'Gabes'),
(7, 'Gafsa'),
(8, 'Jendouba'),
(9, 'Kairouan'),
(10, 'Kasserine'),
(11, 'Kebili'),
(12, 'Le Kef'),
(13, 'Mahdia'),
(14, 'Mannouba'),
(15, 'Medenine'),
(16, 'Monastir'),
(17, 'Nabeul'),
(18, 'Sfax'),
(19, 'Sidi bouzid'),
(20, 'Siliana'),
(21, 'Sousse'),
(22, 'Tataouine'),
(23, 'Tozeur'),
(24, 'Tunis'),
(25, 'Zaghouan');

-- --------------------------------------------------------

--
-- Structure de la table `inscription_events`
--

CREATE TABLE `inscription_events` (
  `id` int(11) NOT NULL,
  `id_events` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `medecines`
--

CREATE TABLE `medecines` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `adresse_cabinet` text NOT NULL,
  `phone_fixe_cabinet` varchar(16) NOT NULL,
  `phone_fixe2_cabinet` varchar(16) NOT NULL,
  `id_specialite` int(11) NOT NULL,
  `id_gouvernorat` int(11) NOT NULL,
  `gallery_cabinet` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `medecines`
--

INSERT INTO `medecines` (`id`, `id_user`, `adresse_cabinet`, `phone_fixe_cabinet`, `phone_fixe2_cabinet`, `id_specialite`, `id_gouvernorat`, `gallery_cabinet`) VALUES
(6, 58, 'test101', '70222111', '71111222', 8, 17, '-'),
(8, 70, 'aaaaaaaaaaaaaaaaaaaa', '33333333', '33333333', 4, 4, '-'),
(9, 71, 'aaaaaaaaaaaaaaa', '33333333', '', 3, 4, '-');

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `id_user`) VALUES
(3, 42),
(24, 76),
(25, 77);

-- --------------------------------------------------------

--
-- Structure de la table `pharmacie`
--

CREATE TABLE `pharmacie` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `adresse_pharmacie` text NOT NULL,
  `phone_fixe_pharmacie` varchar(16) NOT NULL,
  `phone_fixe2_pharmacie` varchar(16) NOT NULL,
  `name_pharmacie` varchar(255) NOT NULL,
  `gallery_pharmacie` text NOT NULL,
  `id_gouvernorat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `pharmacie`
--

INSERT INTO `pharmacie` (`id`, `id_user`, `adresse_pharmacie`, `phone_fixe_pharmacie`, `phone_fixe2_pharmacie`, `name_pharmacie`, `gallery_pharmacie`, `id_gouvernorat`) VALUES
(2, 59, 'test', '70258741', '70258741', 'Pharmacien101', '-', 17),
(7, 73, 'gouvernorat', '33333333', '33333333', 'gouvernorat', '-', 1);

-- --------------------------------------------------------

--
-- Structure de la table `planification`
--

CREATE TABLE `planification` (
  `id_plan` int(11) NOT NULL,
  `id_cre` int(11) NOT NULL,
  `id_medecin` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `date_plan` date NOT NULL,
  `Name` varchar(20) DEFAULT NULL,
  `commentaire` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `planification`
--

INSERT INTO `planification` (`id_plan`, `id_cre`, `id_medecin`, `id_patient`, `date_plan`, `Name`, `commentaire`) VALUES
(1, 13, 58, 0, '2022-06-30', 'Amine CHemsi', 'Consult'),
(2, 8, 58, 0, '2022-06-24', 'Amine CHemsi', 'Consult'),
(3, 17, 58, 0, '2022-06-28', 'Amine CHemsi', 'Consult'),
(14, 7, 58, 0, '2022-06-29', 'pppppppp', 'ooooooo'),
(15, 4, 58, 0, '2022-07-07', 'Test', 'test_test'),
(16, 8, 58, 0, '2022-07-19', 'Test123', 'test_test1236'),
(17, 10, 58, 0, '2022-06-27', 'Foulen ben Foulen', 'RS'),
(18, 18, 58, 0, '2022-06-27', 'yyyyyyyyyy', 'aaaaaaa'),
(19, 1, 60, 0, '2022-06-26', 'PTOIYGFCFWX', 'azertyui'),
(20, 5, 60, 57, '2022-07-08', 'uytr', 'uytr'),
(21, 9, 60, 57, '2022-07-01', 'Patient  1', 'Merci pour votre attention'),
(22, 1, 60, 57, '2022-07-08', 'Patient  1', 'jioppppeppe'),
(23, 2, 60, 57, '2022-07-08', 'Patient 1', 'oiuhygfds'),
(24, 18, 60, 57, '2022-07-08', 'zertyuio', 'xcvbn'),
(25, 17, 60, 57, '2022-07-08', 'qsdfg', 'poiuyt'),
(26, 9, 60, 57, '2022-07-20', 'Patient  1', 'dfghjkl'),
(27, 1, 60, 57, '2022-07-04', 'Patient  1', 'azertyuio'),
(28, 18, 60, 57, '2022-07-04', 'Patient 1', '45'),
(29, 11, 60, 57, '2022-07-04', 'ertyuio', 'sdfghjk'),
(30, 1, 58, 57, '2022-07-10', 'Patient  1', 'ghj'),
(31, 2, 58, 57, '2022-07-22', 'Patient  101', 'zertyu'),
(32, 1, 58, 57, '2022-08-01', 'zertyui', 'zerty'),
(33, 5, 71, 57, '2022-07-29', 'Patient  101', 'ffgfgfgfgf'),
(34, 3, 71, 57, '2022-07-22', 'Patient  101', ''),
(35, 3, 58, 57, '2022-07-22', 'wwwx', 'sqsqqs'),
(36, 4, 58, 57, '2022-07-21', 'cxc', 'wswxwxwx');

-- --------------------------------------------------------

--
-- Structure de la table `specialites`
--

CREATE TABLE `specialites` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `specialites`
--

INSERT INTO `specialites` (`id`, `name`) VALUES
(1, 'Cardiologue'),
(2, 'Chirurgien Esthétique'),
(3, 'Chirurgien Orthopédiste Traumatologue'),
(4, 'Chirurgien Esthétique'),
(5, 'Dentiste'),
(6, 'Dermatologue'),
(7, 'Endocrinologue Diabétologue'),
(8, 'Gastro-entérologue'),
(9, 'Gynécologue Obstétricien'),
(10, 'Interniste'),
(11, 'Néphrologue'),
(12, 'Neurologue'),
(13, 'Nutritionniste'),
(14, 'Ophtalmologiste'),
(15, 'Oto-Rhino-Laryngologiste (ORL)'),
(16, 'Pédiatre'),
(17, 'Pneumologue'),
(18, 'Psychiatre'),
(19, 'Psychothérapeute'),
(20, 'Radiologue'),
(21, 'Rhumatologue'),
(22, 'Sexologue'),
(23, 'Urologue');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `photo_profil` varchar(255) NOT NULL,
  `adresse` text NOT NULL,
  `phone` varchar(16) NOT NULL,
  `role` bigint(1) NOT NULL,
  `password` text NOT NULL,
  `login` varchar(255) NOT NULL,
  `statu` int(11) NOT NULL DEFAULT 0,
  `code` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `photo_profil`, `adresse`, `phone`, `role`, `password`, `login`, `statu`, `code`) VALUES
(42, 'admin', 'admin', '-', 'admin', 'admin', 10, 'admin', 'admin', 1, NULL),
(57, 'Patient', ' 101', 'Patient-57.jpg', 'test 101sqsqsqssqssqqs', '44444444', 1, '123456', 'patient', 1, NULL),
(58, 'Medecine', '1', 'Patient-58.jpg', 'test 102', '72569852', 2, '123456', 'medecine', 1, NULL),
(59, 'Pharmacien', 'test', 'Pharmacien-59.jpg', 'PharmacienPharmacien', '70258741', 3, '123456', 'pharmacien@gmail.com', 1, NULL),
(69, 'aaaa', 'aaaa', '-', 'aaaaaaaaaaaaa', '11111111', 1, 'aaaa', 'aaaa', 1, NULL),
(70, 'aaaa', 'aaaa', '-', 'aaaaaaaaaaaaaaaaaa', '33333333', 2, 'aaaa', 'aaaaa', 1, NULL),
(71, 'aaaaaaaaaaaaaa', 'aaaaaaaaaaaaaa', '-', 'aaaaaaaaaaaaa', '33333333', 2, 'azaz', 'azaz', 0, NULL),
(73, 'gouvernorat', 'gouvernorat', '-', 'gouvernoratgouvernorat', '66666666', 3, 'gouvernorat', 'gouvernorat', 0, NULL),
(74, 'badi', 'andelkhakhak', '-', 'nabeul', '92458669', 1, '123585', 'badinj', 0, NULL),
(75, 'badi', 'andelkhakhak', '-', 'nabeul', '92458669', 1, '123585', 'badinj', 0, NULL),
(76, 'badi', 'andelkhakhak', '-', 'nabeul', '92458669', 1, '123585', 'badinjupdate', 0, NULL),
(77, 'badi', 'andelkhakhak', '-', 'nabeul', '92458669', 1, '123585', 'badinj', 1, NULL),
(78, 'aaaa@bbbb.ccc', 'aaaa@bbbb.ccc', 'Patient-78.jpg', 'aaaa@bbbb.ccc', '25555555', 1, '0000', 'aaaa@bbbb.ccc', 1, '479778'),
(79, 'test email', 'badi', '-', 'badi test emil', '92452336', 1, '159753', 'badi9880@gmail.com', 1, '665379');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `creneaux`
--
ALTER TABLE `creneaux`
  ADD PRIMARY KEY (`id_cre`);

--
-- Index pour la table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `gouvernorats`
--
ALTER TABLE `gouvernorats`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `inscription_events`
--
ALTER TABLE `inscription_events`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_inscription_events_user` (`id_user`),
  ADD KEY `FK_inscription_events` (`id_events`);

--
-- Index pour la table `medecines`
--
ALTER TABLE `medecines`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_specialite` (`id_specialite`),
  ADD KEY `id_gouvernorat` (`id_gouvernorat`),
  ADD KEY `FK_medecines` (`id_user`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_patient` (`id_user`);

--
-- Index pour la table `pharmacie`
--
ALTER TABLE `pharmacie`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_pharmacie` (`id_user`),
  ADD KEY `FK_pharmacie_gouvernorat` (`id_gouvernorat`);

--
-- Index pour la table `planification`
--
ALTER TABLE `planification`
  ADD PRIMARY KEY (`id_plan`),
  ADD KEY `id_medecin` (`id_medecin`),
  ADD KEY `id_cre` (`id_cre`);

--
-- Index pour la table `specialites`
--
ALTER TABLE `specialites`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `creneaux`
--
ALTER TABLE `creneaux`
  MODIFY `id_cre` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `events`
--
ALTER TABLE `events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `gouvernorats`
--
ALTER TABLE `gouvernorats`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `inscription_events`
--
ALTER TABLE `inscription_events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `medecines`
--
ALTER TABLE `medecines`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT pour la table `pharmacie`
--
ALTER TABLE `pharmacie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `planification`
--
ALTER TABLE `planification`
  MODIFY `id_plan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT pour la table `specialites`
--
ALTER TABLE `specialites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `inscription_events`
--
ALTER TABLE `inscription_events`
  ADD CONSTRAINT `FK_inscription_events` FOREIGN KEY (`id_events`) REFERENCES `events` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_inscription_events_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `medecines`
--
ALTER TABLE `medecines`
  ADD CONSTRAINT `FK_medecines` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_medecines_gouvernorat` FOREIGN KEY (`id_gouvernorat`) REFERENCES `gouvernorats` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_medecines_specialite` FOREIGN KEY (`id_specialite`) REFERENCES `specialites` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `FK_patient` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `pharmacie`
--
ALTER TABLE `pharmacie`
  ADD CONSTRAINT `FK_pharmacie` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_pharmacie_gouvernorat` FOREIGN KEY (`id_gouvernorat`) REFERENCES `gouvernorats` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
