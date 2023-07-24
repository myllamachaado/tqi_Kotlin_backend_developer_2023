package com.tqi.project.api.jumarket.service

import com.tqi.project.api.jumarket.entity.Carrinho
import com.tqi.project.api.jumarket.entity.Venda
import com.tqi.project.api.jumarket.entity.VendaCarrinho
import java.math.BigDecimal

interface IVenda {

    fun saveVenda(valorTotal : BigDecimal, formaPagamento : String) : Venda
    fun formataRelacionamentoVendaCarrinho(venda : Venda, itensCarrinho : List<Carrinho>) : List<VendaCarrinho>

}