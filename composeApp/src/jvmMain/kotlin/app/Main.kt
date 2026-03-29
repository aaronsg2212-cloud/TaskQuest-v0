package app
import models.*
import core.Usuari
import utils.Utils

fun main() {
    val gestor = GestorUsuaris()

    while (true) {
        if (gestor.usuariActual == null) {
            menuPrincipal(gestor)
        } else {
            menuUsuari(gestor)
        }
    }
}

fun menuPrincipal(gestor: GestorUsuaris) {
    println("\n=== TASKQUEST ===")
    println("1. Crear usuari")
    println("2. Iniciar sessió")
    println("3. Sortir")
    print("Escull una opció: ")

    when (readLine()) {
        "1" -> crearUsuari(gestor)
        "2" -> iniciarSessio(gestor)
        "3" -> {
            println("Fins aviat!")
            System.exit(0)
        }
        else -> println("Opció no vàlida.")
    }
}

fun menuUsuari(gestor: GestorUsuaris) {
    val usuari = gestor.usuariActual!!
    println("\n=== Hola, ${usuari.nom}! Nivell ${usuari.nivell} | XP: ${usuari.xp}/${usuari.nivell * 100} ===")
    println("1. Afegir tasca")
    println("2. Completar tasca")
    println("3. Mostrar tasques")
    println("4. Eliminar tasca")
    println("5. Eliminar usuari")
    println("6. Tancar Sessió")
    println("7. Sortir")
    print("Escull una opció: ")

    when (readLine()) {
        "1" -> afegirTasca(usuari)
        "2" -> completarTasca(usuari)
        "3" -> Utils.mostrarTasques(usuari)
        "4" -> eliminarTasca(usuari)
        "5" -> gestor.eliminarUsuari()
        "6" -> gestor.canviarUsuari()
        "7" -> {
            println("Fins aviat!")
            System.exit(0)
        }
        else -> println("Opció no vàlida.")
    }
}

fun crearUsuari(gestor: GestorUsuaris) {
    print("ID: ")
    val id = readLine()!!
    print("Nom: ")
    val nom = readLine()!!
    gestor.crearUsuari(id, nom)
    println("Usuari creat!")
}

fun iniciarSessio(gestor: GestorUsuaris) {
    print("ID: ")
    val id = readLine()!!
    print("Nom: ")
    val nom = readLine()!!
    gestor.iniciarSessio(id, nom)
}

fun afegirTasca(usuari: Usuari) {
    println("\nTipus de tasca:")
    println("1. Normal")
    println("2. Diària")
    println("3. Especial")
    print("Escull: ")
    val tipus = readLine()

    print("ID: ")
    val id = readLine()!!
    print("Títol: ")
    val titol = readLine()!!
    print("Dificultat (1-10): ")
    val dificultat = readLine()!!.toInt()
    println("Estadístiques disponibles: ${usuari.estadistiques.map { it.nom }.joinToString(", ")}")
    print("Estadística afectada: ")
    val estadistica = readLine()!!

    val tasca = when (tipus) {
        "1" -> MissioNormal(id, titol, dificultat, estadistica)
        "2" -> MissioDiaria(id, titol, dificultat, estadistica)
        "3" -> MissioEspecial(id, titol, dificultat, estadistica)
        else -> null
    }

    if (tasca != null) {
        usuari.afegirTasca(tasca)
        println("Tasca afegida!")
    } else {
        println("Tipus no vàlid.")
    }
}

fun completarTasca(usuari: Usuari) {
    if (usuari.tasques.isEmpty()) {
        println("No tens tasques.")
        return
    }
    Utils.mostrarTasques(usuari)
    print("ID de la tasca: ")
    val id = readLine()!!
    usuari.completarTasca(id)
}

fun eliminarTasca(usuari: Usuari) {
    if (usuari.tasques.isEmpty()) {
        println("No tens tasques.")
        return
    }
    Utils.mostrarTasques(usuari)
    print("ID de la tasca a eliminar: ")
    val id = readLine()!!
    usuari.eliminarTasca(id)
}