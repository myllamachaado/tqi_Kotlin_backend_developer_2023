package com.tqi.project.api.jumarket.repository

import com.tqi.project.api.jumarket.entity.Unidade
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UnidadeRepository: JpaRepository<Unidade, Long> {
}