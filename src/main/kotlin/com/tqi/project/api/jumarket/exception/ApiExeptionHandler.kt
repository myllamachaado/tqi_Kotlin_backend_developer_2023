package com.tqi.project.api.jumarket.exception

import com.tqi.project.api.jumarket.exception.error.ErrorResponse
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontradaException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.OffsetDateTime


@ControllerAdvice
class ApiExeptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException::class)
    fun handleEntidadeNaoEncontrada(ex: EntidadeNaoEncontradaException): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.NOT_FOUND
        val erro = ErrorResponse(status.value(), OffsetDateTime.now(), ex.message)
        return ResponseEntity(erro, HttpStatus.NOT_FOUND)
    }

}