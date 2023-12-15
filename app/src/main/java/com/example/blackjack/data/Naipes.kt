package com.example.blackjack.data

/**
 * Clase que contiene el número de cada carta.
 */
enum class Naipes(val puntosMin: Int,val puntosMax: Int) {
    AS(1, 11),
    DOS(2, 2),
    TRES( 3, 3),
    CUATRO(4,4),
    CINCO(5,5),
    SEIS(6,6),
    SIETE(7,7),
    OCHO(8,8),
    NUEVE(9,9),
    DIEZ(10,10),
    VALET(10,10),
    DAME(10,10),
    ROI(10,10)
}