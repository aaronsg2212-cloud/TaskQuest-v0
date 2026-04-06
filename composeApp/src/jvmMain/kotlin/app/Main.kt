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
    println("7. Filtrar per dificultat")
    println("8. Sortir")
    print("Escull una opció: ")

    when (readLine()) {
        "1" -> afegirTasca(usuari)
        "2" -> completarTasca(usuari)
        "3" -> Utils.mostrarTasques(usuari)
        "4" -> eliminarTasca(usuari)
        "5" -> gestor.eliminarUsuari()
        "6" -> gestor.canviarUsuari()
        "7" -> filtrarTasques(usuari)
        "8" -> {
            println("Fins aviat!")
            System.exit(0)
        }
        else -> println("Opció no vàlida.")
    }
}

fun crearUsuari(gestor: GestorUsuaris) {
//funcions a utils pera demanar els ids i els noms per a evitar redundancia de codi
    println("ID usuari:")
    val id = Utils.demanarId()
    println("Nom usuari:")
    val nom = Utils.demanarINoms()

    gestor.crearUsuari(id, nom)
}

fun iniciarSessio(gestor: GestorUsuaris) {
    println("===INICI DE SESSIÓ===")
    println("Id usuari:")
    val id = Utils.demanarId()
    println("Nom usuari:")
    val nom = Utils.demanarINoms()
    gestor.iniciarSessio(id, nom)
}

fun afegirTasca(usuari: Usuari) {
    println("\nTipus de tasca:")
    println("1. Normal")
    println("2. Diària")
    println("3. Especial")
    print("Escull: ")
    val tipus = readLine()!!.trim()

    println("ID tasca:")
    val id = Utils.demanarId()
    println("Titol de la tasca:")
    val titol = Utils.demanarINoms()

    var dificultat: Int
    do {
        print("Dificultat (1-10): ")
        dificultat = readLine()!!.toInt()
        if (dificultat !in 1..10) println("La dificultat no pot ser mes gran que 10 ni menor que 1")
    }while (dificultat !in 1..10)

    println("Estadístiques disponibles: ${usuari.estadistiques.map { it.nom }.joinToString(", ")}")
    print("Estadística afectada: ")
    val estadistica = readLine()!!.trim()

    val tasca = Tasca.crear(tipus, id, titol, dificultat, estadistica)

    if (tasca != null) {
        usuari.afegirTasca(tasca)
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
    val id = Utils.demanarId()
    usuari.completarTasca(id)
}

fun eliminarTasca(usuari: Usuari) {
    if (usuari.tasques.isEmpty()) {
        println("No tens tasques.")
        return
    }
    Utils.mostrarTasques(usuari)
    print("ID de la tasca a eliminar: ")
    val id = Utils.demanarId()
    usuari.eliminarTasca(id)
}
//funició per a fultrar tasques per dificultat mitjançant rangs.

fun filtrarTasques(usuari: Usuari) {
    println("\n1. Fàcil (1-3)")
    println("2. Mitjà (4-6)")
    println("3. Difícil (7-10)")
    print("Escull: ")
    val rang = readLine()!!
    usuari.filtrarPerDificultat(rang)
}