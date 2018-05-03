CREATE DATABASE `crypto` CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `User`(
	id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	login VARCHAR(20) NOT NULL UNIQUE,
	password VARCHAR(64) NOT NULL,
	permissions VARCHAR(255) NOT NULL,
	regDate DATETIME NOT NULL,
	ipAddress VARCHAR(50) NOT NULL,
	email VARCHAR(50),
	apiKey VARCHAR(255),
	apiSecret VARCHAR(255)
) ENGINE=InnoDB;

INSERT INTO User VALUES(null, 'admin', 'f603c40ced1068285660fb5a7c8137275a1c2613ffe7628d11b8c6274ea61f8a', 'member,modo,admin', NOW(), 'localhost', null, null, null);
