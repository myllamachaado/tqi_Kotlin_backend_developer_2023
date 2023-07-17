package com.tqi.project.api.jumarket.entity

import javax.persistence.Embeddable
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

@Embeddable
data class ItemCompra (
    @OneToOne(fetch = FetchType.LAZY) @JoinColumn(name = "produto_id", nullable = false)
    val produto : Produto,
    var quantidade : Int = 0,
    val valorTotal : Float = 0.0F
)