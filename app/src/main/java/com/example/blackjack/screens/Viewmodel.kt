package com.example.blackjack.screens


import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.blackjack.data.Baraja
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.blackjack.data.Baraja.Companion.baraja
import com.example.blackjack.data.Carta
import com.example.blackjack.data.Jugador

//class ViewModel: ViewModel()
//class Viewmodel(application: Application): AndroidViewModel(application)
class Viewmodel(application: Application): AndroidViewModel(application){

    @SuppressLint("StaticFieldLeak")
    val context = getApplication<Application>().applicationContext

    private val _carta = MutableLiveData<String>()
    val carta: LiveData<String> = _carta

    private val _show = MutableLiveData<Boolean>()
    val show: LiveData<Boolean> = _show

    private val _cartasEnMano = MutableLiveData<MutableList<String>>()
    val cartasEnMano: LiveData<MutableList<String>> = _cartasEnMano

    private val jugador1 = MutableLiveData<Jugador>()
    private val jugador2 = MutableLiveData<Jugador>()

        init {
            NuevaBaraja()
        }



        private fun NuevaBaraja() {
            val baraja = Baraja.creaBaraja(context)
            Baraja.barajar(baraja)
        }



    fun MostrarPuntos(player: Int): Int{
        jugador1.value = Jugador("pepe", 100, ArrayList(), 100)
        return if(player == 1){
            jugador1.value!!.fichas
        }else jugador2.value?.fichas ?: 0
    }

    /*
        @Composable
        fun PedirCarta() {
            if (baraja.isEmpty()) {
                Toast.makeText( context,"La baraja está vacía ", Toast.LENGTH_SHORT).show()
                _show.value = false

            } else {
                val cartaDada = Baraja.dameCarta(baraja)
                cartaDada.idDrawable
                jugador1.value?.manoCartas?.add(cartaDada)
            }
            /*
            if(contador>1) _cartasEnMano.add(_carta)
            _cartasEnMano.value = cartasEnMano.toMutableList().apply { set(contador, carta) }
            contador ++},
        enabled = enabled

             */
        }



     */



}
/*
   fun Botones(
    Apostar = {},
    PedirCarta = {carta = if (baraja.isEmpty()) {
        Toast.makeText( context,"La baraja está vacía ", Toast.LENGTH_SHORT).show()
        show = false
        "reverso2"
    } else {
        val cartaDada = Baraja.dameCarta(baraja)
        "${cartaDada.nombre}_${cartaDada.palo}".lowercase()
    }
        if(contador>1) cartasEnMano.add(carta)
        cartasEnMano = cartasEnMano.toMutableList().apply { set(contador, carta) }
        contador ++},
    enabled = enabled,
    Reiniciar = {baraja = Baraja.creaBaraja()
        show = true
        enabled = true
        carta = "reverso2"
        cartasEnMano = mutableListOf("reverso2", "reverso2")
        contador = 0
    },
    Plantarse = {enabled = false}
    )
}



 */



/*
fun MostrarCarta(
    carta: String
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = context.resources.getIdentifier(carta, "drawable", context.packageName) ),
            contentDescription = "Carta mostrada",
            modifier = Modifier
                .height(150.dp)
                .width(75.dp)
        )
    }

 */
