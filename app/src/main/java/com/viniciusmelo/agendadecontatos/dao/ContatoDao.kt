package com.viniciusmelo.agendadecontatos.dao

import androidx.room3.Dao
import androidx.room3.Insert
import com.viniciusmelo.agendadecontatos.model.Contato

@Dao
interface ContatoDao {

    @Insert
    fun gravar(contato: Contato)

}