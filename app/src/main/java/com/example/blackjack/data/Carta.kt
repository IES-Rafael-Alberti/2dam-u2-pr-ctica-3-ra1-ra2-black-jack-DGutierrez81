package com.example.blackjack.data

import androidx.annotation.DrawableRes

/**
 * Clase para crear una carta francesa.
 */
class Carta(val nombre: Naipes, val palo: Palos, val puntosMin: Int, val puntosMax: Int, @DrawableRes val idDrawable: Int)