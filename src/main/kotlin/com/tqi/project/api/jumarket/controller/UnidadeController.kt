package com.tqi.project.api.jumarket.controller

import com.tqi.project.api.jumarket.dto.request.UnidadeDTO
import com.tqi.project.api.jumarket.dto.response.UnidadeView
import com.tqi.project.api.jumarket.entity.Unidade
import com.tqi.project.api.jumarket.service.implementacao.UnidadeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/unidades")
class UnidadeController(private val unidadeService: UnidadeService) {

    @PostMapping
    fun saveUnidade(@RequestBody @Valid unidadeDTO: UnidadeDTO): ResponseEntity<String> {
        val unidadeSalva: Unidade = this.unidadeService.saveUnidade(unidadeDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Unidade ${unidadeSalva.nomeUnidade} salva!")
    }

    @GetMapping
    fun findAllUnidades(): ResponseEntity<List<UnidadeView>> {
        val unidadesList: List<UnidadeView> = this.unidadeService.findAllUnidades()
            .stream().map { unidade: Unidade -> UnidadeView(unidade) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(unidadesList)
    }

    @GetMapping("/{unidadeId}")
    fun findCategoria(@PathVariable unidadeId: Long): ResponseEntity<UnidadeView> {
        val unidade: Unidade = this.unidadeService.findUnidade(unidadeId)
        return ResponseEntity.status(HttpStatus.OK).body(UnidadeView(unidade))
    }

    @PutMapping("/{unidadeId}")
    fun updateCategoria(@RequestBody @Valid unidadeDTO: UnidadeDTO,
                        @PathVariable unidadeId: Long): ResponseEntity<UnidadeView> {
        val unidade: Unidade = this.unidadeService.updateUnidade(unidadeDTO.toEntity(), unidadeId)
        return ResponseEntity.status(HttpStatus.OK).body(UnidadeView(unidade))
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{unidadeId}")
    fun deleteCategoria(@PathVariable unidadeId: Long) {
        this.unidadeService.deleteUnidade(unidadeId)
    }

}