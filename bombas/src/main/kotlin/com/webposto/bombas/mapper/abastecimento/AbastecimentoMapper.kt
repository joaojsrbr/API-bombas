package com.webposto.bombas.mapper.abastecimento

import com.webposto.bombas.dto.request.AbastecimentoDTO
import com.webposto.bombas.models.Abastecimento
import org.mapstruct.Mapper

@Mapper
public interface AbastecimentoMapper {
    fun toDTO(abastecimento: Abastecimento): AbastecimentoDTO
    fun toModel(abastecimentoDTO: AbastecimentoDTO): Abastecimento
}