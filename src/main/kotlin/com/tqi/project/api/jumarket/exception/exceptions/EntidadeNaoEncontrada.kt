package com.tqi.project.api.jumarket.exception.exceptions

data class EntidadeNaoEncontrada(override val message: String?) : RuntimeException(message)