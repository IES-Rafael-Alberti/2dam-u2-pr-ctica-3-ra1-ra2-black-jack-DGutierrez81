package com.example.blackjack.data

/**
 * Clase que contiene el nombre de las rutas o pantallas que contiene el juego.
 */
sealed class Routes(val route: String) {
    object Screen1: Routes("Screen1")
    object Screen2: Routes("Screen2")

    object Screen3: Routes("Screen3")
}