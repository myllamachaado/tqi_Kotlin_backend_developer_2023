package com.tqi.project.api.jumarket.repository

import com.tqi.project.api.jumarket.entity.Carrinho
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CarrinhoRepository : JpaRepository<Carrinho, Long> {
    
    fun existsByProdutoId(produtoId : Long) : Boolean
    fun findByProdutoId(produtoId : Long) : Optional<Carrinho>
    fun deleteByProdutoId(produtoId : Long)

}