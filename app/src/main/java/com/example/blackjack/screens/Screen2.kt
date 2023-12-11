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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.blackjack.R
import com.example.blackjack.data.Carta
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.Dialog
import com.example.blackjack.data.Routes


/**
 * Función principal donde se incia el juego y realiza las acciones principales.
 */
//@Preview(showBackground = true)
@Suppress("SpellCheckingInspection")

@Composable
//navController: NavHostController, Viewmodel: Viewmodel
fun Screen2(navController: NavHostController, viewmodel: Viewmodel){
    val enable: Boolean by viewmodel.enable.observeAsState(true)
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
        Tapete(R.drawable.tapten)
        MyButton(onClick = { viewmodel.reiniciar() }, enabled =  !enable, texto = "Reiniciar")
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
                    IndicadorJugador2(viewmodel)
                    IndicardorPuntos2(viewmodel)
                    Monedas()
                    Indicardorfichas2(viewmodel)
                }
                if(idJuego == 2){
                    Botones2(viewmodel, enableButton2, 2)
                    MostrarCartasEnMano2(viewmodel)
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
                    MostrarCartasEnMano(viewmodel)
                    Botones(viewmodel, enableButton, 1)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IndicadorJugador1(viewmodel)
                    IndicardorPuntos1(viewmodel)
                    Monedas()
                    Indicardorfichas1(viewmodel)
                }
            }
        }
    }
}

@Composable
fun Tapete(@DrawableRes tapeteID: Int){
    Image(painter = painterResource(id = tapeteID),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds)
}

@Composable
fun Monedas(){
    Image(painter = painterResource(id = R.drawable.monedas),
        contentDescription = "Monedas del juego",
        modifier = Modifier
            .height(30.dp)
            .width(30.dp)
            .padding(2.dp))
}

@Composable
fun IndicadorJugador1(viewmodel: Viewmodel){
    Text(text = viewmodel.mostrarNombre(1),
        color = Color.White,
        modifier = Modifier.padding(top = 4.dp, end = 10.dp),
        fontSize = 10.sp)
}
@Composable
fun IndicadorJugador2(viewmodel: Viewmodel){
    Text(text = viewmodel.mostrarNombre(2),
        color = Color.White,
        modifier = Modifier.padding(10.dp),
        fontSize = 10.sp)
}


@Composable
fun IndicardorPuntos1(viewmodel: Viewmodel) {
    val puntos: String by viewmodel.puntos.observeAsState("0")
    Text(text = puntos,
        color = Color.White,
        modifier = Modifier.padding(top = 4.dp),
        fontSize = 10.sp)
}

@Composable
fun IndicardorPuntos2(viewmodel: Viewmodel) {
    val puntos: String by viewmodel.puntos2.observeAsState("0")

    Text(text = puntos,
        color = Color.White,
        modifier = Modifier.padding(top = 10.dp),
        fontSize = 10.sp)
}

@Composable
fun Indicardorfichas1(viewmodel: Viewmodel) {
    Text(text = viewmodel.mostrarFichas(1).toString(),
        color = Color.White,
        modifier = Modifier.padding(top = 4.dp),
        fontSize = 10.sp)
}

@Composable
fun Indicardorfichas2(viewmodel: Viewmodel) {
    Text(text = viewmodel.mostrarFichas(2).toString(),
        color = Color.White,
        modifier = Modifier.padding(top = 10.dp),
        fontSize = 10.sp)
}

@Composable
fun Botones(viewmodel: Viewmodel, enable: Boolean, id: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        MyButton(onClick = { viewmodel.activarApuesta(1) }, enabled = enable, texto = "Apostar")
        MyButton(onClick = { viewmodel.pedirCarta(id)}, enabled = enable, texto = "PedirCarta")
        MyButton(onClick = { viewmodel.plantarse(1)
            viewmodel.ganador()
            viewmodel.desactivarApuesta()}, enabled = true, texto = "Plantarse")
    }
}

