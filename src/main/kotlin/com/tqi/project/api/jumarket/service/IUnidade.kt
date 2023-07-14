package com.tqi.project.api.jumarket.service

import com.tqi.project.api.jumarket.entity.Unidade

interface IUnidade {
    fun saveUnidade(unidade : Unidade): Unidade
    fun findAllUnidades(): List<Unidade>
    fun findUnidade(unidadeId: Long): Unidade
    fun updateUnidade(unidade : Unidade, unidadeId : Long): Unidade
    fun deleteUnidade(unidadeId: Long)
}