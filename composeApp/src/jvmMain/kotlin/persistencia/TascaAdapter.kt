package persistencia
import com.google.gson.*
import models.*
import java.lang.reflect.Type

// TascaAdapter és un adaptador personalitzat de Gson per gestionar la jerarquia d'herència de Tasca.
// Gson no sap com serialitzar/deserialitzar classes abstractes per defecte.
// Serialize: afegeix el camp "tipus" al JSON per saber quin tipus de Tasca és.
// Deserialize: llegeix el camp "tipus" i crea l'objecte correcte (MissioNormal, MissioDiaria, MissioEspecial).

class TascaAdapter : JsonDeserializer<Tasca>, JsonSerializer<Tasca> {

    override fun serialize(src: Tasca, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val jsonObject = context.serialize(src).asJsonObject
        jsonObject.addProperty("tipus", src::class.simpleName)
        return jsonObject
    }

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Tasca {
        val jsonObject = json.asJsonObject
        val tipus = jsonObject.get("tipus").asString
        return when (tipus) {
            "MissioNormal" -> context.deserialize(json, MissioNormal::class.java)
            "MissioDiaria" -> context.deserialize(json, MissioDiaria::class.java)
            "MissioEspecial" -> context.deserialize(json, MissioEspecial::class.java)
            else -> throw JsonParseException("Tipus desconegut: $tipus")
        }
    }
}