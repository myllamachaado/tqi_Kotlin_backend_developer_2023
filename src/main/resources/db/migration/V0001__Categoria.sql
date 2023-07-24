CREATE TABLE `categoria` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome_categoria` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `nome_categoria_UNIQUE` (`nome_categoria` ASC) VISIBLE
) ENGINE = InnoDB;


