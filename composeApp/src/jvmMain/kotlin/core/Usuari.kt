package core

import models.Tasca
import models.Estadistica

class Usuari (val id: String, val nom: String, val nivell: Int, val xp: Int){

    val tasques = mutableListOf<Tasca>()
    val estadistiques = mutableListOf<Estadistica>()

    fun afegirTasca(){

    }

    fun completaTasca(){

    }

    fun guanyarXP(){

    }
}