package com.example.blackjack.screens

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.blackjack.data.Baraja
import com.example.blackjack.data.Carta
import com.example.blackjack.data.Jugador
import kotlin.system.exitProcess

/**
 * ViewModel utilizados para el juego del BlackJack.
 *
 * Clase que contiene toda la lógica del juego.
 *
 * @property _mostrarMensaje Livedata privada de tipo Boolean que permite mostrar el mensaje de fin de mano.
 * @property mostrarMensaje Livedata pública de tipo Boolean que observa el estado de _mostrarMensaje.
 * @property _numeroJugadores Livedata privada de tipo entero que indica el número de jugadores de la partida.
 * @property _idJuego Livedata privada de tipo entero que indica si es jugador 1, 2 o máquina.
 * @property idJuego Livedata pública de tipo entero que observa el estado de _idJuego.
 * @property _mensaje Livedata privada de tipo cadena que manda el mensaje de quien ha ganado la mano.
 * @property mensaje Livedata pública de tipo cadena que observa el estado de _mensaje.
 * @property _show Livedata privadad de tipo Boolean que permite mostrar el mazo de cartas.
 * @property show Livedata pública de tipo Boolean que observa el estado de _show.
 * @property _showSimple Livedata privada de tipo Boolean que permite mostrar el juego en modo un jugador.
 * @property showSimple Livedata pública de tipo Boolean que observa el estado de _showSimple.
 * @property _showDoble Livedata privada de tipo Boolean que permita mostrar el juego en modo dos jugadores.
 * @property showDoble Livedata pública de tipo Boolean que observa el estado de _showDoble.
 * @property _cartasEnMano Livedata privada de tipo MutableList<Carta> que contiene la mano del jugador1.
 * @property cartasEnMano Livedata pública de tipo MutableList<Carta> que observa el estado de _cartasEnMano.
 * @property _cartasEnMano2 Livedata privada de tipo MutableList<Carta> que contiene la mano del jugador2 o máquina.
 * @property cartasEnMano2 Livedata pública de tipo MutableList<Carta> que observa el estado de _cartasEnMano2.
 * @property _puntos Livedata privado de tipo entero que contiene los puntos sacados por el jugador1.
 * @property puntos Livedata público de tipo entero que observa el estado de _puntos.
 * @property _puntos2 Livedata privado de tipo entero que contiene los puntos sacados por el jugador2.
 * @property puntos2 Livedata público de tipo entero que observa el estado de _puntos2.
 * @property _fichas Livedata privado de tipo entero que contiene el número de fichas del jugador1.
 * @property fichas Livedata público de tipo entero que observa el estado de _fichas.
 * @property _fichas2 Livedata privado de tipo entero que contiene el número de fichas del jugador2.
 * @property fichas2 Livedata público de tipo entero que observa el estado de _fichas2.
 * @property _enableButton Livedata privada de tipo Boolean que permite mostrar u ocultar los botones del jugador1.
 * @property enableButton Livedata público de tipo Boolean que observa el estado de _enableButton.
 * @property _enableButton2 Livedata privada de tipo Boolean que permite mostrar u ocultar los botones del jugador2.
 * @property enableButton2 Livedata público de tipo Boolean que observa el estado de _enableButton2.
 * @property jugador1 Livedata público que contiene el objeto del jugador 1.
 * @property _nombreJugador1 Livedata privada de tipo cadena que contiene el nombre del jugador 1.
 * @property nombreJugador1 Livedata pública de tipo cadena que observa el estado de _nombreJugador1.
 * @property _contador Livedata privada de tipo entero que cuenta el número de cartas de la mano del jugador 1.
 * @property contador Livedata pública de tipo entero que observa el estado de _contador.
 * @property jugador2 Livedata público que contiene el objeto del jugador 2.
 * @property _nombreJugador2 Livedata privada de tipo cadena que contiene el nombre del jugador 2.
 * @property nombreJugador2 Livedata pública de tipo cadena que observa el estado de _nombreJugador2.
 * @property _contador2 Livedata privada de tipo entero que cuenta el número de cartas de la mano del jugador 2.
 * @property contador2 Livedata pública de tipo entero que observa el estado de _contador2.
 * @property _botonInicio Livedata privada de tipo Boolean que habilita o deshabilita el botón enviar del cuadro de inicio
 * @property botonInicio Livedata pública de tipo Boolean que observa el estado de _botonInicio.
 * @property _apuestaTotal Livedata de tipo entero que muestra la cantidad de la apuesta total.
 * @property apuestaTotal Livedata de tipo entero que observa el estado de _apuestaTotal.
 * @property _apuesta Livedata de tipo Boolean que permite mostrar la pantalla de apuesta.
 * @property apuesta Livedata de tipo Boolean que observa el estado de _apuesta.
 * @property _idApuesta Livedata de tipo entero que indica que jugador realiza la apuesta.
 * @property idApuesta Livedata de tipo entero que observa el estado de _idApuesta.
 * @property _fin Livedata de tipo Boolean que permite mostrar la pantalla de final de partida.
 * @property fin Livedata de tipo Boolean que observa el estado de _fin.
 * @property _ganador Livedata de tipo cadena que contiene el nombre del jugador que ha ganado.
 * @property ganador Livedata de tipo cadena que observa el estado de _ganador.
 */


