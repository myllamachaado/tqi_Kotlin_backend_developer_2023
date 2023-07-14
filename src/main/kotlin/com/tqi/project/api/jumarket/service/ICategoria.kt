package com.tqi.project.api.jumarket.service

import com.tqi.project.api.jumarket.entity.Categoria

interface ICategoria {
    fun saveCategoria(categoria: Categoria): Categoria
    fun findAllCategorias(): List<Categoria>
    fun findCategoria(categoriaId: Long): Categoria
    fun updateCategoria(categoria: Categoria, categoriaId : Long): Categoria
    fun deleteCategoria(categoriaId: Long)
}