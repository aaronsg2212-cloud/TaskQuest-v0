package models
import core.Usuari
import viewmodel.UsuariViewModel
class GestorUsuaris(
    var usuariActual: Usuari? = null,
    private val viewModel: UsuariViewModel = UsuariViewModel()
) {
    fun crearUsuari(id: String, nom: String): Usuari? {
        require(id.isNotEmpty()) { "L'ID no pot estar buit" }
        try {
            val usuaris = viewModel.obtenirUsuaris()
            if (usuaris.any { it.id == id }) {
                throw IdDuplicatException("Ja existeix un usuari amb aquest ID.")
            } else {
                val usuari = viewModel.crearUsuari(id, nom)
                println("Usuari creat!")
                return usuari
            }
        } catch (e: IdDuplicatException) {
            println(e.message)
            return null
        }
    }

//nomes el mateix usuari pot eliminar la seva compta. i es demanarà confirmació
fun eliminarUsuari() {
    print("Estàs segur que vols eliminar el teu usuari? (s/n): ")
    val confirmacio = readLine()!!.trim().lowercase()
    if (confirmacio == "s") {
        usuariActual?.let { viewModel.eliminarUsuari(it.id) }
        usuariActual = null
        println("Usuari eliminat.")
    } else {
        println("Operació cancel·lada.")
    }
}

    fun iniciarSessio(id: String, nom: String) {
        val usuari = viewModel.obtenirUsuaris().find { it.id == id && it.nom == nom }
        if (usuari != null) {
            usuariActual = usuari
            usuari.tasques.filterIsInstance<MissioDiaria>().forEach { it.reset() }
            println("Benvingut, ${usuari.nom}!")
        } else {
            println("Usuari no trobat.")
        }
    }

    fun canviarUsuari(){
        usuariActual= null
        println("sessió tancada.")
    }
// getter per a que el viewmodel segueixi sent privat i no es trenqui la estructira
    fun actualitzarUsuari(usuari: Usuari) {
        viewModel.actualitzarUsuari(usuari)
    }
}