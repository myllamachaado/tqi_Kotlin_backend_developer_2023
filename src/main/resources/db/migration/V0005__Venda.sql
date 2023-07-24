CREATE TABLE `venda` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `valor_total` BIGINT NOT NULL,
  `forma_pagamento` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;