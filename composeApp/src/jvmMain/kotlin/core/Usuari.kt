package core

import models.Tasca
import models.Estadistica
import models.IdDuplicatException
import models.EstadisticaNoExisteixException

class Usuari (val id: String, val nom: String){
    //nivell i xp definida per defecte a l'usuari
    var nivell: Int = 1
    var xp: Int = 0
    //llista de tasques mutables
    val tasques = mutableListOf<Tasca>()
    //lista de estadistiques predefinides. NOTA: Es podria canviar per a que l'usuari pugui afegir estadistiques
    val estadistiques: MutableList<Estadistica> = mutableListOf(
        Estadistica("força"),
        Estadistica("intel·ligencia"),
        Estadistica("creativitat"),
        Estadistica("disciplina"),
        Estadistica("salut")

    )
    //funcio afegir tasques.
    fun afegirTasca(tasca: Tasca) {
        require(tasca.id.isNotEmpty()) { "L'ID no pot estar buit" }
        require(tasca.dificultat in 1..10) { "La dificultat ha de ser entre 1 i 10" }
        try {
            if (tasques.any { it.id == tasca.id }) {
                throw IdDuplicatException("Ja existeix una tasca amb aquest ID.")
            } else {
                tasques.add(tasca)
                assert(tasques.contains(tasca)) { "La tasca no s'ha afegit correctament" }
                println("Tasca afegida!")
            }
            if (estadistiques.none { it.nom.lowercase() == tasca.estadisticaAfectada.lowercase() }) {
                throw EstadisticaNoExisteixException("L'estadística no existeix.")
            }
        } catch (e: IdDuplicatException) {
            println(e.message)
        } catch (e: EstadisticaNoExisteixException) {
            println(e.message)
        }
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

    //filtrar les tasques per dificultat amb rangs per a facilitar la búsqueda.
    fun filtrarPerDificultat(rang: String){
        val resultat = when (rang) {
            "1" -> tasques.filter { it.dificultat in 1..3 }
            "2" -> tasques.filter { it.dificultat in 4..6 }
            "3" -> tasques.filter { it.dificultat in 7..10 }
            else -> emptyList()
        }
        if (resultat.isEmpty()) {
            println("No hi ha tasques amb aquesta dificultat.")
        } else {
            resultat.forEach { println("${it.id} - ${it.titol} [Dificultat: ${it.dificultat}]") }
        }

    }

}