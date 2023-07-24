package com.tqi.project.api.jumarket.repository

import com.tqi.project.api.jumarket.entity.Venda
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VendaRepository : JpaRepository<Venda, Long>{

}