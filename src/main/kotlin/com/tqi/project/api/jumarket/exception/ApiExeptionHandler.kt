package com.tqi.project.api.jumarket.exception

import com.tqi.project.api.jumarket.exception.error.ErrorResponse
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontradaException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.sql.SQLIntegrityConstraintViolationException
import java.time.OffsetDateTime

@ControllerAdvice
class ApiExeptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException::class)
    fun handleEntidadeNaoEncontrada(ex: EntidadeNaoEncontradaException): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.NOT_FOUND
        val erro = ErrorResponse(status.value(), OffsetDateTime.now(), ex.message)
        return ResponseEntity(erro, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException (ex : HttpMessageNotReadableException) : ResponseEntity<ErrorResponse> {
        val status = HttpStatus.BAD_REQUEST
        val erro = ErrorResponse(status.value(), OffsetDateTime.now(), "RequestBody incorreto, verifique o json.")
        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex : MethodArgumentNotValidException)
                                                : ResponseEntity<ErrorResponse>{
        val status = HttpStatus.BAD_REQUEST
        val erro = ErrorResponse(status.value(), OffsetDateTime.now(),
                "Verifique se as informações do RequestBody estão preenchidas corretamente.")
        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun handleSQLIntegrityConstraintViolationException(ex : SQLIntegrityConstraintViolationException)
                                                        : ResponseEntity<ErrorResponse>{
        val status = HttpStatus.BAD_REQUEST
        val erro = ErrorResponse(status.value(), OffsetDateTime.now(),
                "Não é possível inserir itens duplicados.")
        return ResponseEntity(erro, HttpStatus.BAD_REQUEST)
    }



}