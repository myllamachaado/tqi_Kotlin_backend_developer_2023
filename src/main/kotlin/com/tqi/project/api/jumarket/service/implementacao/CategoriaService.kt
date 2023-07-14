package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.entity.Categoria
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontrada
import com.tqi.project.api.jumarket.repository.CategoriaRepository
import com.tqi.project.api.jumarket.service.ICategoria
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class CategoriaService(private val categoriaRepository: CategoriaRepository) : ICategoria {

    @Transactional
    override fun saveCategoria(categoria: Categoria): Categoria {
        return this.categoriaRepository.save(categoria)
    }

    override fun findAllCategorias(): List<Categoria> {
        return this.categoriaRepository.findAll()
    }

    override fun findCategoria(categoriaId: Long): Categoria {
        return this.categoriaRepository.findById(categoriaId)
            .orElseThrow{throw EntidadeNaoEncontrada("A categoria não existe.") }

    }

    @Transactional
    override fun updateCategoria(categoria: Categoria, categoriaId: Long): Categoria {
        this.findCategoria(categoriaId)
        return this.saveCategoria(Categoria(id = categoriaId, nomeCategoria = categoria.nomeCategoria)
        )
    }

    @Transactional
    override fun deleteCategoria(categoriaId: Long) {
        this.findCategoria(categoriaId)
        this.categoriaRepository.deleteById(categoriaId)
    }


}