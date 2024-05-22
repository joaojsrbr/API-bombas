package com.webposto.bombas.business

import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class AbastecimentoBusiness {


    fun getValorUnitario(codigoProduto: String) : BigDecimal{
        
        val produto:Produto? = Produto.entries.firstOrNull { it.toString().contains(codigoProduto.uppercase()) }

        return (produto ?: Produto.P001).preco
    }

    fun calcularValorUnitario(litrosS:BigDecimal, preco: BigDecimal) : BigDecimal {
        return litrosS.multiply(preco)
    }


}


private enum class Produto(val preco:BigDecimal) {
    P001(2.toBigDecimal()),
    P002(5.toBigDecimal()),
    P003(8.toBigDecimal())
}