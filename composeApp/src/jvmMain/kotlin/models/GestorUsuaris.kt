package models
import core.Usuari
class GestorUsuaris (var usuariActual: Usuari? = null){
    val usuaris =  mutableListOf<Usuari>()
    //ara crea l'usuari i el guarda a la llista
    //Nota: quan hi hagi persistencia s'haura que canviar per a que carregui els que estan guardats si té.
    //"PROBLEMA?" FINS QUE L'USUARI NO INTRODUEIX LES DADES NO ES COMPROVA SI JA EXISTEIX PERO COM HI HAN TRY I CATCH . . . (CONSULTAR A CLASSE) (PASA EL MATEIX AMB TASCA)
    fun crearUsuari(id: String, nom: String): Usuari?{
        require(id.isNotEmpty()) { "L'ID no pot estar buit" }
        try {
            if (usuaris.any { it.id == id }) {
                throw IdDuplicatException("Ja existeix un usuari amb aquest ID.")
            } else {
                val usuari = Usuari(id, nom)
                usuaris.add(usuari)
                assert(usuaris.contains(usuari)) { "L'usuari no s'ha afegit correctament" }
                println("Usuari creat!")
                return usuari
            }

        } catch (e: IdDuplicatException) {
            println(e.message)
            return null
        }

    }

//nomes el mateix usuari pot eliminar la seva compta. i es demanarà confirmació
    fun eliminarUsuari(){
        print("Estàs segur que vols eliminar el teu usuari? (s/n): ")
        val confirmacio = readLine()!!.trim().lowercase()
        if (confirmacio == "s") {
            usuaris.remove(usuariActual)
            usuariActual = null
            println("Usuari eliminat.")
        } else {
            println("Operació cancel·lada.")
        }
    }

    fun iniciarSessio(id:String, nom: String){
        val usuari = usuaris.find {it. id == id && it.nom == nom}
        if (usuari != null){
            usuariActual = usuari
            //Mira totes les missions diaries que te l'usuari i "activa" la funcio reset per reiniciarla si el dia a canviat.
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
}