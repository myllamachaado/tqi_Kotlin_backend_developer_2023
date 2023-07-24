package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.entity.*
import com.tqi.project.api.jumarket.enums.Pagamento
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontradaException
import com.tqi.project.api.jumarket.repository.CarrinhoRepository
import com.tqi.project.api.jumarket.repository.ProdutoRepository
import com.tqi.project.api.jumarket.repository.VendaCarrinhoRepository
import com.tqi.project.api.jumarket.repository.VendaRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.math.BigDecimal
import java.util.*
import kotlin.collections.ArrayList


@ExtendWith(MockitoExtension::class)
class CarrinhoServiceTest{

    @Mock
    lateinit var carrinhoRepository: CarrinhoRepository
    @Mock
    lateinit var produtoRepository: ProdutoRepository
    @Mock
    lateinit var vendaRepository: VendaRepository
    @Mock
    lateinit var vendaCarrinhoRepository: VendaCarrinhoRepository
    @Mock
    lateinit var produtoService : ProdutoService
    @Mock
    lateinit var vendaService: VendaService
    @Mock
    lateinit var vendaCarrinhoService: VendaCarrinhoService

    @InjectMocks
    lateinit var carrinhoService: CarrinhoService

    @Test
    public fun deve_adicionar_um_produto_no_carrinho_com_sucesso(){
        val produto = cria_produto()
        val carrinhoItem = cria_carrinho(10, produto)
        val carrinho : List<Carrinho> = listOf(carrinhoItem)

        `when`(this.produtoService.findProduto(1L)).thenReturn(produto)
        `when`(this.carrinhoRepository.existsByProdutoId(1L)).thenReturn(false)
        `when`(this.carrinhoRepository.save(carrinhoItem)).thenReturn(carrinhoItem)
        `when`(this.carrinhoRepository.findAll()).thenReturn(carrinho)

        val response = carrinhoService.adicionarItemCarrinho(1L, 10)

        assertEquals(carrinho[0].produto, response[0].produto)
    }

    @Test
    public fun deve_atualizar_quantidade_de_um_produto_ja_adicionado_no_carrinho_com_sucesso(){
        val produto = cria_produto()
        val carrinhoItem = cria_carrinho(10, produto)
        val carrinhoAtualizado = cria_carrinho(20, produto)
        val carrinho : List<Carrinho> = listOf(carrinhoAtualizado)

        `when`(this.carrinhoRepository.findByProdutoId(1L)).thenReturn(Optional.of(carrinhoItem))
        `when`(this.produtoService.findProduto(1L)).thenReturn(produto)
        `when`(this.carrinhoRepository.existsByProdutoId(1L)).thenReturn(true)
        `when`(this.carrinhoRepository.save(carrinhoAtualizado)).thenReturn(carrinhoAtualizado)
        `when`(this.carrinhoRepository.findAll()).thenReturn(carrinho)

        val response = carrinhoService.adicionarItemCarrinho(1L, 10)

        assertEquals(carrinho[0].quantidade, response[0].quantidade)
    }

    @Test
    public fun deve_remover_produto_do_carrinho_com_sucesso() {
        val produto = cria_produto()
        val carrinho : List<Carrinho> = ArrayList()

        `when`(this.produtoService.findProduto(1L)).thenReturn(produto)
        `when`(this.carrinhoRepository.existsByProdutoId(1L)).thenReturn(true)
        `when`(this.carrinhoRepository.findAll()).thenReturn(carrinho)

        val response = carrinhoService.removerItemCarrinho(1L)

        Mockito.verify(this.carrinhoRepository, Mockito.times(1)).deleteByProdutoId(1L)

        assertEquals(carrinho, response)

    }

