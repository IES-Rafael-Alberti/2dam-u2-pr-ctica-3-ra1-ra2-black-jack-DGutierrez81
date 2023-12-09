package com.example.blackjack.screens


import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
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

import com.example.blackjack.data.Carta
import com.example.blackjack.data.Jugador

//class ViewModel: ViewModel()
//class Viewmodel(application: Application): AndroidViewModel(application)
class Viewmodel(application: Application): AndroidViewModel(application){

    @SuppressLint("StaticFieldLeak")
    val context = getApplication<Application>().applicationContext

    private val _carta = MutableLiveData<String>()
    val carta: LiveData<String> = _carta

    private val _mostrarMensaje = MutableLiveData<Boolean>()
    val mostrarMensaje: LiveData<Boolean> = _mostrarMensaje

    private val _mensaje = MutableLiveData<String>()
    val mensaje: LiveData<String> = _mensaje

    private val _show = MutableLiveData<Boolean>()
    val show: LiveData<Boolean> = _show

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    private val _cartasEnMano = MutableLiveData<MutableList<Carta>>()
    val cartasEnMano: LiveData<MutableList<Carta>> = _cartasEnMano

    private val _cartasEnMano2 = MutableLiveData<MutableList<Carta>>()
    val cartasEnMano2: LiveData<MutableList<Carta>> = _cartasEnMano2

    private val _puntos = MutableLiveData<String>()
    val puntos: LiveData<String> = _puntos

    private val _puntos2 = MutableLiveData<String>()
    val puntos2: LiveData<String> = _puntos2

    private val _enable = MutableLiveData<Boolean>()
    val enable: LiveData<Boolean> = _enable

    private val _enableButton = MutableLiveData<Boolean>()
    val enableButton: LiveData<Boolean> = _enableButton

    private val _enableButton2 = MutableLiveData<Boolean>()
    val enableButton2: LiveData<Boolean> = _enableButton2

    private var jugador1 = MutableLiveData<Jugador>()

    private val _nombreJugador1 = MutableLiveData<String>()
    val nombreJugador1: LiveData<String> = _nombreJugador1

    private val _nombreJugador2 = MutableLiveData<String>()
    val nombreJugador2: LiveData<String> = _nombreJugador2

    private var jugador2 = MutableLiveData<Jugador>()

    private val _contador = MutableLiveData<Int>()
    val contador:LiveData<Int> = _contador

    private val _contador2 = MutableLiveData<Int>()
    val contador2:LiveData<Int> = _contador2

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
            jugador1.value?.nombre ?: "jugador1"
        }else{
            jugador2.value?.nombre ?: "jugador2"
        }
    }
