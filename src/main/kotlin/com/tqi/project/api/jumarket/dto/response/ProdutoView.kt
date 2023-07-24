package com.tqi.project.api.jumarket.dto.response

import com.tqi.project.api.jumarket.entity.Categoria
import com.tqi.project.api.jumarket.entity.Produto
import com.tqi.project.api.jumarket.entity.Unidade
import java.math.BigDecimal

data class ProdutoView (
        val nomeProduto: String,
        val precoUnitario: BigDecimal,
        val unidade: UnidadeView,
        val categoria: CategoriaView
) {
    constructor(produto: Produto?, unidade: Unidade?, categoria: Categoria?) : this(
        produto!!.nomeProduto, produto.precoUnitario, UnidadeView(unidade!!), CategoriaView(categoria!!))
}