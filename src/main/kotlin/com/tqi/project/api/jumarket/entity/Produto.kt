package com.tqi.project.api.jumarket.entity

import javax.persistence.*

@Entity
@Table(name = "produto")
data class Produto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private val id: Long = 0,
    @Column(name = "nome_produto", nullable = false) private val nomeProduto: String = "",
    @Column(name = "preco_unitario", nullable = false) private val precoUnitario: Float = 0.0F,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "unidade_id", nullable = false)
    private val unidade: Unidade? = null,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "categoria_id", nullable = false)
    private val categoria: Categoria? = null
) {
    constructor(produto : Produto) : this(
        produto.id,
        produto.nomeProduto,
        produto.precoUnitario,
        produto.unidade,
        produto.categoria)
}