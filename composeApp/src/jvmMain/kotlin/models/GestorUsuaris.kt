package models
import core.Usuari
class GestorUsuaris (var usuariActual: Usuari? = null){
    val usuaris =  mutableListOf<Usuari>()
    //ara crea l'usuari i el guarda a la llista
    //Nota: quan hi hagi persistencia hara que canviar per a que carregui els que estan guardats si té.
    fun crearUsuari(id: String, nom: String): Usuari{
        val usuari = Usuari(id, nom)
        usuaris.add(usuari)
        return usuari
    }

//nomes el mateix usuari pot eliminar la seva compta. i es demanarà confirmació
    fun eliminarUsuari(){
        print("Estàs segur que vols eliminar el teu usuari? (s/n): ")
        val confirmacio = readLine()
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