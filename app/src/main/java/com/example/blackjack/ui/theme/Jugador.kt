package com.example.blackjack.ui.theme

import com.example.blackjack.Carta

class Jugador(val nombre: String, var fichas: Int = 100) {
    private val manoCartas: MutableList<Carta> = mutableListOf()

    var puntos: Int = 0
        private set

    val mano: Pair<Carta, Carta>
        get() {
            require(manoCartas.size >= 2) { "El jugador debe tener al menos dos cartas en la mano" }
            return manoCartas[0] to manoCartas[1]
        }

    fun recibirCarta(carta: Carta) {
        manoCartas.add(carta)
       // puntos += carta.valor // Suponiendo que Carta tiene un atributo "valor"
    }

    // Otras funciones relacionadas con el jugador y su mano, como evaluar la puntuación, etc.
}