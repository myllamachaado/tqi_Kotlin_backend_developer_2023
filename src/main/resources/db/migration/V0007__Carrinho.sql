CREATE TABLE `carrinho` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `produto_id` BIGINT NOT NULL,
  `quantidade` BIGINT NOT NULL,
  `valor_total` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `id_produto_idx` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `id_produto` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;