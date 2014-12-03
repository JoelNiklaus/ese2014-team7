-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 03. Dez 2014 um 13:17
-- Server Version: 5.6.20
-- PHP-Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `ESE`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Ad`
--

CREATE TABLE IF NOT EXISTS `Ad` (
`id` bigint(20) NOT NULL,
  `addCost` bigint(20) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `dateIn` varchar(255) DEFAULT NULL,
  `dateOut` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `distanceToPublicTransport` bigint(20) DEFAULT NULL,
  `distanceToShopping` bigint(20) DEFAULT NULL,
  `houseNr` bigint(20) DEFAULT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `lng` varchar(255) DEFAULT NULL,
  `placerId` bigint(20) DEFAULT NULL,
  `postingDateFormatted` varchar(255) DEFAULT NULL,
  `rent` bigint(20) DEFAULT NULL,
  `roomSize` bigint(20) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `us` varchar(255) DEFAULT NULL,
  `you` varchar(255) DEFAULT NULL,
  `zip` bigint(20) DEFAULT NULL,
  `ad_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Daten für Tabelle `Ad`
--

INSERT INTO `Ad` (`id`, `addCost`, `city`, `dateIn`, `dateOut`, `description`, `distanceToPublicTransport`, `distanceToShopping`, `houseNr`, `lat`, `lng`, `placerId`, `postingDateFormatted`, `rent`, `roomSize`, `street`, `timestamp`, `title`, `us`, `you`, `zip`, `ad_id`) VALUES
(10, 10, 'Eins', '03.12.2014', '03.01.2015', 'Pets are allowed. Smoke on the balcony. Parking spots additionally rentable.', 100, 100, 1, '32.6015262', '-93.8427419', 105, '03/12/2014', 100, 10, 'One', '2014-12-03 12:46:07', 'Alphas House', 'One female studying philosophy and one guy working as an artist', 'Open for stuff and games', 1, NULL),
(11, 12, 'Bern', '03.12.2014', '22.04.2015', 'Yes Pets are welcome', 123, 112, 23, '29.553501', '-98.3777729', 105, '03/12/2014', 133, 12, 'Musterstrasse', '2014-12-03 12:48:00', 'Beautiful House with Garden', 'We are very cool', 'Psyched about uno.', 3011, NULL),
(12, 123, 'Bern', '03.12.2014', '04.03.2015', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata', 123, 243, 23, '', '', 106, '03/12/2014', 465, 24, 'Jungerngasse', '2014-12-03 12:51:04', 'Home in Jungerngasse', 'Open and love games.', 'Funny', 3011, NULL),
(13, 234, 'Bern', '03.12.2014', '01.04.2015', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata', 345, 123, 12, '46.760565', '7.6286002', 106, '03/12/2014', 567, 12, 'Marktgasse', '2014-12-03 12:52:59', 'Live in Bern', 'Interested to get to know you.', 'Ready to cook, haute cuisine.', 3011, NULL),
(14, 123, 'Thun', '03.12.2014', '04.03.2015', 'Very big room, we own a cat, bla bla bla', 456, 300, 56, '46.7622152', '7.5932054', 106, '03/12/2014', 789, 34, 'Thunstrasse', '2014-12-03 12:54:49', 'Wanna live in Thun', 'We love cats', 'You should love cats, too.', 3008, NULL),
(15, 150, 'Ittigen', '03.12.2014', '27.05.2015', 'The room is big, enough space for a spacecraft.', 240, 345, 42, '46.9810095', '7.4798868', 108, '03/12/2014', 570, 15, 'Grauholzstrasse', '2014-12-03 13:01:21', 'Awesome Room near Bern', 'They are all cool, we like to watch movies and stuff', 'You should love movies and cinema', 3063, NULL),
(16, 50, 'Ittigen', '03.12.2014', '01.04.2015', 'A very big room, enough space to fit in a spacestation', 560, 1000, 23, '46.9788405', '7.47651864388504', 108, '03/12/2014', 980, 25, 'Jurastrasse', '2014-12-03 13:03:17', 'Live in Ittigen', 'Very activ, running a lot', 'Sportsbuddy', 3063, NULL),
(17, 230, 'Bern', '03.12.2014', '18.03.2015', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata', 560, 100, 15, '46.81928575', '7.58289707446658', 108, '03/12/2014', 790, 12, 'Bahnhofstrasse', '2014-12-03 13:04:45', 'Near Central station', 'Open and love games', 'Open for stuff and games', 3008, NULL),
(18, 23, 'Zollikofen', '03.12.2014', '31.12.2014', 'Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata', 500, 250, 21, '46.9945073', '7.4598278', 107, '03/12/2014', 456, 12, 'Rüttistrasse', '2014-12-03 13:08:21', 'Zollikofen big room', 'Like to it on the veranda.', 'You should be good at grilling', 3072, NULL),
(19, 100, 'Ittigen', '03.12.2014', '28.01.2015', 'Very big, enough space for a moon', 1000, 250, 66, '46.9835285', '7.4784102', 107, '03/12/2014', 800, 35, 'Langfeldstrasse', '2014-12-03 13:12:53', 'Ittigen for the win', 'Cool about loud music', 'Have a good sound taste', 3063, NULL),
(20, 10, 'Bern', '19.12.2014', '08.07.2015', 'We love all animals and all non smokers and smokers on our balcony.', 130, 1000, 2, '46.8198633', '7.5862142', 108, '03/12/2014', 980, 23, 'Bahnhofstrasse', '2014-12-03 13:15:46', 'Bern Railwaystation', 'Hippies', 'Hippie', 3008, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Address`
--

CREATE TABLE IF NOT EXISTS `Address` (
`id` bigint(20) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `houseNr` int(11) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip` int(11) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=108 ;

--
-- Daten für Tabelle `Address`
--

INSERT INTO `Address` (`id`, `city`, `houseNr`, `street`, `zip`) VALUES
(102, 'Uno', 1, 'First', 1),
(103, 'Primero', 1, 'Uno', 0),
(104, 'One', 1, 'Uno', 1),
(105, 'Ittigen', 23, 'Jungerngasse', 3063),
(106, 'Ittigen', 45, 'Fuchshubelstrasse', 3063),
(107, 'Ittigen', 42, 'Grauholzstrasse', 3063);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Ad_Picture`
--

CREATE TABLE IF NOT EXISTS `Ad_Picture` (
  `Ad_id` bigint(20) NOT NULL,
  `pictures_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `Ad_Picture`
--

INSERT INTO `Ad_Picture` (`Ad_id`, `pictures_id`) VALUES
(10, 16),
(11, 17),
(12, 18),
(13, 19),
(14, 20),
(15, 21),
(15, 22),
(15, 23),
(16, 24),
(17, 25),
(18, 26),
(19, 27),
(19, 28),
(19, 29),
(19, 30),
(20, 31);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Bookmark`
--

CREATE TABLE IF NOT EXISTS `Bookmark` (
`bookmarkId` bigint(20) NOT NULL,
  `adId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Daten für Tabelle `Bookmark`
--

INSERT INTO `Bookmark` (`bookmarkId`, `adId`, `userId`) VALUES
(1, 10, 106),
(2, 11, 106),
(3, 10, 107),
(4, 12, 107),
(5, 10, 108),
(6, 12, 108),
(7, 13, 108);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Enquiry`
--

CREATE TABLE IF NOT EXISTS `Enquiry` (
`enquiryId` bigint(20) NOT NULL,
  `adId` bigint(20) DEFAULT NULL,
  `messageText` varchar(255) DEFAULT NULL,
  `rating` int(11) NOT NULL,
  `receiverId` bigint(20) DEFAULT NULL,
  `senderId` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `timestamp` datetime DEFAULT NULL,
  `unread` tinyint(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Daten für Tabelle `Enquiry`
--

INSERT INTO `Enquiry` (`enquiryId`, `adId`, `messageText`, `rating`, `receiverId`, `senderId`, `status`, `timestamp`, `unread`) VALUES
(1, 11, 'Hi guys, \r\n\r\nI''m interested in your room. Let''s meet and see if I''m going to be your new roomie.\r\n\r\nCheers, Beta', 0, 105, 106, 0, '2014-12-03 12:55:50', 1),
(2, 10, 'Hi guys, \r\n\r\nI''m interested in your room. Let''s meet and see if I''m going to be your new roomie.\r\n\r\nCheers, Beta', 0, 105, 106, 0, '2014-12-03 12:56:23', 1),
(3, 10, 'Hi guys, \r\n\r\nI''m interested in your room. Let''s meet and see if I''m going to be your new roomie.\r\n\r\nCheers, Gamma', 0, 105, 107, 0, '2014-12-03 12:58:13', 1),
(4, 12, 'Hi guys, \r\n\r\nI''m interested in your room. Let''s meet and see if I''m going to be your new roomie.\r\n\r\nCheers, Gamma', 0, 106, 107, 0, '2014-12-03 12:58:21', 0),
(5, 10, 'Hi guys, \r\n\r\nI''m interested in your room. Let''s meet and see if I''m going to be your new roomie.\r\n\r\nCheers, Delta', 0, 105, 108, 0, '2014-12-03 13:05:43', 1),
(6, 12, 'Hi guys, \r\n\r\nI''m interested in your room. Let''s meet and see if I''m going to be your new roomie.\r\n\r\nCheers, Delta', 0, 106, 108, 0, '2014-12-03 13:05:52', 0),
(7, 13, 'Hi guys, \r\n\r\nI''m interested in your room. Let''s meet and see if I''m going to be your new roomie.\r\n\r\nCheers, Delta', 0, 106, 108, 0, '2014-12-03 13:06:02', 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Notification`
--

CREATE TABLE IF NOT EXISTS `Notification` (
`id` bigint(20) NOT NULL,
  `adId` bigint(20) DEFAULT NULL,
  `notificationText` varchar(255) DEFAULT NULL,
  `unread` tinyint(1) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `Notification`
--

INSERT INTO `Notification` (`id`, `adId`, `notificationText`, `unread`, `userId`) VALUES
(1, 19, 'Hey, there''s a new ad that might be interesting for you! Click to check it out!', 1, 106),
(2, 20, 'Hey, there''s a new ad that might be interesting for you! Click to check it out!', 1, 107);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Picture`
--

CREATE TABLE IF NOT EXISTS `Picture` (
`id` bigint(20) NOT NULL,
  `fileName` varchar(255) DEFAULT NULL,
  `filePath` varchar(255) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Daten für Tabelle `Picture`
--

INSERT INTO `Picture` (`id`, `fileName`, `filePath`) VALUES
(3, '1417604029329_${loggedInUser.id}_tumblr_nekip67jKr1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(4, '1417604210302_${loggedInUser.id}_tumblr_nekip67jKr1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(5, '1417604244219_${loggedInUser.id}_tumblr_netqa2F5H31raodjgo1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(6, '1417604445607_${loggedInUser.id}_tumblr_nekip67jKr1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(8, '1417604494440_${loggedInUser.id}_Rubik001.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(9, '1417604590667_${loggedInUser.id}_Rubik001.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(10, '1417604725321_${loggedInUser.id}_tumblr_nekip67jKr1qh422go1_1280.jpeg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(11, '1417604805979_${loggedInUser.id}_tumblr_nekip67jKr1qh422go1_1280.jpeg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(12, '1417605560670_${loggedInUser.id}_tumblr_netqa2F5H31raodjgo1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(13, '1417606015622_${loggedInUser.id}_Rubik001.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(14, '1417606084860_${loggedInUser.id}_Rubik001.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(15, '1417606880727_Rubik001.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(16, '1417607156298_tumblr_netqa2F5H31raodjgo1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(17, '1417607260043_tumblr_ney3flT7H81qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(18, '1417607462931_tumblr_nftk8cHWvt1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(19, '1417607576195_tumblr_nftk9tTr4v1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(20, '1417607683092_tumblr_nftkbzmluu1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(21, '1417608078981_tumblr_nfx67gJoJG1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(22, '1417608078981_tumblr_nfx69fACz01qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(23, '1417608078999_tumblr_nfx6603Vir1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(24, '1417608195510_tumblr_nfx6aww6xp1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(25, '1417608283222_tumblr_nfx6a40u1g1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(26, '1417608496282_tumblr_nfx67gJoJG1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(27, '1417608770968_tumblr_nftk9tTr4v1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(28, '1417608770969_tumblr_nftkbzmluu1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(29, '1417608770984_tumblr_nftkci2fWb1qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(30, '1417608770985_tumblr_nftkdcqPo71qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/'),
(31, '1417608941739_tumblr_nftkdcqPo71qh422go1_1280.jpg', '/Users/artthik/Desktop/INFO/3. Semester/Einführung in die Software Entwicklung/Exercices/project/ese2014-team7/Skeleton/src/main/webapp/img/ad/');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `Search`
--

CREATE TABLE IF NOT EXISTS `Search` (
`id` bigint(20) NOT NULL,
  `addCostMax` bigint(20) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `priceMax` bigint(20) DEFAULT NULL,
  `priceMin` bigint(20) DEFAULT NULL,
  `roomSizeMax` bigint(20) DEFAULT NULL,
  `roomSizeMin` bigint(20) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `Search`
--

INSERT INTO `Search` (`id`, `addCostMax`, `city`, `priceMax`, `priceMin`, `roomSizeMax`, `roomSizeMin`, `timestamp`, `userId`) VALUES
(1, NULL, 'Ittigen', 1680, 0, 300, 15, '2014-12-03 13:09:40', 106),
(2, NULL, 'Bern', 3000, 0, 300, 0, '2014-12-03 13:14:02', 107);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `User`
--

CREATE TABLE IF NOT EXISTS `User` (
`id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `address_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=109 ;

--
-- Daten für Tabelle `User`
--

INSERT INTO `User` (`id`, `email`, `firstName`, `lastName`, `password`, `address_id`) VALUES
(103, 'user@one.com', 'User', 'One', '$2a$10$fySworAXZqO84KvhiVRvVegsDIaZz2GmxzRDLRCxfd0gtC1Lnkuxe', NULL),
(104, 'one@test.com', 'One', 'Eins', '$2a$10$sq9Il0kcgjzZ.IWMRLm3eOtGoMNiQXfe0H.i15.inpBaqyEuGFq5a', NULL),
(105, 'alpha@test.com', 'Alpha', 'Eins', '$2a$10$wCRFK6kdcZBkJXIDxMnUXOESCtOdgLGl5s4D1YJNSalFTSNkc6GDS', NULL),
(106, 'beta@test.com', 'Beta', 'Zwei', '$2a$10$eEj6vGsbhdqextmTvt/i9.cgg9//y5/n16EJ/d1tj1Nr29rD09qBW', NULL),
(107, 'gamma@test.com', 'Gamma', 'Drei', '$2a$10$DJNt.jyorjBxSsPeUdjJceeVfzz16Frv.haZLKFVHwqJNGOYLMmhi', NULL),
(108, 'delta@test.com', 'Delta', 'Vier', '$2a$10$KpuVV2/nqVMCjr.kfDOMTeb9FW3XeJU4ql9dVw/US2W3ZBaDDWDT2', NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `User_Search`
--

CREATE TABLE IF NOT EXISTS `User_Search` (
  `User_id` bigint(20) NOT NULL,
  `searches_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `VisitAppointment`
--

CREATE TABLE IF NOT EXISTS `VisitAppointment` (
`id` bigint(20) NOT NULL,
  `endDate` datetime DEFAULT NULL,
  `equiryId` bigint(20) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Ad`
--
ALTER TABLE `Ad`
 ADD PRIMARY KEY (`id`), ADD KEY `FK84346E1CC99` (`ad_id`);

--
-- Indexes for table `Address`
--
ALTER TABLE `Address`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Ad_Picture`
--
ALTER TABLE `Ad_Picture`
 ADD PRIMARY KEY (`Ad_id`,`pictures_id`), ADD UNIQUE KEY `pictures_id` (`pictures_id`), ADD KEY `FKBEB9B0E277241104` (`pictures_id`), ADD KEY `FKBEB9B0E246E1CC99` (`Ad_id`);

--
-- Indexes for table `Bookmark`
--
ALTER TABLE `Bookmark`
 ADD PRIMARY KEY (`bookmarkId`);

--
-- Indexes for table `Enquiry`
--
ALTER TABLE `Enquiry`
 ADD PRIMARY KEY (`enquiryId`);

--
-- Indexes for table `Notification`
--
ALTER TABLE `Notification`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Picture`
--
ALTER TABLE `Picture`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Search`
--
ALTER TABLE `Search`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
 ADD PRIMARY KEY (`id`), ADD KEY `FK285FEBF0E6CADB` (`address_id`);

--
-- Indexes for table `User_Search`
--
ALTER TABLE `User_Search`
 ADD PRIMARY KEY (`User_id`,`searches_id`), ADD UNIQUE KEY `searches_id` (`searches_id`), ADD KEY `FK2302409C14A9418B` (`searches_id`), ADD KEY `FK2302409C519857D9` (`User_id`);

--
-- Indexes for table `VisitAppointment`
--
ALTER TABLE `VisitAppointment`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Ad`
--
ALTER TABLE `Ad`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `Address`
--
ALTER TABLE `Address`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=108;
--
-- AUTO_INCREMENT for table `Bookmark`
--
ALTER TABLE `Bookmark`
MODIFY `bookmarkId` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `Enquiry`
--
ALTER TABLE `Enquiry`
MODIFY `enquiryId` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `Notification`
--
ALTER TABLE `Notification`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `Picture`
--
ALTER TABLE `Picture`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=32;
--
-- AUTO_INCREMENT for table `Search`
--
ALTER TABLE `Search`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `User`
--
ALTER TABLE `User`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=109;
--
-- AUTO_INCREMENT for table `VisitAppointment`
--
ALTER TABLE `VisitAppointment`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `Ad`
--
ALTER TABLE `Ad`
ADD CONSTRAINT `FK84346E1CC99` FOREIGN KEY (`ad_id`) REFERENCES `Ad` (`id`);

--
-- Constraints der Tabelle `Ad_Picture`
--
ALTER TABLE `Ad_Picture`
ADD CONSTRAINT `FKBEB9B0E246E1CC99` FOREIGN KEY (`Ad_id`) REFERENCES `Ad` (`id`),
ADD CONSTRAINT `FKBEB9B0E277241104` FOREIGN KEY (`pictures_id`) REFERENCES `Picture` (`id`);

--
-- Constraints der Tabelle `User`
--
ALTER TABLE `User`
ADD CONSTRAINT `FK285FEBF0E6CADB` FOREIGN KEY (`address_id`) REFERENCES `Address` (`id`);

--
-- Constraints der Tabelle `User_Search`
--
ALTER TABLE `User_Search`
ADD CONSTRAINT `FK2302409C14A9418B` FOREIGN KEY (`searches_id`) REFERENCES `Search` (`id`),
ADD CONSTRAINT `FK2302409C519857D9` FOREIGN KEY (`User_id`) REFERENCES `User` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
