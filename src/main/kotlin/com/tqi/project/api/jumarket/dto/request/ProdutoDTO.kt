package com.tqi.project.api.jumarket.dto.request

import com.tqi.project.api.jumarket.entity.Categoria
import com.tqi.project.api.jumarket.entity.Produto
import com.tqi.project.api.jumarket.entity.Unidade
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull


data class ProdutoDTO (
    @field:NotEmpty(message = "Input 'nomeProduto' inválido.") val nomeProduto: String,
    @field:NotNull(message = "Input 'precoUnitario' inválido.") val precoUnitario: BigDecimal,
    @field:NotNull(message = "Input 'unidadeId' inválido.") val unidadeId: Long,
    @field:NotNull(message = "Input 'categoriaId' inválido.") val categoriaId: Long
) {
    fun toEntity(): Produto = Produto(
        nomeProduto = this.nomeProduto,
        precoUnitario = this.precoUnitario,
        unidade = Unidade(id = this.unidadeId),
        categoria = Categoria(id = this.categoriaId)
    )
}