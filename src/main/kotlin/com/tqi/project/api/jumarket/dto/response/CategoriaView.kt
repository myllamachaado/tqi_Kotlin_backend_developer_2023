package com.tqi.project.api.jumarket.dto.response

import com.tqi.project.api.jumarket.entity.Categoria

data class CategoriaView (
    var idCategoria: Long?,
    var nomeCategoria: String
) {
    constructor(categoria: Categoria): this (
        idCategoria = categoria.id,
        nomeCategoria = categoria.nomeCategoria
    )
}