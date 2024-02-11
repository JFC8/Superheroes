
USE `superheroes`;


SET FOREIGN_KEY_CHECKS=0;


/* Drop Tables */

DROP TABLE IF EXISTS `heroes` CASCADE
;


/* Create Tables */

CREATE TABLE `heroes`
(
	`id` VARCHAR(36) NOT NULL,
	`hero_name` VARCHAR(50) NOT NULL,
	`first_name` VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
	`power` VARCHAR(50) NOT NULL,
	`created_at` DATETIME NOT NULL,
	`updated_at` DATETIME,
	CONSTRAINT `PK_users` PRIMARY KEY (`id`)
)
;

/* Create Indexes, Uniques, Checks */

ALTER TABLE `heroes`
 ADD CONSTRAINT `UK_heroes_heroname` UNIQUE (`hero_name`)
;


SET FOREIGN_KEY_CHECKS=1 ;
