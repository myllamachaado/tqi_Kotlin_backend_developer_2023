package com.tqi.project.api.jumarket.dto.response

import com.tqi.project.api.jumarket.entity.Carrinho
import com.tqi.project.api.jumarket.entity.ItemCompra

data class CarrinhoView (
    val id : Long,
    val itemCompra : ItemCompra?
) {
    constructor(carrinho: Carrinho): this (carrinho.id, carrinho.itemCompra)
}