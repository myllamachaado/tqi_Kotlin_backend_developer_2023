CREATE TABLE `unidade` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome_unidade` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `nome_unidade_UNIQUE` (`nome_unidade` ASC) VISIBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;