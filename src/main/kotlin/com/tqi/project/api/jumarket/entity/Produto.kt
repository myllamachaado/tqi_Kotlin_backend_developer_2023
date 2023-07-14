package com.tqi.project.api.jumarket.entity

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "produto")
data class Produto(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @Column(name = "nome_produto", nullable = false) val nomeProduto: String = "",
    @Column(name = "preco_unitario", nullable = false) val precoUnitario: Float = 0.0F,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "unidade_id", nullable = false)
    val unidade: Unidade? = null,

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "categoria_id", nullable = false)
    val categoria: Categoria? = null
) {
    constructor(produto : Produto) : this(
        produto.id,
        produto.nomeProduto,
        produto.precoUnitario,
        produto.unidade,
        produto.categoria)
}