CREATE SCHEMA `sso` ;

CREATE TABLE `sso`.`application_key` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `application_code` VARCHAR(20) NULL COMMENT '',
  `application_key` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `application_code_UNIQUE` (`application_code` ASC)  COMMENT '',
  INDEX `INX_APPLICATION_CODE` (`application_code` ASC)  COMMENT '');

CREATE TABLE `sso`.`application_group` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `group_code` VARCHAR(20) NULL COMMENT '',
  `application_code` VARCHAR(20) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `group_code_UNIQUE` (`group_code` ASC)  COMMENT '',
  INDEX `INX_APPLICATION_GROUP_CODE` (`application_code` ASC)  COMMENT '');

CREATE TABLE `sso`.`sso_session` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `session_id` VARCHAR(45) NULL COMMENT '',
  `application_group` VARCHAR(20) NULL COMMENT '',
  `application_code` VARCHAR(20) NULL COMMENT '',
  `ip` VARCHAR(45) NULL COMMENT '',
  `username` VARCHAR(45) NULL COMMENT '',
  `application_session_id` VARCHAR(45) NULL COMMENT '',
  `login_date` TIMESTAMP NULL COMMENT '',
  `login_type` INT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `INX_SSO_SESSION_UQ` (`application_code` ASC, `username` ASC)  COMMENT '');

CREATE TABLE `sso`.`user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `username` VARCHAR(45) NULL COMMENT '',
  `name` VARCHAR(45) CHARACTER SET 'utf8' NULL COMMENT '',
  `status` INT NULL COMMENT '',
  `email` VARCHAR(45) NULL COMMENT '',
  `salt` VARCHAR(45) NULL COMMENT '',
  `password` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `INX_USER_USERNAME` (`username` ASC)  COMMENT '');
