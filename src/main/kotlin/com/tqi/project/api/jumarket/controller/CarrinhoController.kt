package com.tqi.project.api.jumarket.controller

import com.tqi.project.api.jumarket.dto.request.PagamentoDTO
import com.tqi.project.api.jumarket.dto.response.*
import com.tqi.project.api.jumarket.entity.Carrinho
import com.tqi.project.api.jumarket.service.implementacao.CarrinhoService
import com.tqi.project.api.jumarket.service.implementacao.VendaCarrinhoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/v1/carrinho")
class CarrinhoController(private val carrinhoService: CarrinhoService,
                         private val vendaCarrinhoService: VendaCarrinhoService) {
    @PostMapping("/adicionar/{produtoId}/{quantidade}")
    fun adicionarItemCarrinho(@PathVariable produtoId: Long,
                              @PathVariable quantidade : Int): ResponseEntity<List<CarrinhoView>> {
        val itens = carrinhoService.adicionarItemCarrinho(produtoId, quantidade)
                .stream().map { carrinho: Carrinho -> CarrinhoView(carrinho) }
                .collect(Collectors.toList())
        return ResponseEntity.ok().body(itens)
    }

    @DeleteMapping("/remover/{produtoId}")
    fun removerItemCarrinho(@PathVariable produtoId: Long): ResponseEntity<List<CarrinhoView>> {
        val itens = carrinhoService.removerItemCarrinho(produtoId)
                .stream().map { carrinho: Carrinho -> CarrinhoView(carrinho) }
                .collect(Collectors.toList())
        return ResponseEntity.ok().body(itens)
    }

    @PostMapping("/finalizar")
    fun finalizarCompra(@RequestBody formaPagamento: PagamentoDTO) : VendaView {
        return this.carrinhoService.finalizaCompra(formaPagamento.formaPagamento)
    }

}