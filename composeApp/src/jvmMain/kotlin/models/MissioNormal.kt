package models

class MissioNormal(
    id: String,
    titol: String,
    dificultat: Int,
    estadisticaAfectada: String
) : Tasca(id, titol, dificultat, estadisticaAfectada) {
    override fun calcularXP():Int{
        return dificultat * 10
    }
}