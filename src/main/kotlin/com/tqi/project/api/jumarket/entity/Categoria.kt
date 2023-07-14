package com.tqi.project.api.jumarket.entity

import javax.persistence.*

@Entity
@Table(name = "categoria")
data class Categoria(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  val id: Long = 0,
    @Column(name = "nome_categoria", nullable = false) val nomeCategoria: String = ""
)