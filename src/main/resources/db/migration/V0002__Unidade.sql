CREATE TABLE `unidade` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome_unidade` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `nome_unidade_UNIQUE` (`nome_unidade` ASC) VISIBLE
) ENGINE = InnoDB;