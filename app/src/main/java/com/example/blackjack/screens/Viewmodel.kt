package com.example.blackjack.screens


import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.blackjack.data.Baraja
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.blackjack.R
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

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _cartasEnMano = MutableLiveData<MutableList<String>>()
    val cartasEnMano: LiveData<MutableList<String>> = _cartasEnMano

    private val jugador1 = MutableLiveData<Jugador>()

    private val _nombreJugador1 = MutableLiveData<String>()
    val nombreJugador1: LiveData<String> = _nombreJugador1

    private val _nombreJugador2 = MutableLiveData<String>()
    val nombreJugador2: LiveData<String> = _nombreJugador2

    private val jugador2 = MutableLiveData<Jugador>()

    private val _botonInicio = MutableLiveData<Boolean>()
    val botonInicio: LiveData<Boolean> = _botonInicio

        init {
            NuevaBaraja()
        }



        private fun NuevaBaraja() {
            val baraja = Baraja.creaBaraja(context)
            Baraja.barajar(baraja)
        }


    fun MostrarNombre(player: Int): String {
        return if(player == 1){
            _nombreJugador1.value ?: "jugador1"
        }else{
            _nombreJugador2.value ?: "jugador2"
        }
    }

    fun MostrarPuntos(player: Int): Int{

        return if(player == 1){
            jugador1.value?.fichas ?: 0
        }else jugador2.value?.fichas ?: 0
    }

    fun Confirmar(opcion: Boolean){
        _showDialog.value = opcion
        if(!opcion){
            _nombreJugador1.value = ""
            _nombreJugador2.value = ""
        }
    }


    fun MostrarDialogoInicio(id: Int, nombre: String) {
        if(id == 1){
            _nombreJugador1.value = nombre
        }else {
            _nombreJugador2.value = nombre
        }

        _botonInicio.value = !(_nombreJugador1.value.isNullOrEmpty() || _nombreJugador2.value.isNullOrEmpty())
    }


    fun Inicio(){
        jugador1.value = Jugador(_nombreJugador1.value!!, 100, ArrayList(), 0)
        jugador2.value = Jugador(_nombreJugador2.value!!, 100, ArrayList(), 0)
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
