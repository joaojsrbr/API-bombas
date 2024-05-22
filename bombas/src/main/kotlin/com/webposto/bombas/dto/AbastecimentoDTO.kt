package com.webposto.bombas.dto



import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

data class AbastecimentoDTO(

    val controle: Int,

    val codigoBico: String,

    val valorAbastecimento: BigDecimal,

    val codigoProduto: String,

    val matrFuncionario: String?,

    var imprimiu: String?,

    var volumeAbst: BigDecimal,

    var dataAbst: LocalDate,

    var precoUnitario: BigDecimal,
): Serializable