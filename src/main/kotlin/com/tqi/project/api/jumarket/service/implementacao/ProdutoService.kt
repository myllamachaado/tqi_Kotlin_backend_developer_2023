package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.entity.Produto
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontradaException
import com.tqi.project.api.jumarket.repository.ProdutoRepository
import com.tqi.project.api.jumarket.service.IProduto
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ProdutoService(private val produtoRepository: ProdutoRepository) : IProduto {

    @Transactional
    override fun saveProduto(produto: Produto): Produto {
        return this.produtoRepository.save(produto)
    }

    override fun findAllProdutos(): List<Produto> {
        return this.produtoRepository.findAll()
    }

    override fun findProduto(produtoId: Long): Produto {
        return this.produtoRepository.findById(produtoId)
            .orElseThrow{ throw EntidadeNaoEncontradaException("O produto não existe.") }
    }

    @Transactional
    override fun updateProduto(produto: Produto, produtoId: Long): Produto {
        this.findProduto(produtoId)
        return this.saveProduto(Produto(produtoId, produto.nomeProduto, produto.precoUnitario,
                                            produto.unidade, produto.categoria))
    }

    @Transactional
    override fun deleteProduto(produtoId: Long) {
        this.findProduto(produtoId)
        this.produtoRepository.deleteById(produtoId)
    }

}