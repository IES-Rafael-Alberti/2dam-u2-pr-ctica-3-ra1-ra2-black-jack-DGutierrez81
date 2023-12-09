package com.example.blackjack.data

class Jugador(var nombre: String, var fichas: Int = 100, var manoCartas: MutableList<Carta> = mutableListOf(), var puntos: Int = 100){

}