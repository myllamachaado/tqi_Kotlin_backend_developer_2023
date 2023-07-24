package com.tqi.project.api.jumarket.entity

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name="venda")
data class Venda(
        @Id @Column(name = "id", nullable = false)
        @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        @Column(name = "valor_total", nullable = false) val valorTotal: BigDecimal = BigDecimal.ZERO,
        @Column(name = "forma_pagamento", nullable = false) val formaPagamento : String = ""
) : Serializable