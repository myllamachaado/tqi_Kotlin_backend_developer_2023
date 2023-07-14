package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.entity.Unidade
import com.tqi.project.api.jumarket.exception.exceptions.EntidadeNaoEncontradaException
import com.tqi.project.api.jumarket.repository.UnidadeRepository
import com.tqi.project.api.jumarket.service.IUnidade
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UnidadeService (private val unidadeRepository: UnidadeRepository) : IUnidade {

    @Transactional
    override fun saveUnidade(unidade: Unidade): Unidade {
        return this.unidadeRepository.save(unidade)
    }

    override fun findAllUnidades(): List<Unidade> {
        return this.unidadeRepository.findAll()
    }

    override fun findUnidade(unidadeId: Long): Unidade {
        return this.unidadeRepository.findById(unidadeId)
            .orElseThrow{throw EntidadeNaoEncontradaException("A unidade não existe.") }
    }

    @Transactional
    override fun updateUnidade(unidade: Unidade, unidadeId: Long): Unidade {
        this.findUnidade(unidadeId)
        return this.saveUnidade(Unidade(unidadeId, unidade.nomeUnidade))
    }

    @Transactional
    override fun deleteUnidade(unidadeId: Long) {
        this.findUnidade(unidadeId)
        this.unidadeRepository.deleteById(unidadeId)
    }

}