package com.tqi.project.api.jumarket.dto.response

import com.tqi.project.api.jumarket.entity.Venda
import java.math.BigDecimal

data class VendaView (
        val id : Long,
        val valorTotal : BigDecimal,
        val formaPagamento : String,
        val produtos: List<ProdutoFormatadoVendaView>
) {
    constructor(venda : Venda, produtos: List<ProdutoFormatadoVendaView>) : this(
            venda.id,
            venda.valorTotal,
            venda.formaPagamento,
            produtos
    )
}