package com.tqi.project.api.jumarket.controller

import com.tqi.project.api.jumarket.dto.request.CategoriaDTO
import com.tqi.project.api.jumarket.dto.response.CategoriaView
import com.tqi.project.api.jumarket.entity.Categoria
import com.tqi.project.api.jumarket.service.implementacao.CategoriaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/categorias")
class CategoriaController(private val categoriaService: CategoriaService) {

    @PostMapping
    fun saveCategoria(@RequestBody @Valid categoriaDTO: CategoriaDTO): ResponseEntity<String> {
        val categoriaSalva: Categoria = this.categoriaService.saveCategoria(categoriaDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Categoria ${categoriaSalva.nomeCategoria} salva!")
    }

    @GetMapping
    fun findAllCategorias(): ResponseEntity<List<CategoriaView>> {
        val categoriasList: List<CategoriaView> = this.categoriaService.findAllCategorias()
            .stream().map { categoria: Categoria -> CategoriaView(categoria) }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(categoriasList)
    }

    @GetMapping("/{categoriaId}")
    fun findCategoria(@PathVariable categoriaId: Long): ResponseEntity<CategoriaView> {
        val categoria: Categoria = this.categoriaService.findCategoria(categoriaId)
        return ResponseEntity.status(HttpStatus.OK).body(CategoriaView(categoria)
        )
    }

    @PutMapping("/{categoriaId}")
    fun updateCategoria(@RequestBody @Valid categoriaDTO: CategoriaDTO,
                        @PathVariable categoriaId: Long): ResponseEntity<CategoriaView> {
        val categoria: Categoria = this.categoriaService.updateCategoria(categoriaDTO.toEntity(), categoriaId)
        return ResponseEntity.status(HttpStatus.OK).body(
            CategoriaView(categoria)
        )
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{categoriaId}")
    fun deleteCategoria(@PathVariable categoriaId: Long) {
        this.categoriaService.deleteCategoria(categoriaId)
    }
    
}