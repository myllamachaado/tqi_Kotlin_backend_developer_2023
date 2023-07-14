package com.tqi.project.api.jumarket.exception.error

import com.tqi.project.api.jumarket.entity.Produto
import java.time.OffsetDateTime

data class ErrorResponse (
    private val status: Int? = 0,
    private val data: OffsetDateTime? = null,
    private val message: String? = ""
) {
    constructor(error: ErrorResponse) : this(error.status, error.data, error.message)
}