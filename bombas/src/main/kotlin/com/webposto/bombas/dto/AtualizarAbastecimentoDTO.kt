package com.webposto.bombas.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

class AtualizarAbastecimentoDTO(
    @JsonProperty(value = "controle")
    val id:Int,
    var imprimiu: String,
) : Serializable