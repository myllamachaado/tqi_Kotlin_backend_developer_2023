package com.tqi.project.api.jumarket.service

import com.tqi.project.api.jumarket.entity.Carrinho
interface ICarrinho {
    fun adicionarCarrinho(produtoId : Long, quantidade: Int): List<Carrinho>
    fun removerCarrinho(produtoId: Long ): List<Carrinho>
    fun listaItensCarrinho(): List<Carrinho>
}