//class ViewModel: ViewModel()
//class Viewmodel(application: Application): AndroidViewModel(application)
class Viewmodel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    val context = getApplication<Application>().applicationContext

    private val _mostrarMensaje = MutableLiveData<Boolean>()
    val mostrarMensaje: LiveData<Boolean> = _mostrarMensaje

    private val _numeroJugadores = MutableLiveData<Int>()


    private val _idJuego = MutableLiveData<Int>()
    val idJuego: LiveData<Int> = _idJuego

    private val _mensaje = MutableLiveData<String>()
    val mensaje: LiveData<String> = _mensaje

    private val _show = MutableLiveData<Boolean>()
    val show: LiveData<Boolean> = _show

    private val _showSimple = MutableLiveData<Boolean>()
    val showSimple: LiveData<Boolean> = _showSimple

    private val _showDoble = MutableLiveData<Boolean>()
    val showDoble: LiveData<Boolean> = _showDoble

    private val _cartasEnMano = MutableLiveData<MutableList<Carta>>()
    val cartasEnMano: LiveData<MutableList<Carta>> = _cartasEnMano

    private val _cartasEnMano2 = MutableLiveData<MutableList<Carta>>()
    val cartasEnMano2: LiveData<MutableList<Carta>> = _cartasEnMano2

    private val _puntos = MutableLiveData<String>()
    val puntos: LiveData<String> = _puntos

    private val _puntos2 = MutableLiveData<String>()
    val puntos2: LiveData<String> = _puntos2

    private val _fichas = MutableLiveData<Int>()
    val fichas: LiveData<Int> = _fichas

    private val _fichas2 = MutableLiveData<Int>()
    val fichas2: LiveData<Int> = _fichas2

    private val _enableButton = MutableLiveData<Boolean>()
    val enableButton: LiveData<Boolean> = _enableButton

    private val _enableButton2 = MutableLiveData<Boolean>()
    val enableButton2: LiveData<Boolean> = _enableButton2

    private var jugador1 = MutableLiveData<Jugador>()

    private val _nombreJugador1 = MutableLiveData<String>()
    val nombreJugador1: LiveData<String> = _nombreJugador1

    private val _contador = MutableLiveData<Int>()
    val contador: LiveData<Int> = _contador

    private var jugador2 = MutableLiveData<Jugador>()

    private val _nombreJugador2 = MutableLiveData<String>()
    val nombreJugador2: LiveData<String> = _nombreJugador2

    private val _contador2 = MutableLiveData<Int>()
    val contador2: LiveData<Int> = _contador2

    private val _botonInicio = MutableLiveData<Boolean>()
    val botonInicio: LiveData<Boolean> = _botonInicio

    private val _apuestaTotal = MutableLiveData<Int>()
    val apuestaTotal: LiveData<Int> = _apuestaTotal

    private val _apuesta = MutableLiveData<Boolean>()
    val apuesta: LiveData<Boolean> = _apuesta

    private val _idApuesta = MutableLiveData<Int>()
    val idApuesta: LiveData<Int> = _idApuesta

    private val _fin = MutableLiveData<Boolean>()
    val fin: LiveData<Boolean> = _fin

    private val _ganador = MutableLiveData<String>()
    val ganador: LiveData<String> = _ganador

    init {
        nuevaBaraja()
    }


    /**
     * Recibe el nombre del jugador que se inscribe en el recuadro de inscripción.
     * Si el valor de uno de los nombres es nulo o vacio el valor de la variable _botonInicio es false.
     * @param id id del jugador.
     * @param nombre nombre del jugador.
     */

    fun jugadorInscribir(id: Int, nombre: String, numero: Int) {
        if (id == 1) {
            _nombreJugador1.value = nombre
        } else {
            _nombreJugador2.value = nombre
        }

        if(numero == 1){
            _botonInicio.value = !(_nombreJugador1.value.isNullOrEmpty())
        }else _botonInicio.value = !(_nombreJugador1.value.isNullOrEmpty() || _nombreJugador2.value.isNullOrEmpty())
    }

    /**
     * Función que tiene como misión confirmar los datos de entrada de la partida.
     * @param opcion es un valor Booleano que recibe true o false.
     */
    fun confirmar(opcion: Boolean, id: Int) {
        if(id == 1) {
            _showSimple.value = opcion
            _numeroJugadores.value = 1
        } else {
            _showDoble.value = opcion
            _numeroJugadores.value = 2
        }

        if (!opcion) {
            _nombreJugador1.value = ""
            _nombreJugador2.value = ""
        }
    }

    /**
     * Se crea el valor inicial de los jugadores una vez que se acepta en el recuadro de inscripción.
     */
    fun inicio(id: Int) {
        if(id == 1){
            jugador1.value = Jugador(_nombreJugador1.value!!, 100, mutableListOf(), 0)
            jugador2.value = Jugador("BlackJackneitor", 100, mutableListOf(), 0)
            _idJuego.value = turno(id)
            _numeroJugadores.value = 1
            confirmar(false, 1)
        }else{
            jugador1.value = Jugador(_nombreJugador1.value!!, 100, mutableListOf(), 0)
            jugador2.value = Jugador(_nombreJugador2.value!!, 100, mutableListOf(), 0)
            _idJuego.value = turno(id)
            _numeroJugadores.value = 2
            confirmar(false, 2)
        }
        _mostrarMensaje.value = false
        _fin.value = false
        reiniciar()
    }

    /**
     * Se crea y baraja un mazo de cartas
     */
    private fun nuevaBaraja() {
        val baraja = Baraja.creaBaraja(context)
        Baraja.barajar(baraja)
    }


    /**
     * Elije el turno aleatoriamente
     * @return Devuelve el valor aleatirio de la lista.
     */
    private fun turno(jugadores: Int): Int {
        return if(jugadores == 1){
            1
        }else{
            val listaId = listOf(1, 2)
            listaId.random()
        }

    }

    /**
     * Muestra la cantidad de fichas que le queda a cada jugador.
     * @param id Indica la id del jugador.
     * @return Retorna la cantidad de fichas del jugador indicado.
     */


    fun mostrarFichas(id: Int): Int {
        return if (id == 1) {
            jugador1.value!!.fichas
        } else jugador2.value!!.fichas
    }

    /**
     * Devuelve un String, el nombre del jugador.
     * @param id indica si es el jugador 1 o el jugador 2
     */
    fun mostrarNombre(id: Int): String {
        return if (id == 1) {
            jugador1.value?.nombre ?: "jugador1"
        } else {
            jugador2.value?.nombre ?: "jugador2"
        }
    }

    /**
     * Activa o desactiva el cuadro de diálogo que indica el ganador de cada partida, no del juego.
     * @param opcion recibe true o false para activar o desactivar el cuadro.
     */

    fun mensajeFinPartida(opcion: Boolean) {
        _mostrarMensaje.value = opcion
        if(!opcion) reiniciar()
    }

    /**
     * Activa el cuadro para realizar la apuesta.
     * @param id indica la id del jugador que va a realizar la apuesta.
     */
    fun activarApuesta(id: Int) {
        if (id == 1) _apuesta.value = true else _apuesta.value = true
        _idApuesta.value = id
    }

    /**
     * Dependiendo del id se le dá carta al jugador.
     * Si se acabasen las cartas envía un mensaje de aviso.
     * @param id indica la id del jugador.
     */
    fun pedirCarta(id: Int) {
        if (Baraja.baraja.isEmpty()) {
            Toast.makeText(context, "La baraja está vacía ", Toast.LENGTH_SHORT).show()
            _show.value = false

        } else {
            when (id) {
                // Con esta opción siempre pedirá la carta el jugador 1
                1 -> {
                    obtenerMazoJugador1()
                }

                // Pedirá la carta el jugador 2 si está en el modo de 2 jugadores.
                2 -> {
                    obtenerMazoJugador2()
                }
                3 -> {
                    obtenerMazoMaquina()
                }

            }
        }
    }

    /**
     * Se obtiene la mano de cartas del jugador 1.
     */

    private fun obtenerMazoJugador1(){
        if ((fichas.value ?: 0) == 0) Toast.makeText(
            context,
            "Tiene que apostar ",
            Toast.LENGTH_SHORT
        ).show() else {
            jugador1.value!!.manoCartas.add(Baraja.dameCarta())
            _cartasEnMano.value = jugador1.value!!.manoCartas
            _contador.value = _cartasEnMano.value!!.size
            puntaje(jugador1.value!!, 1)
        }
    }

    /**
     * Se obtiene la mano de cartas del jugador 2.
     */
    private fun obtenerMazoJugador2(){
        if ((fichas2.value ?: 0) == 0) Toast.makeText(
            context,
            "Tiene que apostar ",
            Toast.LENGTH_SHORT
        ).show()
        else {
            jugador2.value!!.manoCartas.add(Baraja.dameCarta())
            _cartasEnMano2.value = jugador2.value!!.manoCartas
            _contador2.value = _cartasEnMano2.value!!.size
            puntaje(jugador2.value!!, 2)
        }
    }

    /**
     * Se obtiene la mano de cartas de la máquina.
     */
    private fun obtenerMazoMaquina(){
        jugador2.value!!.manoCartas.add(Baraja.dameCarta())
        _cartasEnMano2.value = jugador2.value!!.manoCartas
        _contador2.value = _cartasEnMano2.value!!.size
        puntaje(jugador2.value!!, 2)
    }


    /**
     * Calculo realizado para saber si llega o no, o se pasa del número 21.
     * @param jugador objeto para poder utilizar sus características.
     * @param id depende del id se utiliza al jugador 1 o al jugador 2.
     */


    private fun puntaje(jugador: Jugador, id: Int) {
        var puntosActuales = 0
        for (i in jugador.manoCartas) {
            puntosActuales += i.puntosMin
        }
        for (i in jugador.manoCartas) {
            if (i.puntosMin != i.puntosMax && (puntosActuales - i.puntosMin + i.puntosMax) <= 21) {
                puntosActuales -= i.puntosMin
                puntosActuales += i.puntosMax
            }

        }
        if (id == 1) {
            puntosJugador1(puntosActuales)
        } else {
            puntosJugador2(puntosActuales)
        }
    }

    /**
     * Se calcula los puntos que tiene  el  jugador 1.
     * @param puntosActuales recibe los puntos actuales de la mano.
     */

    private fun puntosJugador1(puntosActuales: Int){
        jugador1.value!!.puntos = puntosActuales
        _puntos.value = puntosActuales.toString()
        if (jugador1.value!!.puntos > 21) {
            _enableButton.value = false
            if(_numeroJugadores.value == 2) _idJuego.value = 2 else {
                maquina()
            }
            ganadorMano()
        }
    }

    /**
     * Se calcula los puntos que tiene  el  jugador 2.
     * @param puntosActuales recibe los puntos actuales de la mano.
     */
    private fun puntosJugador2(puntosActuales: Int){
        jugador2.value!!.puntos = puntosActuales
        _puntos2.value = puntosActuales.toString()
        if (jugador2.value!!.puntos > 21) {
            _enableButton2.value = false
            _idJuego.value = 1
        }
        ganadorMano()
    }

    /**
     * Contiene la lógica de la máquina a la hora de jugar.
     */

    private fun maquina() {
        while (Baraja.baraja.isNotEmpty()) {
            pedirCarta(3)
            if (jugador2.value!!.puntos > 18) break
        }
        var fichasActuales = _apuestaTotal.value ?: 0


        when (jugador2.value!!.puntos) {
            21 -> {
                fichasActuales += 25
                _apuestaTotal.value = fichasActuales
                jugador2.value!!.fichas -= 25
            }
            in 19..20 -> {
                fichasActuales += 5
                _apuestaTotal.value = fichasActuales
                jugador2.value!!.fichas -= 5
            }
            else -> {
                fichasActuales +=1
                _apuestaTotal.value = fichasActuales
                jugador2.value!!.fichas -= 1
            }
        }


        plantarse(2)

    }

    /**
     * Activa la opción de plantarse si se cumplen las condiciones.
     * @param id indica la id del jugador.
     */
    fun plantarse(id: Int) {
        if (id == 1) {
            plantarJugador1()
        } else {
            plantarJugador2()
        }
    }

    /**
     * Permite poder plantarse al jugador 1.
     */
    private fun plantarJugador1(){
        if (jugador1.value!!.puntos < 15) Toast.makeText(
            context,
            "No puede plantarse con una puntución menor de 15",
            Toast.LENGTH_SHORT
        ).show() else {
            _enableButton.value = false
            if(_numeroJugadores.value == 2) _idJuego.value = 2 else {
                maquina()
                _idJuego.value = 3
            }
        }
    }

    /**
     * Permite poder plantarse al jugador 2.
     */
    private fun plantarJugador2(){
        if (jugador2.value!!.puntos < 15) Toast.makeText(
            context,
            "No puede plantarse con una puntución menor de 15",
            Toast.LENGTH_SHORT
        ).show() else {
            _enableButton2.value = false
            _idJuego.value = 1
        }
    }

    /**
     * Calcula cual es el jugador que gana la partida.
     * Se desactiva los botones de pedir carta y apostar.
     */

    fun ganadorMano() {
        if (_enableButton.value == false && _enableButton2.value == false) {
            when {
                jugador1.value!!.puntos <= 21 && jugador1.value!!.puntos > jugador2.value!!.puntos -> {
                    mensajeFinPartida(true)
                    jugador1.value!!.fichas += _apuestaTotal.value!!
                    _mensaje.value = "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos\nnúmero de cartas 1: ${contador.value}\nnúmero de cartas 2: ${contador2.value}"
                }
                jugador2.value!!.puntos > 21 -> {
                    mensajeFinPartida(true)
                    jugador1.value!!.fichas += _apuestaTotal.value!!
                    _mensaje.value = "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos\nnúmero de cartas 1: ${contador.value}\nnúmero de cartas 2: ${contador2.value}"
                }
                jugador1.value!!.puntos == 21 && contador.value == 2 -> {
                    mensajeFinPartida(true)
                    jugador2.value!!.fichas += _apuestaTotal.value!!
                    _mensaje.value = "¡¡¡¡HAS HECHO BLACK JAC!!!!!"
                }
                jugador1.value!!.puntos == jugador2.value!!.puntos -> {
                    mensajeFinPartida(true)
                    jugador1.value!!.fichas += _fichas.value!!
                    jugador2.value!!.fichas += _fichas2.value!!
                    _mensaje.value = "¡¡¡EMPATE!!!: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
                }

                else -> {
                    mensajeFinPartida(true)
                    jugador2.value!!.fichas += _apuestaTotal.value!!
                    _mensaje.value =
                        "Felicidades has ganado ${jugador2.value!!.nombre}\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos"
                }
            }
            ganadorPartida()
        }
    }

    /**
     * Si un jugador se queda sin fichas pierde la partida e indica que jugador es el ganador.
     */
    private fun ganadorPartida(){
        if (jugador1.value!!.fichas == 0 || jugador2.value!!.fichas == 0) {
            if (jugador1.value!!.fichas > jugador2.value!!.fichas) {
                _ganador.value = jugador1.value!!.nombre
            } else {
                _ganador.value = jugador2.value!!.nombre
            }
            _fin.value = true
        }
    }

    /**
     * Se llama para desactivar el cuadro de diálogo de la apuesta.
     */
    fun desactivarApuesta() {
        _apuesta.value = false
    }

    /**
     * Se dirige a incrementar o decrementar la apuesta.
     * @param signo Dependiendo del signo incrementa o decrementa.
     * @param id Indica la id del jugador.
     */

    fun apostar(signo: String, id: Int) {
        if (signo == "+"){
            incrementarApuesta(id)
        }else decrementarApuesta(id)
    }

    /**
     * Incrementa la apuesta a realizar.
     * @param id Indica la id del jugador.
     */
    private fun incrementarApuesta(id: Int){
        var fichasActuales = _apuestaTotal.value ?: 0
        var fichas: Int
        val jugador: Jugador
        if(id == 1){
            fichas = _fichas.value ?: 0
            jugador = jugador1.value!!
        }else {
            jugador = jugador2.value!!
            fichas = _fichas2.value ?: 0
        }
        if (jugador.fichas == 0) {
            Toast.makeText(
                context,
                "Has alcanzdo el máximo a apostar.",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            fichasActuales ++
            fichas ++
            jugador.fichas -= 1
            _apuestaTotal.value = fichasActuales
            if(id == 1){
                _fichas.value = fichas
            }else _fichas2.value = fichas
        }
    }


    /**
     * Decrementa la apuesta a realizar.
     * @param id Indica la id del jugador.
     */
    private fun decrementarApuesta(id: Int){
        var fichasActuales = _apuestaTotal.value ?: 0
        var fichas: Int
        val jugador: Jugador
        if(id == 1){
            fichas = _fichas.value ?: 0
            jugador = jugador1.value!!
        }else {
            jugador = jugador2.value!!
            fichas = _fichas2.value ?: 0
        }
        if (fichas == 0) {
            Toast.makeText(
                context,
                "Lo siento, no puede dejar la apuesta a 0.",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            fichasActuales -= 1
            fichas --
            jugador.fichas += 1
            _apuestaTotal.value = fichasActuales
            if(id == 1){
                _fichas.value = fichas
            }else _fichas2.value = fichas

        }
    }




    /**
     * Reinicia todos los atributos a su valor de origen.
     */

    private fun reiniciar() {
        _apuestaTotal.value = 0
        _fichas2.value = 0
        _enableButton.value = true
        _enableButton2.value = true
        _show.value = true
        _cartasEnMano.value = mutableListOf()
        jugador1.value!!.manoCartas = mutableListOf()
        jugador1.value!!.puntos = 0
        _fichas.value = 0
        _puntos.value = "0"
        _cartasEnMano2.value = mutableListOf()
        jugador2.value!!.manoCartas = mutableListOf()
        jugador2.value!!.puntos = 0
        _puntos2.value = "0"
        _fichas2.value = 0

        if(_numeroJugadores.value == 1) _idJuego.value = turno(1) else {
            _idJuego.value = turno(2)
        }
        nuevaBaraja()
    }

    /**
     * Sale de aplicación.
     */

    fun finPartida() {
        exitProcess(0)
    }

}
