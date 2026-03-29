package models

class Estadistica (val nom: String){
    var valor: Int = 0
    //millora: que cada X tasques completes s'augmenti el nivell
    //cada vegada sigui mes complicat pujar
    fun augmentarValor(){
        valor++
    }
}