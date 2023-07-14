package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.entity.Produto
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontrada
import com.tqi.project.api.jumarket.repository.ProdutoRepository
import com.tqi.project.api.jumarket.service.IProduto
import org.springframework.stereotype.Service

@Service
class ProdutoService(private val produtoRepository: ProdutoRepository) : IProduto {

    override fun saveProduto(produto: Produto): Produto {
        return this.produtoRepository.save(produto)
    }

    override fun findAllProdutos(): List<Produto> {
        return this.produtoRepository.findAll()
    }

    override fun findProduto(produtoId: Long): Produto {
        return this.produtoRepository.findById(produtoId)
            .orElseThrow{throw EntidadeNaoEncontrada("O produto não existe.") }
    }

    override fun updateProduto(produto: Produto, produtoId: Long): Produto {
        TODO("Not yet implemented")
    }

    override fun deleteProduto(produtoId: Long) {
        this.findProduto(produtoId)
        this.produtoRepository.deleteById(produtoId)
    }

}