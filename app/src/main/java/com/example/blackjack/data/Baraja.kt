package com.example.blackjack.data


import android.content.Context

class Baraja {
    companion object{
        val baraja = mutableListOf<Carta>()

        /**
         * Crea la baraja de cartas francesa.
         */
        fun creaBaraja(context: Context): MutableList<Carta>{
            baraja.clear()
            for(palo in Palos.values()){
                for(naipe in Naipes.values()){
                    baraja.add(Carta(naipe, palo, naipe.puntosMin, naipe.puntosMax, ObtenerId(context, (naipe.toString().lowercase() + "_" + palo.toString().lowercase() ))))
                }
            }
            return baraja
        }

//getIdDrawable(
//                                context,
//                                "${Suits.values()[palo].toString().lowercase()}_${cont}"
//                            )
        /**
         * Baraja el mazo de cartas.
         * @param baraja Recibe el mazo de cartas a barajar.
         * @return Retorna el mazo ya barajdo.
         */
        fun barajar(baraja: MutableList<Carta>): Unit = baraja.shuffle()

        /**
         * Elimina una carta del mazo y la devuelve
         * @return Devuelve la carta eliminada.
         */
        fun dameCarta(): Carta = baraja.removeLast()
        /*
        fun dameCarta(): Carta{
            val carta = baraja.last()
            baraja.removeLast()
            return carta
        }


         */
        /*
        fun getFaceDownCard(): Card {
            return(Card(CardsName.NINGUNA, Suits.NINGUNA, 0, 0, R.drawable.carta))
        }


 */
        private fun ObtenerId(context: Context, nombreCarta: String) = context.resources.getIdentifier(
            nombreCarta,
            "drawable",
            context.packageName
        )

    }
}