package com.webposto.bombas.repositories

import com.webposto.bombas.models.Abastecimento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AbastecimentoRepository : JpaRepository<Abastecimento, Int>{
    @Query("SELECT imprimiu from abastecimentos a WHERE a.controle = ?1", nativeQuery = true)
    fun imprimirById(controle:Int)
}