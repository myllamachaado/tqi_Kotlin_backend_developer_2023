package com.tqi.project.api.jumarket.DTO.request

import com.tqi.project.api.jumarket.entity.Categoria
import javax.validation.constraints.NotEmpty

data class CategoriaDTO (
    @field:NotEmpty(message = "Input 'nomeCategoria' inválido.") val nomeCategoria: String
) {
    fun toEntity(): Categoria = Categoria(
        nomeCategoria = this.nomeCategoria
    )
}