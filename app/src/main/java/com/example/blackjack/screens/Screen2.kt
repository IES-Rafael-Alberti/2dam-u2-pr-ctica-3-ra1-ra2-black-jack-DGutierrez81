package com.example.blackjack.screens
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.blackjack.R
import com.example.blackjack.data.Carta
import com.example.blackjack.data.Routes


/**
 * Pantalla de la vista principal del juego en modo de dos jugadores
 * @param navController permite navegar por las distintas pantallas.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 */
//@Preview(showBackground = true)
@Suppress("SpellCheckingInspection")

@Composable
//navController: NavHostController, Viewmodel: Viewmodel
fun Screen2(navController: NavHostController, viewmodel: Viewmodel){
    val enableButton: Boolean by viewmodel.enableButton.observeAsState(true)
    val enableButton2: Boolean by viewmodel.enableButton2.observeAsState(true)
    val idJuego: Int by viewmodel.idJuego.observeAsState(initial = 0)
    val show: Boolean by viewmodel.show.observeAsState(initial = true)
    val apuesta: Boolean by viewmodel.apuesta.observeAsState(initial = false)
    val fin: Boolean by viewmodel.fin.observeAsState(initial = false)
    if(fin){
        Tapete(R.drawable.casiex)
        DialogoSiNo(navController, viewmodel)
    }else{
        Tapete(R.drawable.tapetin3)
        MensajeFinPartida(viewmodel)
        VistaApuesta(viewmodel, apuesta)
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1.2f),
            ) {
                Row {
                    Icono(R.drawable.carita)
                    IndicadorJugador(viewmodel, 2)
                    IndicardorPuntos(viewmodel, 2)
                    Icono(R.drawable.monedas)
                    Indicardorfichas(viewmodel, 2)
                }
                if(idJuego == 2){
                    Botones(viewmodel, enableButton2, 2)
                    MostrarCartasEnMano(viewmodel, 2)
                }

            }

            if(show){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(start = 25.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    MostrarMazo()
                }
            }
            Column(
                Modifier
                    .fillMaxSize()
                    .weight(1.2f),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(idJuego == 1){
                    MostrarCartasEnMano(viewmodel, 1)
                    Botones(viewmodel, enableButton, 1)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icono(R.drawable.jugador1)
                    IndicadorJugador(viewmodel, 1)
                    IndicardorPuntos(viewmodel, 1)
                    Icono(R.drawable.monedas)
                    Indicardorfichas(viewmodel, 1)
                }
            }
        }
    }
}

/**
 * Función que es utilizada para cargar las distintas imagenes del juego.
 * @param tapeteID contiene la dirección donde se ubica la imagen.
 */
@Composable
fun Tapete(@DrawableRes tapeteID: Int){
    Image(painter = painterResource(id = tapeteID),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds)
}

/**
 * Representa la imagen de los jugadores.
 * @param iconoID contiene la dirección de la imagen del icono.
 */
@Composable
fun Icono(@DrawableRes iconoID: Int){
    Image(painter = painterResource(id = iconoID),
        contentDescription = "Avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(30.dp)
            .width(30.dp)
            .padding(2.dp)
            .clip(CircleShape))
}

/**
 * Muestra el nombre del jugador dependiendo de la ID que tenga.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 * @param id contiene la ID del jugador a nombrar.
 */
@Composable
fun IndicadorJugador(viewmodel: Viewmodel, id: Int){
    Text(text = viewmodel.mostrarNombre(id),
        color = Color.White,
        modifier = Modifier.padding(top = 4.dp, end = 10.dp),
        fontSize = 10.sp)
}

/**
 * Muestra los puntos que tiene cada jugador.
 * * @param viewmodel componente Viewmodel para poder cargar la función.
 *  * @param id contiene la ID del jugador.
 */

@Composable
fun IndicardorPuntos(viewmodel: Viewmodel, id: Int) {
    val puntos: String by viewmodel.puntos.observeAsState("0")
    val puntos2: String by viewmodel.puntos2.observeAsState("0")
    Text(text = if(id == 1)puntos else puntos2,
        color = Color.White,
        modifier = Modifier.padding(top = 4.dp),
        fontSize = 10.sp)
}

