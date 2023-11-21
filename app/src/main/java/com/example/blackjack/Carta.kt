package com.example.blackjack

/**
 * Clase para crear una carta francesa.
 */
class Carta(val nombre: Naipes, val palo: Palos) {
    var puntosMin: Int = 0
    var puntosMax: Int = 0
    var idDrawable: Int = 0
    constructor(nombre: Naipes, palo: Palos,puntosMin: Int, puntosMax: Int): this(nombre, palo){
        this.puntosMin = puntosMin
        this.puntosMax = puntosMax
    }

}