@Composable
fun Botones2(viewmodel: Viewmodel, enable: Boolean, id: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        MyButton(onClick = {viewmodel.activarApuesta(2) }, enabled = enable, texto = "Apostar")
        MyButton(onClick = { viewmodel.pedirCarta(id) }, enabled = enable, texto = "PedirCarta")
        MyButton(onClick = { viewmodel.plantarse(2)
            viewmodel.ganador()
            viewmodel.desactivarApuesta()}, enabled = true, texto = "Plantarse")
    }
}

@Composable
fun MyButton(
    onClick: ()-> Unit,
    enabled: Boolean,
    texto: String
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.padding(3.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(Color(0xFFA54A2D))
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

@Composable
fun MostrarCartasEnMano(viewmodel: Viewmodel) {
    val cartasEnMano: List<Carta> by viewmodel.cartasEnMano.observeAsState(emptyList())
    val contador: Int by viewmodel.contador.observeAsState(0)


    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        item { for(i in cartasEnMano){
            Spacer(modifier = Modifier.width(32.dp))
            MostrarCarta(i, contador)
           // MostrarCarta(i)
        } }
    }

}

@Composable
fun MostrarCartasEnMano2(viewmodel: Viewmodel) {
    val cartasEnMano: List<Carta> by viewmodel.cartasEnMano2.observeAsState(emptyList())
    val contador2: Int by viewmodel.contador2.observeAsState(0)


    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        item { for(i in cartasEnMano){
            Spacer(modifier = Modifier.width(32.dp))
            MostrarCarta(i, contador2)
            // MostrarCarta(i)
        } }
    }

}

@Composable
fun MensajeFinPartida(viewmodel: Viewmodel){
    val mostrarMensaje: Boolean by viewmodel.mostrarMensaje.observeAsState(initial = false)
    val mensaje: String by viewmodel.mensaje.observeAsState(initial = "")
    if(mostrarMensaje){
        Dialog(onDismissRequest = { viewmodel.mensajeFinPartida(false)}){
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(text = mensaje)
                Spacer(modifier = Modifier.height(16.dp))
                Salir(viewmodel)
            }
        }
    }
}

@Composable
fun Salir(viewmodel: Viewmodel){
    Button(
        onClick = { viewmodel.mensajeFinPartida(false)}) {
        Text(text = "Salir")
    }
}


@Composable
fun DialogoSiNo(navController: NavHostController, viewmodel: Viewmodel){
    val jugador: String by viewmodel.ganador.observeAsState(initial = "")
    Dialog(onDismissRequest = {}){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "¡¡¡Felicidade $jugador has ganado!!!\n¿Desea jugar otra partida? S/N", modifier = Modifier.background(color = Color.White))
            Spacer(modifier = Modifier.height(32.dp))
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { navController.navigate(Routes.Screen1.route) }) {
                    Text(text = "SI", fontSize = 15.sp)
                }
                Spacer(modifier = Modifier.height(32.dp))
                Button(onClick = { viewmodel.finPartida() }) {
                    Text(text = "NO", fontSize = 15.sp)
                }
            }
        }

    }

}


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
                    Row(horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically) {
                        Column(verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally) {
                            Button(onClick = { viewmodel.apostar("+", idApuesta) }) {
                                Text(text = "+", fontSize = 15.sp)
                            }
                            Spacer(modifier = Modifier.height(32.dp))
                            Button(onClick = { viewmodel.apostar("-", idApuesta) }) {
                                Text(text = "-", fontSize = 15.sp)
                            }
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(text = fichasJugador, modifier = Modifier
                            .width(100.dp)
                            .background(color = Color.White))
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(onClick = { viewmodel.desactivarApuesta() }) {
                        Text(text = "Apostar", fontSize = 15.sp)
                    }
                }
            }
        }
    }
}
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
/*

@Composable
fun MostrarCartasEnMano(context: Context, cartasEnMano: List<String>, contador: Int) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LazyRow(
        ) {
            item { for(i in cartasEnMano){
                Spacer(modifier = Modifier.width(32.dp))
                MostrarCarta(i, context)
            } }
        }
    }


}
@Composable
fun MyButton(
    onClick: ()-> Unit,
    enabled: Boolean,
    texto: String
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.padding(3.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(Color(0xFFA54A2D))
    ) {
        Text(text = texto)
    }
}
@Composable
fun Botones(
    Apostar: () -> Unit,
    PedirCarta: () -> Unit,
    enabled: Boolean,
    Reiniciar: () -> Unit,
    Plantarse: () -> Unit
){

    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 25.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row{
            //MyButton(onClick = { Apostar() }, texto = "Apostar")
            MyButton(onClick = { PedirCarta() }, enabled = enabled, texto = "PedirCarta")
            MyButton(onClick = { Reiniciar() }, enabled =  true, texto = "Reiniciar")
            MyButton(onClick = { Plantarse() }, enabled = enabled, texto = "Plantarse")
        }
    }

}


 */



