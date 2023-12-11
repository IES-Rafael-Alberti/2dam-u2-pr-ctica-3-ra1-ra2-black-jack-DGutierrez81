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

//class ViewModel: ViewModel()
//class Viewmodel(application: Application): AndroidViewModel(application)
class Viewmodel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    val context = getApplication<Application>().applicationContext

    private val _mostrarMensaje = MutableLiveData<Boolean>()
    val mostrarMensaje: LiveData<Boolean> = _mostrarMensaje

    private val _idJuego = MutableLiveData<Int>()
    val idJuego: LiveData<Int> = _idJuego

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

    private val _fichas = MutableLiveData<Int>()
    val fichas: LiveData<Int> = _fichas

    private val _fichas2 = MutableLiveData<Int>()
    val fichas2: LiveData<Int> = _fichas2

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
    val contador: LiveData<Int> = _contador

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
    private fun turno(): Int {
        val listaId = listOf(1, 2)
        return listaId.random()
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
     * Función que tiene como misión confirmar los datos de entrada de la partida.
     * @param opcion es un valor Booleano que recibe true o false.
     */
    fun confirmar(opcion: Boolean) {
        _showDialog.value = opcion
        if (!opcion) {
            _nombreJugador1.value = ""
            _nombreJugador2.value = ""
        }
    }

    /**
     * Recibe el nombre del jugador que se inscribe en el recuadro de inscripción.
     * Si el valor de uno de los nombres es nulo o vacio el valor de la variable _botonInicio es false.
     * @param id id del jugador.
     * @param nombre nombre del jugador.
     */

    fun jugadorInscribir(id: Int, nombre: String) {
        if (id == 1) {
            _nombreJugador1.value = nombre
        } else {
            _nombreJugador2.value = nombre
        }

        _botonInicio.value =
            !(_nombreJugador1.value.isNullOrEmpty() || _nombreJugador2.value.isNullOrEmpty())
    }


    /**
     * Se crea el valor inicial de los jugadores una vez que se acepta en el recuadro de inscripción.
     */
    fun inicio() {
        jugador1.value = Jugador(_nombreJugador1.value!!, 100, mutableListOf(), 0)
        jugador2.value = Jugador(_nombreJugador2.value!!, 100, mutableListOf(), 0)
        _idJuego.value = turno()
        confirmar(false)
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
            if(i.puntosMin != i.puntosMax && (puntosActuales - i.puntosMin + i.puntosMax) <= 21){
                puntosActuales -= i.puntosMin
                puntosActuales += i.puntosMax
            }

        }
        if (id == 1) {
            jugador1.value!!.puntos = puntosActuales
            _puntos.value = puntosActuales.toString()
            if (jugador1.value!!.puntos > 21) {
                _enableButton.value = false
            }
            ganador()
        } else {
            jugador2.value!!.puntos = puntosActuales
            _puntos2.value = puntosActuales.toString()
            if (jugador2.value!!.puntos > 21) {
                _enableButton2.value = false
            }
            ganador()
        }
    }


    /**
     * Activa o desactiva el cuadro de diálogo que indica el ganador de cada partida, no del juego.
     * @param opcion recibe true o false para activar o desactivar el cuadro.
      */

    fun mensajeFinPartida(opcion: Boolean) {
        _mostrarMensaje.value = opcion
        _enable.value = false
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
                1 -> {
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

                2 -> {
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

            }
        }
    }

    fun maquina(){
        var suma = 0
        while(Baraja.baraja.isNotEmpty()){
            jugador2.value!!.manoCartas.add(Baraja.dameCarta())
            var bandera = false
            for(i in jugador2.value!!.manoCartas){
                suma += i.puntosMax
                if(suma > 18){
                    bandera = true
                }
            }
            if(bandera) {
                break
            }
        }
        if(suma == 21){
            _apuestaTotal.value = 25
        }else _apuestaTotal.value = 5
    }

    /**
     * Activa la opción de plantarse si se cumplen las condiciones.
     * @param id indica la id del jugador.
     */
    fun plantarse(id: Int) {
        if (id == 1) {
            if (jugador1.value!!.puntos < 15) Toast.makeText(
                context,
                "No puede plantarse con una puntución menor de 15",
                Toast.LENGTH_SHORT
            ).show() else {
                _enableButton.value = false
                _idJuego.value = 2
            }
        } else {
            if (jugador2.value!!.puntos < 15) Toast.makeText(
                context,
                "No puede plantarse con una puntución menor de 15",
                Toast.LENGTH_SHORT
            ).show() else {
                _enableButton2.value = false
                _idJuego.value = 1
            }

        }
    }

    /**
     * Calcula cual es el jugador que gana la partida.
     * Se desactiva los botones de pedir carta y apostar.
     */

    fun ganador() {
        if (_enableButton.value == false && _enableButton2.value == false) {
            when {
                jugador1.value!!.puntos <= 21 && jugador1.value!!.puntos > jugador2.value!!.puntos -> {
                    mensajeFinPartida(true)
                    jugador1.value!!.fichas += _apuestaTotal.value!!
                    _apuestaTotal.value = 0
                    _fichas.value = 0
                    _fichas2.value = 0
                    _mensaje.value =
                        "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
                }

                jugador1.value!!.puntos > 21 && jugador1.value!!.puntos < jugador2.value!!.puntos -> {
                    mensajeFinPartida(true)
                    jugador1.value!!.fichas += _apuestaTotal.value!!
                    _apuestaTotal.value = 0
                    _fichas.value = 0
                    _fichas2.value = 0
                    _mensaje.value =
                        "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
                }

                (jugador1.value!!.puntos == jugador2.value!!.puntos) && contador.value!! > contador2.value!! -> {
                    mensajeFinPartida(true)
                    _mensaje.value =
                        "Felicidades has ganado ${jugador1.value!!.nombre}\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos\nnúmero de cartas 1: ${contador.value}\nnúmero de cartas 2: ${contador2.value}"
                }

                jugador1.value!!.puntos == jugador2.value!!.puntos -> {
                    mensajeFinPartida(true)
                    _mensaje.value =
                        "¡¡¡EMPATE!!!: ${jugador1.value!!.puntos} puntos\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos"
                }

                else -> {
                    mensajeFinPartida(true)
                    jugador2.value!!.fichas += _apuestaTotal.value!!
                    _apuestaTotal.value = 0
                    _fichas.value = 0
                    _fichas2.value = 0
                    _mensaje.value =
                        "Felicidades has ganado ${jugador2.value!!.nombre}\n${jugador2.value!!.nombre}: ${jugador2.value!!.puntos} puntos\n${jugador1.value!!.nombre}: ${jugador1.value!!.puntos} puntos"
                }
            }
            if(jugador1.value!!.fichas == 0 || jugador2.value!!.fichas == 0 ){
                if(jugador1.value!!.fichas > jugador2.value!!.fichas) {
                    _ganador.value = jugador1.value!!.nombre
                }else{
                    _ganador.value = jugador2.value!!.nombre
                }
                _fin.value = true
            }
        }
    }

    /**
     * Se llama para desactivar el cuadro de diálogo de la apuesta.
     */
    fun desactivarApuesta() {
        _apuesta.value = false
    }

    /**
     * Incrementa o decrementa el número de fichas a apostar.
     * @param signo Dependiendo del signo incrementa o decrementa.
     * @param id Indica la id del jugador.
     */

    fun apostar(signo: String, id: Int) {
        var fichasActuales = _apuestaTotal.value ?: 0
        var fichas1 = _fichas.value ?: 0
        var fichas2 = _fichas2.value ?: 0
            if (id == 1) {
                val nuevoValor: Int
                val nuevo1: Int
                if (signo == "+") {
                    if(jugador1.value!!.fichas == 0){
                        Toast.makeText(
                            context,
                            "Tienes el máximo de fichas.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        fichasActuales += 1
                        fichas1 += 1
                        nuevoValor = fichasActuales
                        nuevo1 = fichas1
                        jugador1.value!!.fichas -= 1
                        _apuestaTotal.value = nuevoValor
                        _fichas.value = nuevo1
                    }
                } else {
                    if((_fichas.value ?: 0) == 0){
                        Toast.makeText(
                            context,
                            "Lo siento te has quedado sin fichas",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        fichasActuales -= 1
                        fichas1 -= 1
                        nuevoValor = fichasActuales
                        nuevo1 = fichas1
                        jugador1.value!!.fichas += 1
                        _apuestaTotal.value = nuevoValor
                        _fichas.value = nuevo1
                    }

                }

            } else {

            val nuevoValor: Int
            val nuevo2: Int
            if (signo == "+") {
                if(jugador2.value!!.fichas == 0){
                    Toast.makeText(
                        context,
                        "Tienes el máximo de fichas.",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    fichasActuales += 1
                    fichas2++
                    nuevoValor = fichasActuales
                    nuevo2 = fichas2
                    jugador2.value!!.fichas -= 1
                    _apuestaTotal.value = nuevoValor
                    _fichas2.value = nuevo2
                }
            } else {
                if((_fichas2.value ?: 0) == 0) {
                    Toast.makeText(
                        context,
                        "Lo siento te has quedado sin fichas",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    fichasActuales -= 1
                    fichas2--
                    nuevoValor = fichasActuales
                    nuevo2 = fichas2
                    jugador2.value!!.fichas += 1
                    _apuestaTotal.value = nuevoValor
                    _fichas2.value = nuevo2
                }
            }
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
     * Reinicia todos los atributos a su valor de origen.
     */

    fun reiniciar() {
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
        nuevaBaraja()
    }

    /**
     * Sale de aplicación.
     */

    fun finPartida(){
        exitProcess(0)
    }

}

