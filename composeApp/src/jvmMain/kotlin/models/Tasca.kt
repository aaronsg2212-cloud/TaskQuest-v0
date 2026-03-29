package models

abstract class Tasca (val id: String, val titol: String, val dificultat: Int, val estadisticaAfectada: String) {
    var completada: Boolean = false
//marca la tasca com completada i crida a calcularXP.
    //open fa que la funcio es pugui sobreescriure amb override.
    open fun completar():Int {
        completada = true
        println("Tasca completada. Felicitats! Bon treball.")
        return calcularXP()
    }
//calcula l'experiencia guanyada depenent de la dificultat de la tasca
    abstract fun calcularXP(): Int

}