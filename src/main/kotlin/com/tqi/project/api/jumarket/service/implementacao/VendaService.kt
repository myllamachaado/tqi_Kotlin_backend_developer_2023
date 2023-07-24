package com.tqi.project.api.jumarket.service.implementacao

import com.tqi.project.api.jumarket.entity.*
import com.tqi.project.api.jumarket.repository.VendaRepository
import com.tqi.project.api.jumarket.service.IVenda
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
class VendaService(private val vendaRepository: VendaRepository) : IVenda {

    @Transactional
    override fun saveVenda(valorTotal : BigDecimal, formaPagamento : String) : Venda {
        val venda = Venda(valorTotal = valorTotal, formaPagamento = formaPagamento)
        return this.vendaRepository.save(venda)
    }

    override fun formataRelacionamentoVendaCarrinho(venda : Venda,
                                                    itensCarrinho : List<Carrinho>)
                                                    : List<VendaCarrinho> {
        return itensCarrinho.stream().map { carrinho : Carrinho ->
            VendaCarrinho(vendaID = VendaID(Venda(venda.id),
                    Produto(carrinho.produto.id, carrinho.produto.nomeProduto,
                            precoUnitario = carrinho.produto.precoUnitario,
                            unidade = carrinho.produto.unidade,
                            categoria = carrinho.produto.categoria)),
                    quantidade = carrinho.quantidade,
                    valorUnitario = carrinho.produto.precoUnitario) }
                .collect(Collectors.toList())
    }

}