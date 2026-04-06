package utils
import core.Usuari
import models.Tasca
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
    fun validarId(id: String): Boolean {
        val regex = "^[0-9]+$".toRegex()
        return regex.matches(id)
    }

    fun validarNom(nom: String): Boolean {
        val regex = "^[a-zA-Z][a-zA-Z0-9]*$".toRegex()
        return regex.matches(nom)
    }
    fun demanarId(): String {
        var id: String
        do {
            id = readLine()!!.trim().lowercase()
            if (id.isEmpty()) println("L'ID no pot estar buit.")
            else if (!validarId(id)) println("L'ID només pot contenir números.")
        } while (id.isEmpty() || !validarId(id))
        return id
    }

    fun demanarINoms(): String {
        var noms: String
        do {
            noms = readLine()!!.trim().lowercase()
            if (noms.isEmpty()) println("no pot estar buit.")
            else if (!validarNom(noms)) println("No pot començar amb un número.")
        } while (noms.isEmpty() || !validarNom(noms))
        return noms
    }

}




//sprint 4: mostrar niveles de estadisticas. 