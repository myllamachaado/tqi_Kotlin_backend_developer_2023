CREATE TABLE `venda_carrinho` (
  `venda_id` BIGINT NOT NULL,
  `produto_id` BIGINT NOT NULL,
  `quantidade` DECIMAL(6,2) NOT NULL,
  `valor_unitario` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`venda_id`, `produto_id`),
  INDEX `fk_venda_has_produto_produto1_idx` (`produto_id` ASC) VISIBLE,
  INDEX `fk_venda_has_produto_venda1_idx` (`venda_id` ASC) VISIBLE,
  CONSTRAINT `fk_venda_has_produto_venda1`
    FOREIGN KEY (`venda_id`)
    REFERENCES `venda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_has_produto_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;