/*
    fun MostrarPuntos(player: Int): Int{

        return if(player == 1){
            _puntos.value ?: 0
        }else jugador2.value!!.puntos
    }

 */

    /**
     * Función que tiene como misión confirmar los datos de entrada de la partida
     * @param opcion es un valor Booleano que recibe true o false.
     */
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
        jugador1.value = Jugador(_nombreJugador1.value!!, 100, mutableListOf(), 0)
        jugador2.value = Jugador(_nombreJugador2.value!!, 100, mutableListOf(), 0)
    }

    fun PedirCarta(player: Int) {


        if (Baraja.baraja.isEmpty()) {
            Toast.makeText( context,"La baraja está vacía ", Toast.LENGTH_SHORT).show()
            _show.value = false

        } else {
            /*
            val lista = mutableListOf<Carta>()
            lista.add(Baraja.dameCarta())
            jugador1.value!!.manoCartas = lista
            _cartasEnMano.value = lista
             */
            when(player){
                1 -> {
                    jugador1.value!!.manoCartas.add(Baraja.dameCarta())
                    _cartasEnMano.value = jugador1.value!!.manoCartas
                    _contador.value = _cartasEnMano.value!!.size
                    puntaje(jugador1.value!!, 1)
                }
                2 -> {
                    jugador2.value!!.manoCartas.add(Baraja.dameCarta())
                    _cartasEnMano2.value = jugador2.value!!.manoCartas
                    _contador2.value = _cartasEnMano2.value!!.size
                    puntaje(jugador2.value!!, 2)
                }
            }


        }
        /*
        if (baraja.isEmpty()) {
            Toast.makeText( context,"La baraja está vacía ", Toast.LENGTH_SHORT).show()
            _show.value = false

        } else {
            val cartaDada = Baraja.dameCarta(baraja)
            jugador1.value?.manoCartas?.add(cartaDada)
            _cartasEnMano.value?.add(cartaDada)
        }

        if(contador>1) _cartasEnMano.add(_carta)
        _cartasEnMano.value = cartasEnMano.toMutableList().apply { set(contador, carta) }
        contador ++},
    enabled = enabled

         */
    }

    fun puntaje(jugador: Jugador, id: Int){
        var puntosActuales = 0
        for(i in jugador.manoCartas){
            //jugador1.value!!.puntos += 10
            puntosActuales += i.puntosMin
        }
        if(id == 1) {
            jugador1.value!!.puntos = puntosActuales
            _puntos.value = puntosActuales.toString()
            if(jugador1.value!!.puntos > 21) {
                _enableButton.value = false
            }
            Ganador(1)
        } else {
            jugador2.value!!.puntos = puntosActuales
            _puntos2.value = puntosActuales.toString()
            if(jugador2.value!!.puntos > 21) {
                _enableButton2.value = false
            }
            Ganador(2)
        }

    }

    fun MensajeFinPartida(opcion: Boolean){
        _mostrarMensaje.value = opcion
        _enable.value = false
    }

    fun Ganador(id: Int){
        if(_enableButton.value == false && _enableButton2.value == false) {
            when{
                jugador1.value!!.puntos <= 21 && jugador1.value!!.puntos > jugador2.value!!.puntos -> {
                    MensajeFinPartida(true)
                    _mensaje.value = "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
                }
                jugador1.value!!.puntos > 21 && jugador1.value!!.puntos < jugador2.value!!.puntos -> {
                    MensajeFinPartida(true)
                    _mensaje.value = "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
                }
                jugador1.value!!.puntos == jugador2.value!!.puntos -> {
                    MensajeFinPartida(true)
                    _mensaje.value = "¡¡¡EMPATE!!!: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
                }
                else -> {
                    MensajeFinPartida(true)
                    _mensaje.value = "Felicidades has ganado ${jugador2.value!!.nombre}\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos"
                }
            }
        }
        /*

        fun apostar(jugador: Jugador, id: Int){
            var fichasActuales = 0

            if(id == 1) {
                jugador1.value!!.puntos = puntosActuales
                _puntos.value = puntosActuales.toString()
                if(jugador1.value!!.puntos > 21) {
                    _enableButton.value = false
                }
                Ganador(1)
            } else {
                jugador2.value!!.puntos = puntosActuales
                _puntos2.value = puntosActuales.toString()
                if(jugador2.value!!.puntos > 21) {
                    _enableButton2.value = false
                }
                Ganador(2)
            }

        }

         */

        /*

if ((jugador1.value!!.puntos <= 21) && (jugador1.value!!.puntos > jugador2.value!!.puntos)) {
                MensajeFinPartida(true)
                _mensaje.value = "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
            } else if (jugador1.value!!.puntos > 21 && jugador1.value!!.puntos < jugador2.value!!.puntos) {
                MensajeFinPartida(true)
                _mensaje.value =
                    "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
            } else if (jugador1.value!!.puntos == jugador2.value!!.puntos) {
                MensajeFinPartida(true)
                _mensaje.value = "¡¡¡EMPATE!!!: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
            }else {
                MensajeFinPartida(true)
                _mensaje.value = "Felicidades has ganado ${jugador2.value!!.nombre}\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos"
            }







else if(jugador1.value!!.puntos == jugador2.value!!.puntos){
                MensajeFinPartida(true)
                _mensaje.value = "¡¡¡EMPATE!!!: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
            }else {
                MensajeFinPartida(true)
                _mensaje.value = "Felicidades has ganado ${jugador2.value!!.nombre} por tener menos cartas \n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
            }
        if(_enableButton.value == false && _enableButton2.value == false) {
            if ((jugador1.value!!.puntos == 21 && jugador2.value!!.puntos < 21) || (jugador1.value!!.puntos < 21 && jugador2.value!!.puntos > 21)) {
                MensajeFinPartida(true)
                _mensaje.value =
                    "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
            } else if (jugador2.value!!.puntos == 21 && jugador1.value!!.puntos < 21 || (jugador2.value!!.puntos < 21 && jugador1.value!!.puntos > 21)) {
                MensajeFinPartida(true)
                _mensaje.value =
                    "Felicidades has ganado ${jugador2.value!!.nombre}\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos"
            } else if ((jugador1.value!!.puntos == jugador2.value!!.puntos) && (_contador.value!! > _contador2.value!!)) {
                MensajeFinPartida(true)
                _mensaje.value =
                    "Felicidades has ganado ${jugador1.value!!.nombre} por tener menos cartas \n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
            }else if ((jugador1.value!!.puntos == jugador2.value!!.puntos) && (_contador.value!! < _contador2.value!!)) {
                MensajeFinPartida(true)
                _mensaje.value =
                    "Felicidades has ganado ${jugador2.value!!.nombre} por tener menos cartas \n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
            }else if ((jugador1.value!!.puntos == jugador2.value!!.puntos) && (_contador.value!! > _contador2.value!!)) {
                MensajeFinPartida(true)
                _mensaje.value =
                    "Felicidades has ganado ${jugador1.value!!.nombre} por tener menos cartas \n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
            }
        }


        if(_enableButton.value == false && _enableButton2.value == false){
            if((jugador1.value!!.puntos == 21 && jugador2.value!!.puntos < 21) || (jugador1.value!!.puntos < 21 && jugador2.value!!.puntos > 21)){
                MensajeFinPartida(true)
                _mensaje.value = "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
            }else if(jugador2.value!!.puntos == 21 && jugador1.value!!.puntos < 21 || (jugador2.value!!.puntos < 21 && jugador1.value!!.puntos > 21)){
                MensajeFinPartida(true)
                _mensaje.value = "Felicidades has ganado ${jugador2.value!!.nombre}\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos"
        }

        when(id){
            1 -> {
                if(jugador1.value!!.puntos == 21 && jugador2.value!!.puntos < 21){
                    MensajeFinPartida(true)
                    _mensaje.value = "Felicidades has ganado ${jugador1.value!!.nombre}"
                }else if(jugador1.value!!.puntos > 21){
                    MensajeFinPartida(true)
                    _mensaje.value = "${jugador1.value!!.nombre} eres un paquete"
                }
            }
            2 -> {
                if(jugador2.value!!.puntos == 21 && jugador1.value!!.puntos < 21){
                    MensajeFinPartida(true)
                    _mensaje.value = "Felicidades has ganado ${jugador2.value!!.nombre}"
                }else if(jugador2.value!!.puntos > 21){
                    MensajeFinPartida(true)
                    _mensaje.value = "${jugador2.value!!.nombre} eres un paquete"
                }
            }

         */



    }

    fun Reiniciar(){
        _enable.value = true
        _enableButton.value = true
        _enableButton2.value = true
        _show.value = true
        jugador1.value!!.manoCartas = mutableListOf()
        _cartasEnMano.value = mutableListOf()
        _puntos.value = "0"
        jugador1.value!!.puntos = 0
        jugador2.value!!.manoCartas = mutableListOf()
        _cartasEnMano2.value = mutableListOf()
        jugador2.value!!.puntos = 0
        _puntos2.value = "0"
        NuevaBaraja()
    }

    fun Plantarse(id: Int){
        if(id == 1) _enableButton.value = false else _enableButton2.value = false
    }





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
