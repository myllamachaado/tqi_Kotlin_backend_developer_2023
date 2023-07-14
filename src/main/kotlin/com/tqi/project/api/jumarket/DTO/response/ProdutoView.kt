package com.tqi.project.api.jumarket.DTO.response

import com.tqi.project.api.jumarket.entity.Categoria
import com.tqi.project.api.jumarket.entity.Produto
import com.tqi.project.api.jumarket.entity.Unidade

data class ProdutoView (
    val nomeProduto: String,
    val precoUnitario: Float,
    val unidade: Unidade?,
    val categoria: Categoria?
) {
    constructor(produto: Produto) : this(
        produto.nomeProduto, produto.precoUnitario, produto.unidade, produto.categoria)
}