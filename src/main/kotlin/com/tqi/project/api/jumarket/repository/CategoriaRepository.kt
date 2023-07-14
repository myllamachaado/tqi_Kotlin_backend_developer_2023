package com.tqi.project.api.jumarket.repository

import com.tqi.project.api.jumarket.entity.Categoria
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaRepository : JpaRepository<Categoria, Long> {

}