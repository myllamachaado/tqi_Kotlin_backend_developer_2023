package com.tqi.project.api.jumarket.dto.request

import com.tqi.project.api.jumarket.entity.Unidade
import javax.validation.constraints.NotEmpty

data class UnidadeDTO (
    @field:NotEmpty(message = "Input 'nomeUnidade' inválido.") val nomeUnidade: String
){
    fun toEntity(): Unidade = Unidade(
        nomeUnidade = this.nomeUnidade
    )
}