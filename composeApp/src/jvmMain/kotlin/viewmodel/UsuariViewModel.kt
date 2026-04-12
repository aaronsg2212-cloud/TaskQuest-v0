package viewmodel
import persistencia.JsonRepository
import core.Usuari

class UsuariViewModel {
    private val repository = JsonRepository("usuaris.json")

    fun crearUsuari(id: String, nom: String): Usuari? {
        val usuari = Usuari(id, nom)
        repository.save(usuari)
        return usuari
    }

    fun obtenirUsuaris(): List<Usuari> {
        return repository.findAll()
    }

    fun eliminarUsuari(id: String) {
        repository.delete(id)
    }

    fun actualitzarUsuari(usuari: Usuari) {
        repository.delete(usuari.id)
        repository.save(usuari)
    }
}