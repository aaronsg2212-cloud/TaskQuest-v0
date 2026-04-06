package models
import java.time.LocalDate

class MissioDiaria(
    id: String,
    titol: String,
    dificultat: Int,
    estadisticaAfectada: String
) : Tasca(id, titol, dificultat, estadisticaAfectada) {

    var repeticions: Int = 0
    var ultimaCompletacio: LocalDate? = null

    override fun calcularXP(): Int {
        return dificultat * 8
    }

    override fun completar(): Int{
        ultimaCompletacio = LocalDate.now()
        repeticions++
        return super.completar()
    }
    fun reset(){
        if (ultimaCompletacio != null && ultimaCompletacio != LocalDate.now()) {
            completada = false
        }
    }
}