package com.webposto.bombas.controllers

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.webposto.bombas.dto.AbastecimentoDTO
import com.webposto.bombas.dto.AtualizarAbastecimentoDTO
import com.webposto.bombas.dto.DeletarAbastecimentoDTO
import com.webposto.bombas.mapper.abastecimento.AbastecimentoMapper
import com.webposto.bombas.models.Abastecimento
import com.webposto.bombas.repositories.AbastecimentoRepository
import lombok.Data
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import java.io.Serializable
import java.util.Objects

@RestController
@RequestMapping("api")
class AbastecimentoController(
    private val abastecimentoRepository: AbastecimentoRepository,
    private val abastecimentoMapper: AbastecimentoMapper =  AbastecimentoMapper.INSTANCE,
) {

    @GetMapping("abastecimentos")
    fun getAllAbastecimentos() : List<AbastecimentoDTO> {
        return abastecimentoRepository.findAll().map(abastecimentoMapper::toDTO).toList()
    }

    @GetMapping("abastecimento")
    fun getByParamId( @RequestParam id:Int) : AbastecimentoDTO {

        val abastecimento:Abastecimento =  abastecimentoRepository.getReferenceById(id)

        return abastecimentoMapper.toDTO(abastecimento)

    }

    @GetMapping("abastecimento/{id}")
    fun getByPathId(@PathVariable id: Int) : AbastecimentoDTO {
        val abastecimento:Abastecimento =  abastecimentoRepository.getReferenceById(id)

        return abastecimentoMapper.toDTO(abastecimento)
    }


    @PostMapping("abastecimento/criar")
    fun adicionarAbastecimento(@RequestBody abastecimentoDTO: AbastecimentoDTO): ResponseMessage {

        if(abastecimentoRepository.existsById(abastecimentoDTO.controle )){
            return ResponseMessage("error","para atualizar user o entrypoint /atualizar.")
        }

        val abastecimento: Abastecimento = abastecimentoMapper.toModel(abastecimentoDTO)

        abastecimentoRepository.save(abastecimento)

        return ResponseMessage("success","abastecimendo criado com sucesso.")

    }

    @PostMapping("abastecimento/deletar")
    fun deleteAbastecimento(@RequestBody deletarAbastecimentoDTO: DeletarAbastecimentoDTO): ResponseMessage {

        if(!abastecimentoRepository.existsById(deletarAbastecimentoDTO.id)){
            return ResponseMessage("error","esse abastecimendo não existe no banco de dado.")
        }

       val imprimir = abastecimentoRepository.findById(deletarAbastecimentoDTO.id)


        return if (imprimir.get().imprimiu != null && imprimir.get().imprimiu!!.contains("Y")){
            abastecimentoRepository.deleteById(imprimir.get().controle)
            ResponseMessage("success","abastecimendo deletado com sucesso.")
        }else{
            ResponseMessage("error","abastecimento não baixado.")
        }

    }

    @PostMapping("abastecimento/atualizar")
    fun updateAbastecimento(@RequestBody atualizarAbastecimentoDTO: AtualizarAbastecimentoDTO): ResponseMessage {

//        val abastecimento: Abastecimento = abastecimentoMapper.toModel(abastecimentoDTO)

//        if(!(abastecimentoRepository.existsById(atualizarAbastecimentoDTO.id))){
//            return ResponseMessage("error","esse abastecimendo não existe no banco de dado.")
//        }

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

