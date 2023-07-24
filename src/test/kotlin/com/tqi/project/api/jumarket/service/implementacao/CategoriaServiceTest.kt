package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.entity.Categoria
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontradaException
import com.tqi.project.api.jumarket.repository.CategoriaRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.collections.ArrayList

@ExtendWith(MockitoExtension::class)
class CategoriaServiceTest {

    @Mock
    lateinit var categoriaRepository : CategoriaRepository

    @InjectMocks
    lateinit var categoriaService : CategoriaService

    @Test
    fun deve_salvar_uma_categoria_com_sucesso(){
        val categoria = cria_categoria()
        `when`(this.categoriaRepository.save(categoria)).thenReturn(categoria)
        val response = this.categoriaService.saveCategoria(categoria)
        verify(categoriaRepository, times(1)).save(categoria)
        assertEquals(categoria, response)
    }

    @Test
    fun deve_deletar_uma_categoria_com_sucesso(){
        val categoriaId = 1L
        `when`(this.categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(Categoria()))
        this.categoriaService.deleteCategoria(categoriaId)
        verify(categoriaRepository, times(1)).deleteById(categoriaId)
    }

    @Test
    fun deve_listar_categorias_com_sucesso(){
        val categorias : List<Categoria> = ArrayList()
        `when`(this.categoriaRepository.findAll()).thenReturn(categorias)
        val response = this.categoriaService.findAllCategorias()
        verify(categoriaRepository, times(1)).findAll()
        assertEquals(categorias, response)
    }

    @Test
    fun deve_lancar_excecao_categoria_nao_encontrada(){
        val categoriaId = 1L
        `when`(this.categoriaRepository.findById(categoriaId)).thenReturn(Optional.empty())
        assertThrows(EntidadeNaoEncontradaException::class.java) { this.categoriaService.findCategoria(1L) }
        verify(categoriaRepository, times(1)).findById(categoriaId)
    }

    @Test
    fun deve_editar_categoria_com_sucesso(){
        val categoriaOriginal = cria_categoria()
        val categoriaAtualizada = Categoria(id=1L, nomeCategoria = "Categoria de Teste Atualizada")
        val categoriaId = 1L

        `when`(this.categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(categoriaOriginal))
        `when`(this.categoriaRepository.save(categoriaAtualizada)).thenReturn(categoriaAtualizada)

        val response = this.categoriaService.updateCategoria(categoriaAtualizada, categoriaId)

        verify(categoriaRepository, times(1)).findById(categoriaId)
        verify(categoriaRepository, times(1)).save(categoriaAtualizada)

        assertEquals(response, categoriaAtualizada)
    }

//    @Test
//    @Throws(SQLIntegrityConstraintViolationException::class)
//    public fun deve_evitar_insercao_categoria_duplicada(){
//        val categoria = cria_categoria()
//        assertThrows(EntidadeNaoEncontradaException::class.java) { this.categoriaService.saveCategoria(categoria) }
//        verify(categoriaRepository, times(1)).save(categoria)
//    }
//
//    @Test
//    public fun deve_evitar_deletar_categoria_com_relacionamento(){
//
//    }


    private fun cria_categoria() : Categoria {
        return Categoria(id = 1L, nomeCategoria = "Categoria de Teste")
    }

}