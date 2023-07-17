package com.tqi.project.api.jumarket.repository

import com.tqi.project.api.jumarket.entity.Carrinho
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarrinhoRepository : JpaRepository<Carrinho, Long>{
    fun findByItemCompraProdutoId(produtoId : Long) : Carrinho

}