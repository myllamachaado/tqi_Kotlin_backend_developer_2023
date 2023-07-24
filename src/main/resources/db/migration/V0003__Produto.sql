CREATE TABLE `produto` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `nome_produto` VARCHAR(255) NOT NULL,
  `preco_unitario` DECIMAL(6,2) NOT NULL,
  `categoria_id` BIGINT NOT NULL,
  `unidade_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_produto_categoria_idx` (`categoria_id` ASC) VISIBLE,
  INDEX `fk_produto_unidade1_idx` (`unidade_id` ASC) VISIBLE,
  CONSTRAINT `fk_produto_categoria`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_unidade1`
    FOREIGN KEY (`unidade_id`)
    REFERENCES `unidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;