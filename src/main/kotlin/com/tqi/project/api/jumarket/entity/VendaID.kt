package com.tqi.project.api.jumarket.entity

import lombok.EqualsAndHashCode
import java.io.Serializable
import javax.persistence.Embeddable
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@EqualsAndHashCode
@Embeddable
data class VendaID (
        @ManyToOne @JoinColumn(name = "venda_id") val venda: Venda? = null,
        @ManyToOne @JoinColumn(name = "produto_id") val produto: Produto? = null
) : Serializable