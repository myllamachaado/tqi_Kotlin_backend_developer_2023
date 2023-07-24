package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.entity.Unidade
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontradaException
import com.tqi.project.api.jumarket.repository.UnidadeRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.collections.ArrayList

@ExtendWith(MockitoExtension::class)
class UnidadeServiceTest{

    @Mock
    lateinit var unidadeRepository : UnidadeRepository

    @InjectMocks
    lateinit var unidadeService : UnidadeService

    @Test
    fun deve_salvar_uma_unidade_com_sucesso(){
        val unidade = cria_unidade()
        `when`(this.unidadeRepository.save(unidade)).thenReturn(unidade)
        val response = this.unidadeService.saveUnidade(unidade)
        verify(unidadeRepository, Mockito.times(1)).save(unidade)
        assertEquals(unidade, response)
    }

    @Test
    fun deve_deletar_uma_unidade_com_sucesso(){
        val unidadeId = 1L
        `when`(this.unidadeRepository.findById(unidadeId)).thenReturn(Optional.of(Unidade()))
        this.unidadeService.deleteUnidade(unidadeId)
        verify(unidadeRepository, Mockito.times(1)).deleteById(unidadeId)
    }

    @Test
    fun deve_listar_unidades_com_sucesso(){
        val unidades : List<Unidade> = ArrayList()
        `when`(this.unidadeRepository.findAll()).thenReturn(unidades)
        val response = this.unidadeService.findAllUnidades()
        verify(unidadeRepository, Mockito.times(1)).findAll()
        assertEquals(unidades, response)
    }

    @Test
    fun deve_lancar_excecao_unidade_nao_encontrada(){
        val unidadeId = 1L
        `when`(this.unidadeRepository.findById(unidadeId)).thenReturn(Optional.empty())
        assertThrows(EntidadeNaoEncontradaException::class.java) { this.unidadeService.findUnidade(1L) }
        verify(unidadeRepository, Mockito.times(1)).findById(unidadeId)
    }

    @Test
    fun deve_editar_unidade_com_sucesso(){
        val unidadeOriginal = cria_unidade()
        val unidadeAtualizada = Unidade(id=1L, nomeUnidade = "Unidade de Teste Atualizada")

        val unidadeId = 1L

        `when`(this.unidadeRepository.findById(unidadeId)).thenReturn(Optional.of(unidadeOriginal))
        `when`(this.unidadeRepository.save(unidadeAtualizada)).thenReturn(unidadeAtualizada)

        val response = this.unidadeService.updateUnidade(unidadeAtualizada, unidadeId)

        verify(unidadeRepository, Mockito.times(1)).findById(unidadeId)
        verify(unidadeRepository, Mockito.times(1)).save(unidadeAtualizada)

        assertEquals(response, unidadeAtualizada)
    }

//    @Test
//    @Throws(SQLIntegrityConstraintViolationException::class)
//    public fun deve_evitar_insercao_unidade_duplicada(){
//        val unidade = cria_unidade()
//        assertThrows(EntidadeNaoEncontradaException::class.java) { this.unidadeService.saveUnidade(unidade) }
//        verify(unidadeRepository, times(1)).save(unidade)
//    }
//
//    @Test
//    public fun deve_evitar_deletar_unidade_com_relacionamento(){
//
//    }

    private fun cria_unidade() : Unidade {
        return Unidade(id = 1L, nomeUnidade = "Unidade de Teste")
    }

}