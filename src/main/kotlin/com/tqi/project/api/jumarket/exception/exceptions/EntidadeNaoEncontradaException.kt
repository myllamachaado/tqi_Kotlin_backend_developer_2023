package com.tqi.project.api.jumarket.exception.exceptions

data class EntidadeNaoEncontradaException(override val message: String?) : RuntimeException(message)