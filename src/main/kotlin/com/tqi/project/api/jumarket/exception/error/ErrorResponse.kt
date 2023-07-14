package com.tqi.project.api.jumarket.exception.error

import com.tqi.project.api.jumarket.entity.Produto
import java.time.OffsetDateTime

data class ErrorResponse (
    val status: Int? = 0,
    val data: OffsetDateTime? = null,
    val message: String? = ""
) {
    constructor(error: ErrorResponse) : this(error.status, error.data, error.message)
}