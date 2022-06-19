-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 20 juin 2022 à 00:05
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
(6, 58, 'test1225', '70222111++', '71111222--', 5, 5, '-');

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
(3, 42);

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
(2, 59, 'test102', '70258741++', '70258741--', 'Pharmacien10133', '-', 25);

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
  `statu` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `photo_profil`, `adresse`, `phone`, `role`, `password`, `login`, `statu`) VALUES
(42, 'admin', 'admin', '-', 'admin', 'admin', 10, 'admin', 'admin', 1),
(57, 'Patient', ' 1', '-', 'test 101', '72458968', 1, '0000', 'patient', 1),
(58, 'Medecine', '1', '-', 'test 102', '72569852', 2, '0000', 'medecine', 1),
(59, 'Pharmacien', '1', '-', 'test 1035', '70258741', 3, '0000', 'pharmacien', 1);

--
-- Index pour les tables déchargées
--

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
-- AUTO_INCREMENT pour la table `events`
--
ALTER TABLE `events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pour la table `pharmacie`
--
ALTER TABLE `pharmacie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `specialites`
--
ALTER TABLE `specialites`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

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
