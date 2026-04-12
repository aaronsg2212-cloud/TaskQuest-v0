package persistencia
import com.google.gson.*
import models.*
import java.lang.reflect.Type

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