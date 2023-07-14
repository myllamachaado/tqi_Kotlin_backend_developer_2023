package com.tqi.project.api.jumarket.entity

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "categoria")
data class Categoria(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @Column(name = "nome_categoria", nullable = false) val nomeCategoria: String = ""
) {
    constructor(categoria : Categoria) : this(categoria.id, categoria.nomeCategoria)
}