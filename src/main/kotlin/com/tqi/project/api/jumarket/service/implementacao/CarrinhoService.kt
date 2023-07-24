package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.dto.response.VendaView
import com.tqi.project.api.jumarket.entity.*
import com.tqi.project.api.jumarket.enums.Pagamento
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontradaException
import com.tqi.project.api.jumarket.repository.CarrinhoRepository
import com.tqi.project.api.jumarket.service.ICarrinho
import org.springframework.stereotype.Service
import java.math.BigDecimal
import javax.transaction.Transactional

@Service
class CarrinhoService(private val carrinhoRepository: CarrinhoRepository,
                      private val produtoService: ProdutoService,
                      private val vendaService: VendaService,
                      private val vendaCarrinhoService: VendaCarrinhoService) : ICarrinho {


    @Transactional
    override fun adicionarItemCarrinho(produtoId: Long, quantidade : Int) : List<Carrinho> {
        val produto = this.produtoService.findProduto(produtoId)

        val item = if(!this.carrinhoRepository.existsByProdutoId(produtoId)){
            Carrinho(quantidade=quantidade, produto=produto)
        }
        else {
            val carrinho = this.carrinhoRepository.findByProdutoId(produtoId).get()
            val quantidadeAtualizada = carrinho.quantidade + quantidade
            Carrinho(quantidade=quantidadeAtualizada, produto=produto)
        }

        this.carrinhoRepository.save(item)
        return this.carrinhoList()
    }

    @Transactional
    override fun removerItemCarrinho(produtoId: Long) : List<Carrinho> {
        val produto = this.produtoService.findProduto(produtoId)

        if(!this.carrinhoRepository.existsByProdutoId(produtoId)){
            throw EntidadeNaoEncontradaException("O produto não pode ser removido pois não está no carrinho.")
        }

        this.carrinhoRepository.deleteByProdutoId(produto.id)
        return this.carrinhoList()
    }

    @Transactional
    override fun finalizaCompra(pagamento: Pagamento) : VendaView {
        val carrinho = this.carrinhoList()

        if(carrinho.isEmpty()){
            throw EntidadeNaoEncontradaException("O carrinho está vazio! Adicione algo para finalizar a compra.")
        }

        val valorTotalCarrinho = somaValorCarrinho(carrinho)

        // Salva tabela vendas
        val venda : Venda = this.vendaService.saveVenda(valorTotalCarrinho, pagamento.ordinal.toString())

        // Salva relacionamento Venda_Carrinho
        val vendaCarrinho = this.vendaService.formataRelacionamentoVendaCarrinho(venda, carrinho)
        this.vendaCarrinhoService.saveVendaCarrinho(vendaCarrinho)

        // Formata response para o usuário
        val produtosFormatados = this.vendaCarrinhoService.formataItensVenda(vendaCarrinho)
        val resFormatada = VendaView(venda.id, valorTotalCarrinho, pagamento.name, produtosFormatados)

        // Esvazia tabela do carrinho para a próxima venda
        this.esvaziaCarrinho()
        return resFormatada
    }

    override fun carrinhoList() : List<Carrinho> {
        return this.carrinhoRepository.findAll()
    }

    @Transactional
    override fun esvaziaCarrinho(){
        return this.carrinhoRepository.deleteAll()
    }

    private fun somaValorCarrinho(carrinho : List<Carrinho>) : BigDecimal {
        return carrinho.sumOf { (it.produto.precoUnitario.times(BigDecimal(it.quantidade))) }
    }
}