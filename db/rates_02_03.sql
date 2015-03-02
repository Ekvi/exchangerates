-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.36 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных exchangerates
CREATE DATABASE IF NOT EXISTS `exchangerates` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `exchangerates`;


-- Дамп структуры для таблица exchangerates.banksinfo
CREATE TABLE IF NOT EXISTS `banksinfo` (
  `bankName` varchar(100) NOT NULL,
  `url` text NOT NULL,
  `mainRegexp` varchar(100) NOT NULL,
  `replaceRegexp` varchar(35) NOT NULL,
  PRIMARY KEY (`bankName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы exchangerates.banksinfo: ~18 rows (приблизительно)
/*!40000 ALTER TABLE `banksinfo` DISABLE KEYS */;
REPLACE INTO `banksinfo` (`bankName`, `url`, `mainRegexp`, `replaceRegexp`) VALUES
	('OPT Bank', 'http://www.otpbank.com.ua', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('Unicredit Bank', 'http://www.unicredit.ua', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('Universal банк', 'http://www.universalbank.com.ua', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('Альфа Банк', 'http://www.alfabank.ua', '.*\\n.*\\n(.*\\d)((\\/\\d+.\\d+)|(\\/\\d+))', '\\D+'),
	('ВТБ', 'http://vtb.ua/private/av_currency/cur_operations/', '.*\\n(.*\\d).*\\n(.*\\d)', '.*<b>'),
	('Кредит Агриколь Банк', 'https://credit-agricole.ua', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('НБУ', 'http://www.bank.gov.ua/control/uk/index', '.*\\n(.*\\d)', '\\D+'),
	('Пивденный банк', 'http://bank.com.ua/ru/', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('Приват банк', 'https://privatbank.ua', '.*\\n(.*\\d).*\\n(.*\\d)', '<.*>'),
	('Проминвест банк', 'http://www.pib.com.ua', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('ПУМБ', 'http://pumb.ua/ru/', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('Райффайзен Банк Аваль', 'http://www.aval.ua', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('СберБанк России', 'http://www.sberbank.ua', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('УкрГаз банк', 'http://www.ukrgasbank.com/eng/?%20&/', '.*\\n.*\\n.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('УкрСибБанк', 'https://my.ukrsibbank.com/ua/personal', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('УкрЭксим банк', 'http://www.eximb.com/ukr/personal/', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('Финансы и Кредит Банк', 'http://www.fcbank.com.ua', '.*\\n(.*\\d).*\\n(.*\\d)', '\\D+'),
	('Чёрный рынок', 'http://finance.i.ua', '.*\\n.*\\d.*\\n(.*\\d.*)', '.*<big>(\\d+\\.\\d+|\\d+)<\\/big>');
/*!40000 ALTER TABLE `banksinfo` ENABLE KEYS */;


-- Дамп структуры для таблица exchangerates.banksrates
CREATE TABLE IF NOT EXISTS `banksrates` (
  `name` varchar(100) NOT NULL,
  `usdBuying` double DEFAULT NULL,
  `usdCelling` double DEFAULT NULL,
  `eurBuying` double DEFAULT NULL,
  `eurCelling` double DEFAULT NULL,
  `rubBuying` double DEFAULT NULL,
  `rubCelling` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы exchangerates.banksrates: ~16 rows (приблизительно)
/*!40000 ALTER TABLE `banksrates` DISABLE KEYS */;
REPLACE INTO `banksrates` (`name`, `usdBuying`, `usdCelling`, `eurBuying`, `eurCelling`, `rubBuying`, `rubCelling`, `date`) VALUES
	('OPT Bank', 27, 30, 30, 33.7, 0, 0, '2015-03-02'),
	('Unicredit Bank', 27, 32, 29, 35, 0.4, 0.5, '2015-03-02'),
	('Universal банк', 26.5, 28.5, 29.4, 32.3, 0, 0, '2015-03-02'),
	('Альфа Банк', 26, 32, 29, 36, 0.42, 0.54, '2015-03-02'),
	('ВТБ', 27, 30, 30.11, 33.63, 0.435, 0.491, '2015-03-02'),
	('Кредит Агриколь Банк', 26, 28, 29, 31.6, 0.41, 0.465, '2015-03-02'),
	('Пивденный банк', 24, 28.5, 28, 32.5, 0.4, 0.5, '2015-03-02'),
	('Приват банк', 26, 30, 29, 33.6, 0.42, 0.5, '2015-03-02'),
	('Проминвест банк', 26.51, 30.99, 29.01, 34.99, 0.416, 0.499, '2015-03-02'),
	('ПУМБ', 26.5, 30.5, 29.7, 34.2, 0.42, 0.499, '2015-03-02'),
	('Райффайзен Банк Аваль', 25, 31, 27.5, 35, 0.4, 0.5, '2015-03-02'),
	('СберБанк России', 26, 30, 28.92, 33.88, 0.415, 0.5, '2015-03-02'),
	('УкрГаз банк', 24, 29.5, 27, 34, 0.4, 0.5, '2015-03-02'),
	('УкрСибБанк', 23.5, 29.5, 25.3, 33.5, 0.36, 0.49, '2015-03-02'),
	('УкрЭксим банк', 24, 29, 27, 32.5, 0.39, 0.472, '2015-03-02'),
	('Финансы и Кредит Банк', 25, 31, 26, 35, 0.38, 0.5, '2015-03-02');
/*!40000 ALTER TABLE `banksrates` ENABLE KEYS */;


-- Дамп структуры для таблица exchangerates.mainrates
CREATE TABLE IF NOT EXISTS `mainrates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `averageUsdBuying` double NOT NULL,
  `averageUsdCelling` double NOT NULL,
  `averageEurBuying` double NOT NULL,
  `averageEurCelling` double NOT NULL,
  `averageRubBuying` double NOT NULL,
  `averageRubCelling` double NOT NULL,
  `blackMarketUsd` double NOT NULL,
  `blackMarketEur` double NOT NULL,
  `blackMarketRub` double NOT NULL,
  `nbuUsd` double NOT NULL,
  `nbuEur` double NOT NULL,
  `nbuRub` double NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы exchangerates.mainrates: ~42 rows (приблизительно)
/*!40000 ALTER TABLE `mainrates` DISABLE KEYS */;
REPLACE INTO `mainrates` (`id`, `averageUsdBuying`, `averageUsdCelling`, `averageEurBuying`, `averageEurCelling`, `averageRubBuying`, `averageRubCelling`, `blackMarketUsd`, `blackMarketEur`, `blackMarketRub`, `nbuUsd`, `nbuEur`, `nbuRub`, `date`) VALUES
	(2, 16.183076923076918, 16.43769230769231, 18.590769230769233, 19.141538461538463, 0.23662272727272723, 0.26492272727272725, 20.57, 23.69, 0.3124, 15.807737, 18.318006, 0.24255, '2015-01-20'),
	(3, 16.181, 16.433, 18.59, 19.13, 0.237, 0.265, 20.7, 23.75, 0.313, 15.79, 18.3, 0.24, '2015-01-21'),
	(4, 16.198461538461537, 16.43769230769231, 18.58230769230769, 19.123846153846152, 0.23654999999999998, 0.2609045454545455, 20.93, 23.99, 0.3149, 15.767642, 18.279427000000002, 0.24051999999999998, '2015-01-22'),
	(17, 16.199230769230766, 16.438076923076924, 18.531269230769233, 19.160769230769237, 0.2370909090909091, 0.263, 20.68, 23.71, 0.3192, 15.79205, 18.347203999999998, 0.24147, '2015-01-23'),
	(25, 16.1725, 16.43041666666667, 18.479708333333335, 19.15916666666667, 0.23650000000000002, 0.264, 20.51, 23.54, 0.3126, 15.79205, 18.347203999999998, 0.24147, '2015-01-24'),
	(28, 16.1725, 16.43041666666667, 18.479708333333335, 19.15916666666667, 0.23650000000000002, 0.264, 20.65, 23.56, 0.3121, 15.79205, 18.347203999999998, 0.24147, '2015-01-25'),
	(43, 16.23923076923077, 16.461538461538463, 18.06692307692308, 18.649884615384618, 0.24186818181818187, 0.2686772727272727, 20.73, 23.5, 0.3137, 15.79205, 18.347203999999998, 0.24147, '2015-01-26'),
	(54, 16.262307692307694, 16.48846153846154, 18.052307692307693, 18.626153846153844, 0.23745454545454547, 0.2623636363636364, 20.64, 23.32, 0.3074, 15.899591000000001, 17.8775, 0.2424, '2015-01-27'),
	(66, 16.295384615384616, 16.51461538461539, 18.26846153846154, 18.833076923076916, 0.2342272727272727, 0.2566136363636364, 20.88, 23.51, 0.3124, 15.869445, 17.941995, 0.23401, '2015-01-28'),
	(69, 16.328846153846154, 16.548384615384613, 18.319999999999997, 18.89769230769231, 0.2330909090909091, 0.2566136363636364, 21.07, 23.54, 0.3096, 15.869445, 17.941995, 0.23401, '2015-01-29'),
	(71, 16.389999999999997, 16.60615384615385, 18.374615384615385, 18.89846153846154, 0.23163636363636364, 0.2531445454545454, 20.86, 23.46, 0.3032, 15.954336000000001, 18.098599, 0.23759000000000002, '2015-01-30'),
	(73, 16.369999999999997, 16.60166666666667, 18.333333333333332, 18.897500000000004, 0.2293, 0.253859, 20.85, 23.68, 0.3002, 16.157817, 18.28257, 0.23509000000000002, '2015-01-31'),
	(74, 16.369999999999997, 16.60166666666667, 18.333333333333332, 18.897500000000004, 0.2293, 0.253859, 20.86, 23.32, 0.3023, 16.157817, 18.28257, 0.23509000000000002, '2015-02-01'),
	(75, 16.463846153846152, 16.69538461538462, 18.543846153846154, 19.05369230769231, 0.22918181818181818, 0.25323181818181817, 20.98, 23.58, 0.3042, 16.146995999999998, 18.254179, 0.23426, '2015-02-02'),
	(76, 16.548461538461535, 16.77461538461538, 18.62769230769231, 19.123115384615385, 0.22945454545454547, 0.25176818181818184, 21.15, 23.52, 0.3049, 16.241449, 18.369079, 0.23314, '2015-02-03'),
	(77, 16.66769230769231, 16.878461538461544, 18.839999999999996, 19.28980769230769, 0.23890909090909096, 0.2660045454545455, 22.11, 24.95, 0.3186, 16.728929, 19.03083, 0.24684, '2015-02-04'),
	(78, 16.712, 17.137, 18.946, 19.746, 0.243, 0.264, 22.95, 26.1, 0.337, 17.99, 20.602, 0.275, '2015-02-05'),
	(79, 21.665833333333335, 23.715833333333332, 24.338333333333335, 27.441666666666663, 0.2892, 0.3660999999999999, 24.16, 27.35, 0.3355, 23.13058, 26.391992000000002, 0.33712, '2015-02-06'),
	(80, 21.665833333333335, 23.715833333333332, 24.338333333333335, 27.441666666666663, 0.2892, 0.3660999999999999, 25.74, 29.07, 0.3577, 23.13058, 26.391992000000002, 0.33712, '2015-02-07'),
	(81, 21.84, 24.05818181818182, 24.491818181818186, 27.855454545454542, 0.28800000000000003, 0.37299999999999994, 25, 28.47, 0.365, 23.13058, 26.391992000000002, 0.33712, '2015-02-08'),
	(84, 22.108333333333334, 24.299166666666665, 24.888333333333335, 27.808333333333334, 0.2964, 0.36829999999999996, 25.27, 28.66, 0.3692, 24.956198, 28.567359999999997, 0.37788, '2015-02-09'),
	(85, 22.741666666666664, 24.92916666666667, 25.84166666666667, 28.582499999999996, 0.3149, 0.3889, 25.49, 29.18, 0.3781, 24.840495, 28.007658000000003, 0.37761999999999996, '2015-02-10'),
	(86, 23.458333333333332, 25.209166666666672, 26.384166666666662, 28.855833333333333, 0.3316, 0.3914, 25.96, 29.05, 0.3832, 25.558067, 28.872948, 0.39052, '2015-02-11'),
	(87, 24.11666666666667, 25.6825, 26.980833333333337, 29.382499999999993, 0.3426, 0.39859999999999995, 26.32, 29.73, 0.393, 25.099142, 28.397168999999998, 0.37995, '2015-02-12'),
	(88, 24.0925, 25.71666666666667, 27.138333333333335, 29.489999999999995, 0.34930000000000005, 0.40120000000000006, 25.7, 29.42, 0.3933, 25.918523, 29.360502999999998, 0.39211, '2015-02-13'),
	(89, 24.0925, 25.71666666666667, 27.138333333333335, 29.489999999999995, 0.34930000000000005, 0.40120000000000006, 25.66, 29.15, 0.3932, 25.918523, 29.360502999999998, 0.39211, '2015-02-14'),
	(90, 24.0925, 25.71666666666667, 27.138333333333332, 29.49, 0.3493, 0.40120000000000006, 25.59, 29.05, 0.3949, 23, 24, 0.35, '2015-02-15'),
	(91, 24.25916666666667, 25.762500000000003, 27.367500000000003, 29.646666666666665, 0.3556, 0.4072, 26.049893, 29.647383, 0.40024, 25.55, 29.1, 0.3974, '2015-02-16'),
	(92, 24.550833333333333, 25.912500000000005, 27.68416666666667, 29.723333333333333, 0.36150000000000004, 0.4117, 26.358712999999998, 30.07002, 0.42064, 26.02, 29.38, 0.4036, '2015-02-17'),
	(93, 25.075000000000003, 26.35833333333333, 28.4675, 30.458333333333332, 0.376, 0.4255, 26.799674, 30.591828, 0.42651000000000006, 26.96, 30.77, 0.4221, '2015-02-18'),
	(94, 25.60083333333333, 26.92916666666667, 29.005, 30.890833333333337, 0.38719999999999993, 0.4396, 27.74, 31.64, 0.4533, 26.799674, 30.591828, 0.42651000000000006, '2015-02-19'),
	(95, 26.133333333333336, 27.440833333333334, 29.504166666666666, 31.436666666666667, 0.3905, 0.44610000000000005, 29.44, 32.75, 0.4633, 27.858494, 31.722467, 0.44839, '2015-02-20'),
	(96, 26.258333333333336, 27.6075, 29.65416666666667, 31.728333333333335, 0.39390000000000003, 0.4511, 30.59, 33.87, 0.4804, 27.858494, 31.722467, 0.44839, '2015-02-21'),
	(97, 26.313333333333333, 27.686, 29.69333333333333, 31.739333333333335, 0.39638461538461545, 0.45346153846153847, 31.9, 35.16, 0.5019, 27.858494, 31.722467, 0.44839, '2015-02-22'),
	(98, 27.447142857142858, 29.263571428571424, 30.84357142857143, 33.71357142857143, 0.41383333333333333, 0.47775000000000006, 32.31, 36.23, 0.5033, 28.348066, 32.027645, 0.45928, '2015-02-23'),
	(99, 29.236428571428572, 31.855, 32.89, 36.642857142857146, 0.43716666666666676, 0.515, 36.51, 39.89, 0.5227, 28.291218, 31.963418, 0.45835, '2015-02-24'),
	(100, 29.75, 33.0625, 33.458125, 38.061249999999994, 0.4535714285714286, 0.5410714285714285, 37.17, 43.12, 0.5734, 28.046032, 31.770545000000002, 0.44161, '2015-02-25'),
	(101, 26.625625, 31.986874999999998, 29.705625, 36.363749999999996, 0.41457142857142854, 0.528642857142857, 34.55, 39.4, 0.5411, 30.010175, 34.049544999999995, 0.47946999999999995, '2015-02-26'),
	(102, 28.313125, 32.718125, 31.413749999999997, 36.80625, 0.386625, 0.4753124999999999, 33.83, 38.38, 0.5409, 27.76312, 31.419522999999998, 0.45730000000000004, '2015-02-27'),
	(103, 28.063125, 32.468125, 31.135624999999997, 36.528125, 0.38256249999999997, 0.4703124999999999, 31.24, 35.82, 0.5061, 27.76312, 31.419522999999998, 0.45730000000000004, '2015-02-28'),
	(104, 28.063125, 32.468125, 31.135624999999997, 36.528125, 0.38256249999999997, 0.4703124999999999, 30.31, 34.39, 0.4962, 27.76312, 31.419522999999998, 0.45730000000000004, '2015-03-01'),
	(105, 25.625625, 30.030625, 28.37125, 33.837500000000006, 0.35412499999999997, 0.4347500000000001, 29.06, 33.2, 0.4787, 26.858114, 30.188519999999997, 0.43834, '2015-03-02');
/*!40000 ALTER TABLE `mainrates` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;