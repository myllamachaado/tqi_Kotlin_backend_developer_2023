package com.tqi.project.api.jumarket.service

import com.tqi.project.api.jumarket.entity.Produto

interface IProduto {
    fun saveProduto(produto: Produto): Produto
    fun findAllProdutos(): List<Produto>
    fun findProduto(produtoId: Long): Produto
    fun updateProduto(produto: Produto, produtoId : Long): Produto
    fun deleteProduto(produtoId: Long)
}