package com.tqi.project.api.jumarket.dto.response

import com.tqi.project.api.jumarket.entity.Categoria
import com.tqi.project.api.jumarket.entity.Produto
import com.tqi.project.api.jumarket.entity.Unidade

data class ProdutoView (
    private val nomeProduto: String,
    private val precoUnitario: Float,
    private val unidade: Unidade?,
    private val categoria: Categoria?
) {
    constructor(produto: Produto) : this(
        produto.nomeProduto, produto.precoUnitario, produto.unidade, produto.categoria)
}