package com.webposto.bombas.models.dto



import com.fasterxml.jackson.annotation.JsonInclude
import lombok.Data
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

@Data
data class AbastecimentoDTO(

    val codigoProduto: String,

    val codigoBico: String,

    val matrFuncionario: String,

    val volumeAbst: BigDecimal,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val valorAbastecimento: BigDecimal,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var imprimiu: String,

)