package com.viniciusmelo.agendadecontatos

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.viniciusmelo.agendadecontatos.constantes.Constantes
import com.viniciusmelo.agendadecontatos.dao.ContatoDao
import com.viniciusmelo.agendadecontatos.model.Contato

@Database(entities = [Contato::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contatoDao(): ContatoDao

    @Volatile private var INSTANCE: AppDataBase ?= null

    fun getInstance(context: Context): AppDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                Constantes.DB_CONTATOS
            ).build()

            INSTANCE = instance
            instance

        }
    }

}