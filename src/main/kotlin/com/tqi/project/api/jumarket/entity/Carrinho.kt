package com.tqi.project.api.jumarket.entity

import javax.persistence.*

@Entity
data class Carrinho (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id : Long = 0,
    @Embedded val itemCompra : ItemCompra? = null
) {
    constructor(carrinho: Carrinho) : this(carrinho.id, carrinho.itemCompra)
}