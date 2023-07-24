package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.entity.Categoria
import com.tqi.project.api.jumarket.entity.Produto
import com.tqi.project.api.jumarket.entity.Unidade
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontradaException
import com.tqi.project.api.jumarket.repository.ProdutoRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList

@ExtendWith(MockitoExtension::class)
class ProdutoServiceTest{

    @Mock
    lateinit var produtoRepository : ProdutoRepository

    @InjectMocks
    lateinit var produtoService : ProdutoService

    @Test
    fun deve_salvar_uma_produto_com_sucesso(){
        val produto = cria_produto()
        Mockito.`when`(this.produtoRepository.save(produto)).thenReturn(produto)
        val response = this.produtoService.saveProduto(produto)
        Mockito.verify(produtoRepository, Mockito.times(1)).save(produto)
        assertEquals(produto, response)
    }

    @Test
    fun deve_deletar_uma_produto_com_sucesso(){
        val produtoId = 1L
        Mockito.`when`(this.produtoRepository.findById(produtoId)).thenReturn(Optional.of(Produto()))
        this.produtoService.deleteProduto(produtoId)
        Mockito.verify(produtoRepository, Mockito.times(1)).deleteById(produtoId)
    }

    @Test
    fun deve_listar_produtos_com_sucesso(){
        val produtos : List<Produto> = ArrayList()
        Mockito.`when`(this.produtoRepository.findAll()).thenReturn(produtos)
        val response = this.produtoService.findAllProdutos()
        Mockito.verify(produtoRepository, Mockito.times(1)).findAll()
        assertEquals(produtos, response)
    }

    @Test
    fun deve_lancar_excecao_produto_nao_encontrada(){
        val produtoId = 1L
        Mockito.`when`(this.produtoRepository.findById(produtoId)).thenReturn(Optional.empty())
        assertThrows(EntidadeNaoEncontradaException::class.java) { this.produtoService.findProduto(1L) }
        Mockito.verify(produtoRepository, Mockito.times(1)).findById(produtoId)
    }

    @Test
    fun deve_editar_produto_com_sucesso(){
        val produto = cria_produto()
        val produtoId = 1L

        Mockito.`when`(this.produtoRepository.findById(produtoId)).thenReturn(Optional.of(Produto()))
        Mockito.`when`(this.produtoRepository.save(produto)).thenReturn(produto)

        val response = this.produtoService.updateProduto(produto, produtoId)

        Mockito.verify(produtoRepository, Mockito.times(1)).findById(produtoId)
        Mockito.verify(produtoRepository, Mockito.times(1)).save(produto)

        assertEquals(response, produto)
    }

//    @Test
//    @Throws(SQLIntegrityConstraintViolationException::class)
//    public fun deve_evitar_insercao_produto_duplicada(){
//        val produto = cria_produto()
//        assertThrows(EntidadeNaoEncontradaException::class.java) { this.produtoService.saveProduto(produto) }
//        verify(produtoRepository, times(1)).save(produto)
//    }
//
//    @Test
//    public fun deve_evitar_deletar_produto_com_relacionamento(){
//
//    }

    private fun cria_produto() : Produto {
        return Produto(id = 1L,
                nomeProduto = "Produto de Teste",
                precoUnitario= BigDecimal.valueOf(10),
                Unidade(id=1),
                Categoria(id=1))
    }

}