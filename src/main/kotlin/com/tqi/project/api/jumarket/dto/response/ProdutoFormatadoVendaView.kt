package com.tqi.project.api.jumarket.dto.response

import com.tqi.project.api.jumarket.entity.Produto
import java.math.BigDecimal

data class ProdutoFormatadoVendaView(
        val nomeProduto: String,
        val precoUnitario: BigDecimal,
        val quantidade: Int,
        val valorTotal: BigDecimal
) {
    constructor(produto: Produto?, quantidade: Int, valorTotal : BigDecimal?) : this(
            produto!!.nomeProduto,
            produto.precoUnitario,
            quantidade,
            valorTotal!!
    )
}