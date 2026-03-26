package models

abstract class Tasca (val id: String, val titol: String, val dificultat: Int, val estadisticaAfectada: String) {
    var completada: Boolean = false
    fun completar(){

    }

    fun calcularXP(){

    }
}