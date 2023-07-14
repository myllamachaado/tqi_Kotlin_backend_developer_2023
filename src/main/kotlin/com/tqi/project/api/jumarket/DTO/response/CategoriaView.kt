package com.tqi.project.api.jumarket.DTO.response

import com.tqi.project.api.jumarket.entity.Categoria

data class CategoriaView (
    var idCategoria: Long?,
    val nomeCategoria: String
) {
    constructor(categoria: Categoria): this (
        idCategoria = categoria.id,
        nomeCategoria = categoria.nomeCategoria
    )
}