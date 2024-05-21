package com.webposto.bombas.mapper.abastecimento

import com.webposto.bombas.dto.request.AbastecimentoDTO
import com.webposto.bombas.models.Abastecimento

class AbastecimentoMapperImpl : AbastecimentoMapper  {
    override fun toDTO(abastecimento: Abastecimento): AbastecimentoDTO {
       val dto: AbastecimentoDTO = AbastecimentoDTO(
           controle = abastecimento.controle,
           codigoBico = abastecimento.codigoBico,
           valorAbastecimento = abastecimento.valorAbastecimento,
           codigoProduto = abastecimento.codigoProduto,
           matrFuncionario = abastecimento.matrFuncionario,
           volumeAbst = abastecimento.volumeAbst,
           dataAbst = abastecimento.dataAbst,
           precoUnitario = abastecimento.precoUnitario,
           imprimiu = abastecimento.imprimiu,
           )
        return dto;

    }

    override fun toModel(abastecimentoDTO: AbastecimentoDTO): Abastecimento {

        val model: Abastecimento = Abastecimento()
        return model;
    }
}