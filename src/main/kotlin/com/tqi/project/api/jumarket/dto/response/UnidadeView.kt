package com.tqi.project.api.jumarket.dto.response

import com.tqi.project.api.jumarket.entity.Unidade

data class UnidadeView(
    var idUnidade: Long?,
    val nomeUnidade: String
) {
    constructor(unidade: Unidade): this (
        idUnidade = unidade.id,
        nomeUnidade = unidade.nomeUnidade
    )
}