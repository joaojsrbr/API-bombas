package com.webposto.bombas.models.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.Data
import java.io.Serializable

@Data
class BaixaAbastecimentoDTO(

    @JsonProperty(value = "controle")
    val id:Int,

    var imprimiu: String = "Y",
)