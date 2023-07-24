package com.tqi.project.api.jumarket.dto.request

import com.tqi.project.api.jumarket.enums.Pagamento
import javax.validation.constraints.NotEmpty

data class PagamentoDTO (
        @field: NotEmpty(message = "Input 'formaPagamento' inválido.") val formaPagamento : Pagamento
)