/*
Column(
        Modifier.fillMaxSize().padding(bottom = 70.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        LazyRow(
            content = {
                items(cartasEnMano.size) { index ->
                    Spacer(modifier = Modifier.width(32.dp))
                    Image(
                        painter = painterResource(id = context.resources.getIdentifier(cartasEnMano[index], "drawable", context.packageName)),
                        contentDescription = "Carta mostrada",
                        modifier = Modifier
                            .height(150.dp)
                            .width(75.dp)
                    )
                }
            }
        )


    }




LazyColumn(
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
) {
    items(cartasEnMano.size) { index ->
        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre las cartas (puedes ajustarlo)
        Image(
            painter = painterResource(id = context.resources.getIdentifier(cartasEnMano[index], "drawable", context.packageName)),
            contentDescription = "Carta mostrada",
            modifier = Modifier
                .height(150.dp)
                .width(75.dp)
        )
    }
}

*/

/*
@Preview(showBackground = true)
@Composable
fun Juego(){
    val context = LocalContext.current
    var carta by rememberSaveable { mutableStateOf("vuelta") }
    var baraja by rememberSaveable { mutableStateOf(Baraja.creaBaraja()) }
    val mazo by rememberSaveable { mutableStateOf("vuelta") }
    var cartasEnMano by rememberSaveable { mutableStateOf(mutableListOf(carta, carta))}
    var show  by rememberSaveable { mutableStateOf(true) }
    var contador by rememberSaveable { mutableStateOf(0) }
    val jugador = Jugador("Paco")
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.tapete),
            contentScale = ContentScale.FillBounds
        )) {
        val (box1, box2, box3, box4) = createRefs()
        val topGuide = createGuidelineFromTop(0.7f)
        val topGuide2 = createGuidelineFromBottom(0.7f)
        Caja1(box1 = createRef(), topGuide = topGuide)

        Box(
            modifier = Modifier
                .constrainAs(box2){
                    top.linkTo(topGuide)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ){
            MostrarCartasEnMano(context, cartasEnMano)
        }
        Box(
            modifier = Modifier
                .constrainAs(box4){
                    bottom.linkTo(topGuide2)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ){
            MostrarCarta(carta, context)
        }
        Box(
            modifier = Modifier.
            constrainAs(box3){
                top.linkTo(box2.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ){
            Botones(
                Apostar = {},
                PedirCarta = {carta = if (baraja.isEmpty()) {
                    Toast.makeText( context,"La baraja está vacía ", Toast.LENGTH_SHORT).show()
                    show = false
                    "vuelta"
                } else {
                    val cartaDada = Baraja.dameCarta(baraja)
                    jugador.recibirCarta(cartaDada)
                    "${cartaDada.nombre}_${cartaDada.palo}".lowercase()
                }

                    if(contador>1) cartasEnMano.add(carta)
                    cartasEnMano = cartasEnMano.toMutableList().apply { set(contador, carta) }
                    contador ++},
                Reiniciar = {baraja = Baraja.creaBaraja()
                    show = true
                    carta = "vuelta"
                    cartasEnMano = mutableListOf("vuelta", "vuelta")
                    contador = 0
                },
                Plantarse = {}
            )

                /*
                Row {
                    // Al pulsarlo nos muestra la siguiente carta, si la baraja está vacia nos devuelve un mensaje
                    Button(onClick = { carta = if (baraja.isEmpty()) {
                        Toast.makeText( context,"La baraja está vacía ", Toast.LENGTH_SHORT).show()
                        show = false
                        "vuelta"
                    } else {
                        val cartaDada = Baraja.dameCarta(baraja)
                        "${cartaDada.nombre}_${cartaDada.palo}".lowercase()
                    }}, modifier = Modifier.padding(3.dp),
                        colors = ButtonDefaults.buttonColors(Color.Yellow)) {
                        Text(text = "Dar carta")
                    }
                    // Al pulsarlo se baraja de nuevo y se pone el mazo de nuevo en la mesa.
                    Button(onClick = {baraja = Baraja.creaBaraja()
                        show = true
                        carta = "vuelta"}, modifier = Modifier.padding(3.dp),
                        colors = ButtonDefaults.buttonColors(Color.Yellow)
                    ) {
                        Text(text = "Reiniciar", modifier = Modifier)
                    }
                }

                 */

        }
    }
}