/**
 * Muestra el número de fichas que tiene cada jugador.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 * @param id contiene la ID del jugador.
 */
@Composable
fun Indicardorfichas(viewmodel: Viewmodel, id: Int) {
    Text(text = viewmodel.mostrarFichas(id).toString(),
        color = Color.White,
        modifier = Modifier.padding(top = 4.dp),
        fontSize = 10.sp)
}

/**
 * Contienen los botones principales del juego.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 * @param enable recibe true o false permitiendo o no la visibilidad de los botones.
 * @param id contiene la ID del jugador.
 */
@Composable
fun Botones(viewmodel: Viewmodel, enable: Boolean, id: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        MyButton(onClick = { viewmodel.activarApuesta(id) }, enabled = enable, texto = "Apostar")
        MyButton(onClick = { viewmodel.pedirCarta(id)}, enabled = enable, texto = "PedirCarta")
        MyButton(onClick = { viewmodel.plantarse(id)
            viewmodel.ganadorMano()
            viewmodel.desactivarApuesta()}, enabled = enable, texto = "Plantarse")
    }
}

/**
 * Función que se utiliza para hacer los botones funcionales del juego.
 * @param onClick recibe la funcionabilidad de cada botón.
 * @param enabled recibe true o false permitiendo o no la visibilidad de los botones.
 * @param texto texto que indica que funcionabilidad tiene cada botón.
 */
@Composable
fun MyButton(
    onClick: ()-> Unit,
    enabled: Boolean,
    texto: String
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.padding(3.dp),
        shape =  RoundedCornerShape(80),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(Color(0xFF07A717))
    ) {
        Text(text = texto)
    }
}


/**
 * Muestra la carta indicada
 * @param carta Recibe un String con el nombre de la carta a mostrar.
 * @param baraja Recibe el contexto.
 */



@Composable
//fun MostrarCarta(carta: Carta) {
fun MostrarCarta(carta: Carta, baraja: Int) {
    Box(
        modifier = Modifier
    ) {
        Row {
            Image(painter = painterResource(id = carta.idDrawable),
                contentDescription = "Carta mostrada",
                modifier = Modifier
                    .height(150.dp)
                    .width(75.dp)
            )
        }
    }
}

/**
 * Recorre la mano de cartas de cada jugador para poder ser mostradas.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 * @param id contiene la ID del jugador.
 */

@Composable
fun MostrarCartasEnMano(viewmodel: Viewmodel, id: Int) {
    val cartasEnMano: List<Carta> by viewmodel.cartasEnMano.observeAsState(emptyList())
    val contador: Int by viewmodel.contador.observeAsState(0)
    val cartasEnMano2: List<Carta> by viewmodel.cartasEnMano2.observeAsState(emptyList())
    val contador2: Int by viewmodel.contador2.observeAsState(0)

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        item { for(i in if(id == 1)cartasEnMano else cartasEnMano2){
            Spacer(modifier = Modifier.width(32.dp))
            MostrarCarta(i, if(id == 1)contador else contador2)
        } }
    }

}

/**
 * Muestra la pantalla de diálogo cuando termina una mano en la partida.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 */
@Composable
fun MensajeFinPartida(viewmodel: Viewmodel){
    val mostrarMensaje: Boolean by viewmodel.mostrarMensaje.observeAsState(initial = false)
    val mensaje: String by viewmodel.mensaje.observeAsState(initial = "")
    if(mostrarMensaje){
        Dialog(onDismissRequest = { viewmodel.mensajeFinPartida(false)}){
            Caratula(R.drawable.tapetin)
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = mensaje, color = Color(0xFF2FD836),
                    modifier = Modifier.background(Color.White)
                )
                MostrarCartasEnMano(viewmodel, id = 2)
                Spacer(modifier = Modifier.height(32.dp))
                MostrarCartasEnMano(viewmodel, id = 1)
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd){
                    Salir(viewmodel)
                }
            }
        }
    }
}

/**
 * Indica un botón que permite salir de la pantalla de mensaje de fin de partida y reinicia la misma.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 */
@Composable
fun Salir(viewmodel: Viewmodel){
    Button(
        onClick = { viewmodel.mensajeFinPartida(false)},colors = ButtonDefaults.buttonColors(Color(0xFF07A717))) {
        Text(text = "Continuar")
    }
}

