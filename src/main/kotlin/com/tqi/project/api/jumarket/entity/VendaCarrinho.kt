package com.tqi.project.api.jumarket.entity

import java.io.Serializable
import java.math.BigDecimal
import javax.persistence.*

@Entity
data class VendaCarrinho(
        @EmbeddedId val vendaID : VendaID? = null,
        @Column(name = "quantidade", nullable = false) val quantidade: Int = 0,
        @Column(name = "valor_unitario", nullable = false) val valorUnitario: BigDecimal = BigDecimal.ZERO
) : Serializable