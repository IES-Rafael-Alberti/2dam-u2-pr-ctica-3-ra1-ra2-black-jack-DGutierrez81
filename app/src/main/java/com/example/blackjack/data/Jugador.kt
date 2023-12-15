package com.example.blackjack.data

/**
 * Clase que permite crear un jugador concreto y guardar sus datos.
 */
class Jugador(var nombre: String, var fichas: Int = 100, var manoCartas: MutableList<Carta> = mutableListOf(), var puntos: Int = 100){

}