/**
 * Muestra la pantalla de final de partidad  permitiendo la posibilidad de jugar otra o salir de juego.
 * @param navController permite navegar por las distintas pantallas.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 */
@Composable
fun DialogoSiNo(navController: NavHostController, viewmodel: Viewmodel){
    val jugador: String by viewmodel.ganador.observeAsState(initial = "")
    Dialog(onDismissRequest = {}){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text =if(jugador == "BlackJackneitor") "Lo siento te ha ganado $jugador\\n¿Desea jugar otra partida? S/N\"" else "¡¡¡Felicidade $jugador has ganado!!!\n¿Desea jugar otra partida? S/N", style = TextStyle(fontSize = 20.sp,
                shadow = Shadow(color = Color.Green,
                    offset = Offset(5.0f, 10.0f),
                    blurRadius = 3f)
            ))
            Spacer(modifier = Modifier.height(32.dp))
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { navController.navigate(Routes.Screen1.route) }, colors = ButtonDefaults.buttonColors(Color(0xFF07A717))) {
                    Text(text = "SI", fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(50.dp))
                Button(onClick = { viewmodel.finPartida()  }, colors = ButtonDefaults.buttonColors(Color(0xFF07A717))) {
                    Text(text = "NO", fontSize = 15.sp)
                }
            }
        }

    }

}

/**
 * Muestra la pantalla que permite realizar la apuesta a cada jugador.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 * @param apuesta recibe un true o false que permite mostrar o no la vista de la pantalla,
 */

@Composable
fun VistaApuesta(viewmodel: Viewmodel, apuesta: Boolean){
    val fichas: Int by viewmodel.fichas.observeAsState(0)
    val fichas2: Int by viewmodel.fichas2.observeAsState(0)
    val apuestaTotal: Int by viewmodel.apuestaTotal.observeAsState(initial = 0)
    val idApuesta: Int by viewmodel.idApuesta.observeAsState(0)
    val fichasJugador: String
    val nombre: String
    if(idApuesta == 1){
        fichasJugador = fichas.toString()
        nombre = viewmodel.mostrarNombre(1)
    }else{
        fichasJugador = fichas2.toString()
        nombre = viewmodel.mostrarNombre(2)
    }
    if(apuesta){
        Dialog(onDismissRequest = {}){
            Tapete(R.drawable.apuesta)
            Column {
                Box(modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.TopEnd){
                    Row {
                        Box(modifier = Modifier.width(100.dp),
                            contentAlignment = Alignment.TopStart){
                            Text(text = nombre, color = Color.White,fontSize = 15.sp)
                        }
                        Text(text = "Total", color = Color.White,fontSize = 15.sp)
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(text = apuestaTotal.toString(), modifier = Modifier
                            .width(100.dp)
                            .background(color = Color.White))
                    }
                }
                Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(modifier = Modifier
                        .fillMaxSize()
                        .weight(1.2f),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(onClick = { viewmodel.apostar("+", idApuesta) },colors = ButtonDefaults.buttonColors(Color(0xFF07A717))) {
                                Text(text = "+", fontSize = 15.sp)
                            }
                            Spacer(modifier = Modifier.height(32.dp))
                            Button(onClick = { viewmodel.apostar("-", idApuesta) },colors = ButtonDefaults.buttonColors(Color(0xFF07A717))) {
                                Text(text = "-", fontSize = 15.sp)
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = fichasJugador, modifier = Modifier
                            .width(50.dp),
                            color = Color.White)
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Box(modifier = Modifier.fillMaxWidth(),contentAlignment = Alignment.BottomEnd){
                        Button(onClick = { viewmodel.desactivarApuesta() },colors = ButtonDefaults.buttonColors(Color(0xFF07A717))) {
                            Text(text = "Apostar", fontSize = 15.sp)
                        }
                    }
                }
            }
        }
    }
}

/**
 * Muestra el mazo de carta mientras haya cartas.
 */
@Composable
fun MostrarMazo(
) {
    Image(
        painter = painterResource(id = R.drawable.reverso2),
        contentDescription = "Carta mostrada",
        modifier = Modifier
            .height(150.dp)
            .width(75.dp)
    )
}