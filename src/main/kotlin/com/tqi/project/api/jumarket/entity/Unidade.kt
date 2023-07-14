package com.tqi.project.api.jumarket.entity

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "unidade")
data class Unidade(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
    @Column(name = "nome_unidade", nullable = false) val nomeUnidade: String = ""
) {
    constructor(unidade: Unidade) : this (unidade.id, unidade.nomeUnidade)
}