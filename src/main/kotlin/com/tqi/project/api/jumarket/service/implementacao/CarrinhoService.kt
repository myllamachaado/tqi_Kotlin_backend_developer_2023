package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.entity.Carrinho
import com.tqi.project.api.jumarket.entity.ItemCompra
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontradaException
import com.tqi.project.api.jumarket.repository.CarrinhoRepository
import com.tqi.project.api.jumarket.service.ICarrinho
import org.springframework.stereotype.Service
import java.util.*


@Service
class CarrinhoService(private val carrinhoRepository: CarrinhoRepository,
                      private val produtoService: ProdutoService) : ICarrinho {

    /**
     * Adiciona um novo item no carrinho caso ele não esteja na lista.
     * Atualiza um item já adicionado anteriormente
     * @return Lista de itens do carrinho atualizada após as mudanças.
     */
     override fun adicionarCarrinho(produtoId : Long, quantidade: Int): List<Carrinho> {

         val produto = this.produtoService.findProduto(produtoId)
         val itemEstaNoCarrinho = this.findByItemCompraProdutoId(produto.id)

         val item = if(itemEstaNoCarrinho == null) {
             Carrinho(itemCompra=ItemCompra(produto, quantidade, produto.precoUnitario*quantidade))
         }
         else{
             val updateQtd = itemEstaNoCarrinho.itemCompra!!.quantidade
             val updateTotal = produto.precoUnitario * updateQtd
             Carrinho(itemEstaNoCarrinho.id, ItemCompra(produto, updateQtd, updateTotal))
         }

         this.carrinhoRepository.save(item)
         return this.listaItensCarrinho()
    }

    /**
     * Remove um item do carrinho caso ele esteja no carrinho
     * Mostra uma mensagem de erro caso o usuário tente excluir um item que não está no carrinho
     * @return Lista de itens do carrinho atualizada após as mudanças.
     */
    override fun removerCarrinho(produtoId: Long): List<Carrinho> {
        val itemEstaNoCarrinho = this.findByItemCompraProdutoId(produtoId)
        if(itemEstaNoCarrinho == null){
            throw EntidadeNaoEncontradaException("O produto não está no carrinho portanto não pode ser removido.")
        }
        this.carrinhoRepository.deleteById(itemEstaNoCarrinho.id)
        return this.listaItensCarrinho()
    }

    /**
     * Valida se o item está no carrinho.
     * Em [adicionarCarrinho] serve para verificar se é uma adição ou atualização de item
     * Em [removerCarrinho] valida se o item está na lista antes de remover
     * @return O [ItemCompra] caso o produto esteja na lista e [null] caso contrário
     */
    fun findByItemCompraProdutoId(produtoId: Long) : Carrinho? {
        return this.carrinhoRepository.findByItemCompraProdutoId(produtoId)
    }

    /**
     * @return Lista de itens do carrinho
     */
    override fun listaItensCarrinho(): List<Carrinho> {
        return this.carrinhoRepository.findAll()
    }

}