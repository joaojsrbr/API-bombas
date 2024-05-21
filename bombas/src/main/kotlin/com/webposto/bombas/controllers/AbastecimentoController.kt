package com.webposto.bombas.controllers

import com.webposto.bombas.models.Abastecimento
import com.webposto.bombas.repositories.AbastecimentoRepository
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.*
import java.io.Serializable

@RestController
@RequestMapping("abastecimento")
class AbastecimentoController(
    private val abastecimentoRepository: AbastecimentoRepository
) {

    @GetMapping()
    fun getAll() : List<Abastecimento> {
     return  abastecimentoRepository.findAll()
    }

    @GetMapping("{id}")
    fun getById(@PathVariable  id:Int) : Abastecimento {
        return  abastecimentoRepository.getReferenceById(id)
    }

    @PostMapping("criar")
    fun putAbastecimento(@RequestBody abastecimento: Abastecimento): ResponseMessage {

        if(abastecimentoRepository.existsById(abastecimento.controle)){
            return ResponseMessage( """
            Objecto ja existe.""".trimIndent())
        }

        abastecimentoRepository.save(abastecimento)


        return  ResponseMessage( """Objecto criado com sucesso.""".trimIndent())


    }

    @PostMapping("delete")
    fun deleteAbastecimento(@RequestBody  id:Int): ResponseMessage {

        if(!abastecimentoRepository.existsById(id)){
            return ResponseMessage("""Não existe esse abastecimendo no banco de dado.""".trimIndent())
        }

        abastecimentoRepository.deleteById(id)


        return  ResponseMessage( """
            Abastecimento excruido com sucesso.""".trimIndent())


    }



}

data class ResponseMessage(val message:String) : Serializable {}

