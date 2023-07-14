CREATE TABLE `produto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome_produto` VARCHAR(255) NOT NULL,
  `unidade_id` BIGINT NOT NULL,
  `categoria_id` BIGINT NOT NULL,
  `preco_unitario` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `id_unidade_idx` (`unidade_id` ASC) VISIBLE,
  INDEX `id_categoria_idx` (`categoria_id` ASC) VISIBLE,
  CONSTRAINT `id_unidade` FOREIGN KEY (`unidade_id`) REFERENCES `unidade` (`id`),
  CONSTRAINT `id_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;