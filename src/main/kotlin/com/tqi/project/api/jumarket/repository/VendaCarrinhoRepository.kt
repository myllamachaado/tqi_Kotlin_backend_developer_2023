package com.tqi.project.api.jumarket.repository

import com.tqi.project.api.jumarket.entity.VendaCarrinho
import com.tqi.project.api.jumarket.entity.VendaID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VendaCarrinhoRepository : JpaRepository<VendaCarrinho, VendaID> {
}