/**
 * Muestra la carta indicada
 * @param carta Recibe un String con el nombre de la carta a mostrar.
 * @param context Recibe el contexto.
 */
@Suppress("SpellCheckingInspection")
@Composable
fun MostrarCarta(
    carta: String,
    context: Context
){
    Image(painter = painterResource(id = context.resources.getIdentifier(carta, "drawable", context.packageName) ),
        contentDescription = "Carta mostrada",
        modifier = Modifier
            .height(150.dp)
            .width(75.dp)
    )
}

 */

/*
@Composable
fun Caja1(box1: ConstrainedLayoutReference, topGuide: ConstraintLayoutBaseScope.HorizontalAnchor){
    Box(
        modifier = Modifier
            .constrainAs(box1){
                bottom.linkTo(topGuide)
                start.linkTo(parent.start)
            }
    ){
        // Mientras se true nos muestra el mazo y baraja las cartas.
        Show(show, mazo, context, baraja)
        /*
        if(show) {
            MostrarCarta(mazo, context)
            Baraja.barajar(baraja)
        }

         */

    }
}
@Composable
fun MostrarCartasEnMano(context: Context, cartasEnMano: List<String>) {
    LazyRow(
    ) {
        item { for(i in cartasEnMano){
            Spacer(modifier = Modifier.width(32.dp))
            MostrarCarta(i, context)
        } }
    }
}
@Composable
fun Show(
    show: Boolean,
    mazo: String,
    contexto: Context,
    baraja: MutableList<Carta>
){
    if(show) {
        MostrarCarta(mazo, contexto)
        Baraja.barajar(baraja)
    }
}
@Composable
fun Botones(
    Apostar: () -> Unit,
    PedirCarta: () -> Unit,
    Reiniciar: () -> Unit,
    Plantarse: () -> Unit
){
    LazyRow {
        item { MyButton(onClick = { Apostar() }, texto = "Apostar")
            MyButton(onClick = { PedirCarta() }, texto = "PedirCarta")
            MyButton(onClick = { Reiniciar() }, texto = "Plantarse")
            MyButton(onClick = { Plantarse() }, texto = "Reiniciar") }
    }
}
@Composable
fun MyButton(
    onClick: ()-> Unit,
    texto: String
){
    Button(onClick = { onClick() },
        modifier = Modifier.padding(3.dp),
        colors = ButtonDefaults.buttonColors(Color.Yellow)) {
        Text(text = texto)
    }
}

@Composable
fun jugador(
    carta: Carta
): MutableList<Carta>{
    val jugador = Jugador("Paco")
    jugador.recibirCarta(carta)
    return jugador.verMano()
}

 */
