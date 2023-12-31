package com.tqi.project.api.jumarket.controller

import com.tqi.project.api.jumarket.dto.request.ProdutoDTO
import com.tqi.project.api.jumarket.dto.response.ProdutoView
import com.tqi.project.api.jumarket.entity.Produto
import com.tqi.project.api.jumarket.service.implementacao.ProdutoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/produtos")
class ProdutoController(private val produtoService: ProdutoService) {

    @PostMapping
    fun saveProduto(@RequestBody @Valid produtoDTO: ProdutoDTO): ResponseEntity<String> {
        val produtoSalvo : Produto = this.produtoService.saveProduto(produtoDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Produto ${produtoSalvo.nomeProduto} salvo!")
    }

    @GetMapping
    fun findAllProdutos(): ResponseEntity<List<ProdutoView>> {
        val produtoList: List<ProdutoView> = this.produtoService.findAllProdutos()
            .stream().map { produto: Produto -> ProdutoView(produto, produto.unidade, produto.categoria) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(produtoList)
    }

    @GetMapping("/{produtoId}")
    fun findProduto(@PathVariable produtoId: Long): ResponseEntity<ProdutoView> {
        val produto: Produto = this.produtoService.findProduto(produtoId)
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoView(produto, produto.unidade, produto.categoria))
    }

    @PutMapping("/{produtoId}")
    fun updateProduto(@RequestBody @Valid produtoDTO: ProdutoDTO,
                      @PathVariable produtoId: Long): ResponseEntity<ProdutoView> {
        val produto: Produto = this.produtoService.updateProduto(produtoDTO.toEntity(), produtoId)
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoView(produto, produto.unidade, produto.categoria))
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{produtoId}")
    fun deleteProduto(@PathVariable produtoId: Long) {
        this.produtoService.deleteProduto(produtoId)
    }

}