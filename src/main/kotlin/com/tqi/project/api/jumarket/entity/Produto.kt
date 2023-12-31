package com.tqi.project.api.jumarket.entity

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "produto")
data class Produto(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        @Column(name = "nome_produto", nullable = false) val nomeProduto: String = "",
        @Column(name = "preco_unitario", nullable = false) val precoUnitario: BigDecimal = BigDecimal.ZERO,

        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "unidade_id", nullable = false)
    val unidade: Unidade? = null,

        @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "categoria_id", nullable = false)
    val categoria: Categoria? = null
) : Serializable