package com.tqi.project.api.jumarket.entity

import java.io.Serializable
import javax.persistence.*

@Entity
data class Carrinho(
        @Id @Column(name = "id", nullable = false) val id : Long = 0,
        @Column(name = "quantidade", nullable = false) val quantidade: Int,
        @OneToOne @JoinColumn(name = "produto_id") val produto: Produto
) : Serializable