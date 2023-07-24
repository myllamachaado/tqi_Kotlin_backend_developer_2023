package com.tqi.project.api.jumarket.dto.response

import com.tqi.project.api.jumarket.entity.Carrinho
import com.tqi.project.api.jumarket.entity.Produto
import java.math.BigDecimal

data class CarrinhoView (
        val produto: Produto,
        val quantidade: Int,
        var valorTotal: BigDecimal = produto.precoUnitario.times(BigDecimal(quantidade))
) {
    constructor(carrinho : Carrinho) : this(carrinho.produto, carrinho.quantidade)
}