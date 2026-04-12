package models
import kotlinx.serialization.Serializable
@Serializable
//per a implementar el serializer amb herencia he decidit cambia la clase abstract per sealed el canvi es que amb sealed les herencies han d'estar al mateix arxiu
//aquest canvi m'ajudara a facilitar la persistencia de dades
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

    //falta afegir el canvi al UML
    companion object {
        fun crear(tipus: String, id: String, titol: String, dificultat: Int, estadistica: String): Tasca? {
            return when (tipus) {
                "1" -> MissioNormal(id, titol, dificultat, estadistica)
                "2" -> MissioDiaria(id, titol, dificultat, estadistica)
                "3" -> MissioEspecial(id, titol, dificultat, estadistica)
                else -> null
            }
        }
    }
}