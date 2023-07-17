package com.tqi.project.api.jumarket.controller

import com.tqi.project.api.jumarket.dto.response.CarrinhoView
import com.tqi.project.api.jumarket.entity.Carrinho
import com.tqi.project.api.jumarket.service.implementacao.CarrinhoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/v1/carrinho")
class CarrinhoController(private val carrinhoService: CarrinhoService) {

    @PostMapping("/adicionarCarrinho/{produtoId}/{quantidade}")
    fun addProdutoCarrinho(@PathVariable produtoId: Long,
                           @PathVariable quantidade : Int): ResponseEntity<List<CarrinhoView>> {

        val listaItensCarrinho = this.carrinhoService.adicionarCarrinho(produtoId, quantidade)
            .stream().map { carrinho : Carrinho -> CarrinhoView(carrinho) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(listaItensCarrinho)
    }

    @DeleteMapping("/removerCarrinho/{produtoId}")
    fun removerProdutoCarrinho(@PathVariable produtoId: Long) : ResponseEntity<List<CarrinhoView>> {

        val carrinhoAtualizado = this.carrinhoService.removerCarrinho(produtoId)
            .stream().map { carrinho : Carrinho -> CarrinhoView(carrinho) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoAtualizado)
    }

    @PostMapping("/finalizar")
    fun finalizarCompra(@PathVariable produtoId: Long,
                           @PathVariable quantidade : Int) {

    }

}