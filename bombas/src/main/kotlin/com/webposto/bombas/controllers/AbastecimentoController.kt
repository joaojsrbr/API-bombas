package com.webposto.bombas.controllers

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.webposto.bombas.business.AbastecimentoBusiness
import com.webposto.bombas.models.dto.AbastecimentoDTO
import com.webposto.bombas.models.dto.AtualizarAbastecimentoDTO
import com.webposto.bombas.mapper.abastecimento.AbastecimentoMapper
import com.webposto.bombas.models.Abastecimento
import com.webposto.bombas.repositories.AbastecimentoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate

@RestController
@RequestMapping("api")
class AbastecimentoController(
    private val abastecimentoRepository: AbastecimentoRepository,
    private val abastecimentoBusiness: AbastecimentoBusiness,
    private val abastecimentoMapper: AbastecimentoMapper
) {

    @GetMapping("abastecimentos")
    fun getAllAbastecimentos() : List<AbastecimentoDTO> {
        return abastecimentoRepository.findAll().map(abastecimentoMapper::toDTO).toList()
    }

    @PostMapping("inserir")
    fun inserirAbastecimento(@RequestBody body: AbastecimentoDTO): ResponseMessage {

        val precoUnitario:BigDecimal = abastecimentoBusiness.getValorUnitario(body.codigoProduto)

        val valorAbastecimento:BigDecimal = abastecimentoBusiness.calcularValorUnitario(body.volumeAbst,precoUnitario)


        val abastecimento = Abastecimento(
            valorAbastecimento = valorAbastecimento,
            codigoBico = body.codigoBico,
            volumeAbst = body.volumeAbst,
            matrFuncionario = body.matrFuncionario,
            codigoProduto = body.codigoProduto,
            dataAbst = LocalDate.now(),
            imprimiu = "N",
            precoUnitario = precoUnitario,
        )

        abastecimentoRepository.save(abastecimento)
        return ResponseMessage("success","abastecimendo inserido com sucesso!.")

    }

    @DeleteMapping("excluir/{id}")
    fun deleteAbastecimento(@PathVariable id: Int): ResponseMessage {

        val abastecimento:Abastecimento? = abastecimentoRepository.findByIdOrNull(id)

        if(abastecimento == null){
          return  ResponseMessage("error","esse abastecimendo não existe no banco de dado.")
        }else if(abastecimento.imprimiu == "N"){
          return  ResponseMessage("error","abastecimento ainda não foi pago, não é possivel excluir.")
        }

       abastecimentoRepository.deleteById(abastecimento.controle)
       return ResponseMessage("success","abastecimendo deletado com sucesso.")

    }

    @PostMapping("baixa")
    fun baixaAbastecimento(@RequestBody atualizarAbastecimentoDTO: AtualizarAbastecimentoDTO): ResponseMessage {

        val abastecimento = abastecimentoRepository.findByIdOrNull(atualizarAbastecimentoDTO.id)


        if(abastecimento == null){
            return ResponseMessage("error","esse abastecimendo não existe no banco de dado.")
        }

        abastecimentoRepository.save(abastecimento.copy(imprimiu = atualizarAbastecimentoDTO.imprimiu))
        return ResponseMessage("success","abastecimendo atualizado com sucesso.")

    }



}

data class ResponseMessage(
    val status:String,

    val message:String,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data: Any? = null,
)  : Serializable

