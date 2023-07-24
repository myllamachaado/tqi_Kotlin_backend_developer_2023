package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.dto.response.ProdutoFormatadoVendaView
import com.tqi.project.api.jumarket.entity.VendaCarrinho
import com.tqi.project.api.jumarket.repository.VendaCarrinhoRepository
import com.tqi.project.api.jumarket.service.IVendaCarrinho
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.stream.Collectors

@Service
class VendaCarrinhoService(private val vendaCarrinhoRepository: VendaCarrinhoRepository) : IVendaCarrinho {

    override fun saveVendaCarrinho(vendaCarrinho: List<VendaCarrinho>) : List<VendaCarrinho> {
        return this.vendaCarrinhoRepository.saveAll(vendaCarrinho)
    }

    override fun formataItensVenda(itens : List<VendaCarrinho>) : List<ProdutoFormatadoVendaView> {
        return itens.stream().map { it: VendaCarrinho -> ProdutoFormatadoVendaView(it.vendaID?.produto,
                it.quantidade, it.vendaID?.produto?.precoUnitario?.times(BigDecimal(it.quantidade))) }
                .collect(Collectors.toList())
    }

}