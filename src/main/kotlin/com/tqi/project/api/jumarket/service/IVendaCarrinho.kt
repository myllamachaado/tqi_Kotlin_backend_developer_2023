package com.tqi.project.api.jumarket.service

import com.tqi.project.api.jumarket.dto.response.ProdutoFormatadoVendaView
import com.tqi.project.api.jumarket.entity.VendaCarrinho

interface IVendaCarrinho {

    fun saveVendaCarrinho(vendaCarrinho: List<VendaCarrinho>) : List<VendaCarrinho>
    fun formataItensVenda(itens : List<VendaCarrinho>) : List<ProdutoFormatadoVendaView>
}