package com.webposto.bombas.mapper.abastecimento

import com.webposto.bombas.dto.AbastecimentoDTO
import com.webposto.bombas.models.Abastecimento
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper(componentModel = "spring")
interface AbastecimentoMapper {
    fun toDTO(abastecimento: Abastecimento): AbastecimentoDTO
    fun toModel(adicionarAbastecimentoDTO: AbastecimentoDTO): Abastecimento

    companion object {
        val INSTANCE: AbastecimentoMapper = Mappers.getMapper(AbastecimentoMapper::class.java)
    }
}

