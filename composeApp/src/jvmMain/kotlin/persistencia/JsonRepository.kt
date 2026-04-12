package persistencia
import com.google.gson.*
import com.google.gson.reflect.TypeToken
import com.google.gson.GsonBuilder
import java.io.File
import java.lang.reflect.Type
import java.time.LocalDate
import core.Usuari
import models.Tasca

class JsonRepository(
    private val fileName: String
) : Repository<Usuari> {

    private val gson = GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(Tasca::class.java, TascaAdapter())
        .registerTypeAdapter(LocalDate::class.java, object : JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
            override fun serialize(src: LocalDate, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
                return JsonPrimitive(src.toString())
            }
            override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDate {
                return LocalDate.parse(json.asString)
            }
        })
        .create()

    private val file = File(fileName)

    override fun save(item: Usuari) {
        try {
            val llista = findAll().toMutableList()
            llista.add(item)
            val json = gson.toJson(llista)
            file.writeText(json)
        } catch (e: Exception) {
            println("Error al guardar el fitxer: ${e.message}")
        }
    }

    override fun findAll(): List<Usuari> {
        return try {
            if (!file.exists()) return emptyList()
            val json = file.readText()
            val type = object : TypeToken<List<Usuari>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            println("Error al llegir el fitxer: ${e.message}")
            emptyList()
        }
    }

    override fun delete(id: String) {
        try {
            val llista = findAll().toMutableList()
            llista.removeIf { it.id == id }
            val json = gson.toJson(llista)
            file.writeText(json)
        } catch (e: Exception) {
            println("Error al eliminar l'usuari: ${e.message}")
        }
    }
}