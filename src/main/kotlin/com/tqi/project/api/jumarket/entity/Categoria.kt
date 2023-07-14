package com.tqi.project.api.jumarket.entity

import javax.persistence.*

@Entity
@Table(name = "categoria")
data class Categoria(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private val id: Long = 0,
    @Column(name = "nome_categoria", nullable = false) private val nomeCategoria: String = ""
) {
    constructor(categoria : Categoria) : this(categoria.id, categoria.nomeCategoria)
}