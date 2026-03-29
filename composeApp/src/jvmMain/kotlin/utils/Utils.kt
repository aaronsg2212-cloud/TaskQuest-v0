package utils
import core.Usuari
object Utils {

    fun mostrarTasques(usuari: Usuari) {
        if (usuari.tasques.isEmpty()) {
            println("No tens tasques.")
            return
        }
        usuari.tasques.forEach {
            println("${it.id} - ${it.titol} [Dificultat: ${it.dificultat}] [${if (it.completada) "Completada" else "Pendent"}]")
        }
    }
}

//sprint 4: filtrar por dificultar

//sprint 4: mostrar niveles de estadisticas. 