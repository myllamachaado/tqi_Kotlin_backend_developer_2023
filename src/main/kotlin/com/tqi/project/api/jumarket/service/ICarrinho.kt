package com.tqi.project.api.jumarket.service

import com.tqi.project.api.jumarket.dto.response.VendaView
import com.tqi.project.api.jumarket.entity.Carrinho
import com.tqi.project.api.jumarket.enums.Pagamento

interface ICarrinho {
    fun adicionarItemCarrinho(produtoId: Long, quantidade : Int) : List<Carrinho>
    fun removerItemCarrinho(produtoId: Long) : List<Carrinho>
    fun finalizaCompra(pagamento: Pagamento) : VendaView
    fun carrinhoList() : List<Carrinho>
    fun esvaziaCarrinho()
}