package core

import models.Tasca
import models.Estadistica

class Usuari (val id: String, val nom: String){
    //nivell i xp definida per defecte a l'usuari
    var nivell: Int = 1
    var xp: Int = 0
    //llista de tasques mutables
    val tasques = mutableListOf<Tasca>()
    //lista de estadistiques predefinides. NOTA: Es podria canviar per a que l'usuari pugui afegir estadistiques
    val estadistiques: MutableList<Estadistica> = mutableListOf(
        Estadistica("Força"),
        Estadistica("Intel·ligència"),
        Estadistica("Creativitat"),
        Estadistica("Disciplina"),
        Estadistica("Salut")

    )
    //funcio afegur tasques.
    fun afegirTasca(tasca:Tasca){
        tasques.add(tasca)
    }
    //funcio per a compeltar la tasca.
    //agafa l'ID del main i comprova si esta a la llista
    //si esta a la llista i no esta completada
    //es crida a la funció pera completar la tasca des de la classe "tasca"
    //Despres es crida a la funcio de guanyarXP per a sumar l'XP a l'usuari i que puji de nivell
    //Despres es mira a quina estadistica afecta i es modifica el valor de
    //la estaditica.
    fun completarTasca(idTasca:String){
        val tasca = tasques.find  {it.id==idTasca}
        if (tasca != null && !tasca.completada) {
            val xp = tasca.completar()
            guanyarXP(xp)
            val estadistica = estadistiques.find { it.nom == tasca.estadisticaAfectada }
            estadistica?.augmentarValor()
        } else {
            println("Tasca no trobada o ja completada.")
        }

    }

    fun eliminarTasca(idTasca: String) {
        val tasca = tasques.find { it.id == idTasca }
        if (tasca != null) {
            tasques.remove(tasca)
            println("Tasca eliminada.")
        } else {
            println("Tasca no trobada.")
        }
    }
    
    fun guanyarXP(quantitat: Int){
        xp += quantitat
        while(xp>=nivell * 100){
            xp -= nivell * 100
            nivell++
            println("Has pujat de nivell. Ara ets nivell $nivell!")
        }
        println("xp actual: $xp / ${nivell*100}")
    }
}