    @Test
    public fun deve_lancar_excecao_ao_remover_produto_que_nao_existe() {
        val produto = cria_produto()
        val carrinho : List<Carrinho> = ArrayList()

        `when`(this.produtoService.findProduto(1L)).thenReturn(produto)
        `when`(this.carrinhoRepository.existsByProdutoId(1L)).thenReturn(false)

        assertThrows(EntidadeNaoEncontradaException::class.java) { this.carrinhoService.removerItemCarrinho(1L) }

        Mockito.verify(this.carrinhoRepository, Mockito.times(0)).deleteByProdutoId(1L)
    }

    @Test
    public fun deve_somar_valor_total_carrinho_corretamente() {

        val produto1 = cria_produto_dinamico(1L, BigDecimal(10.0))
        val produto2 = cria_produto_dinamico(2L, BigDecimal(12.15))
        val produto3 = cria_produto_dinamico(3L, BigDecimal(2.99))
        val produto4 = cria_produto_dinamico(4L, BigDecimal(4.50))
        val produto5 = cria_produto_dinamico(5L, BigDecimal(35.80))

        val carrinhoItem1 = cria_carrinho(5, produto1)
        val carrinhoItem2 = cria_carrinho(5, produto2)
        val carrinhoItem3 = cria_carrinho(5, produto3)
        val carrinhoItem4 = cria_carrinho(5, produto4)
        val carrinhoItem5 = cria_carrinho(5, produto5)

        val carrinho : List<Carrinho> = listOf(carrinhoItem1, carrinhoItem2, carrinhoItem3, carrinhoItem4, carrinhoItem5)
        val valorTotalCarrinhoEsperado = carrinho.sumOf { (it.produto.precoUnitario.times(BigDecimal(it.quantidade))) }
        val venda = Venda(1L, valorTotal = valorTotalCarrinhoEsperado, formaPagamento=Pagamento.CARTAO.toString())
        val vendaCarrinho : List<VendaCarrinho> = listOf(cria_venda_carrinho())

        `when`(this.vendaService.saveVenda(valorTotalCarrinhoEsperado,Pagamento.CARTAO.ordinal.toString())).thenReturn(venda)
        `when`(this.vendaCarrinhoService.saveVendaCarrinho(vendaCarrinho)).thenReturn(vendaCarrinho)
        `when`(this.vendaService.formataRelacionamentoVendaCarrinho(venda, carrinho)).thenReturn(vendaCarrinho)
        `when`(this.carrinhoRepository.findAll()).thenReturn(carrinho)

        val vendaFinal = this.carrinhoService.finalizaCompra(Pagamento.CARTAO)
        assertEquals(vendaFinal.valorTotal, valorTotalCarrinhoEsperado)
    }

    @Test
    public fun deve_lancar_excecao_carrinho_vazio() {
        assertThrows(EntidadeNaoEncontradaException::class.java) { this.carrinhoService.finalizaCompra(Pagamento.CARTAO) }
    }

    private fun cria_produto() : Produto {
        return Produto(id = 1L,
                nomeProduto = "Produto de Teste",
                precoUnitario= BigDecimal.valueOf(10),
                Unidade(id=1, nomeUnidade="teste"),
                Categoria(id=1, nomeCategoria="teste"))
    }

    private fun cria_produto_dinamico(id : Long, precoUnitario : BigDecimal) : Produto {
        return Produto(id = id,
                nomeProduto = "Produto de Teste",
                precoUnitario= precoUnitario,
                Unidade(id=1, nomeUnidade="teste"),
                Categoria(id=1, nomeCategoria="teste"))
    }

    private fun cria_carrinho(quantidade : Int, produto : Produto) : Carrinho{
        return Carrinho(quantidade=quantidade, produto=produto)
    }

    private fun cria_venda_carrinho() : VendaCarrinho {
        return VendaCarrinho(vendaID = VendaID(Venda(id=1L),
                Produto(1L, "Produto de Teste",
                        precoUnitario = BigDecimal(1),
                        unidade = Unidade(id=1L),
                        categoria = Categoria(id=1L))),
                quantidade = 1,
                valorUnitario = BigDecimal(1))
    }

}