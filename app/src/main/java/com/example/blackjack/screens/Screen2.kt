package com.example.blackjack.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.blackjack.R


/**
 * Función principal donde se incia el juego y realiza las acciones principales.
 */
//@Preview(showBackground = true)
@Suppress("SpellCheckingInspection")

@Composable
//navController: NavHostController, Viewmodel: Viewmodel
fun Screen2(navController: NavHostController, Viewmodel: Viewmodel){
    // val carta: String by Viewmodel.carta.observeAsState(initial = "reverso2")
    //  val show: Boolean by Viewmodel.show.observeAsState(initial = true)
    Tapete(R.drawable.tapten)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
        ) {
            Row {
                IndicadorJugador2(Viewmodel)
                Monedas()
                IndicardorPuntos2(Viewmodel)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Botones()
            }
            MostrarCartasEnMano()
        }

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
        Column(
            Modifier
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MostrarCartasEnMano()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Botones()
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IndicardorPuntos1(Viewmodel)
                Monedas()
                IndicadorJugador1(Viewmodel)
            }
        }
    }
}

@Composable
fun Jugador(Viewmodel: Viewmodel){
    Row(){

    }
}



//@Preview(showBackground = true)
/*
@Composable
fun Juego(Viewmodel: Viewmodel){
    val show: Boolean by Viewmodel.show.observeAsState(initial = true)
    val context = LocalContext.current
    var baraja by rememberSaveable { mutableStateOf(Baraja.creaBaraja()) }
    var cartasEnMano by rememberSaveable { mutableStateOf(mutableListOf(carta, carta))}

    var contador by rememberSaveable { mutableStateOf(0) }
    var enabled by rememberSaveable { mutableStateOf(true)}

    if(show) {
        MostrarMazo("reverso2", context)
        Baraja.barajar(baraja)
    }

 */

/*
    Tapete(R.drawable.tapten)
    MostrarCartasEnMano(context, cartasEnMano, contador)
    if(show) {
        MostrarMazo("reverso2", context)
        Baraja.barajar(baraja)
    }


}

 */



@Composable
fun Tapete(@DrawableRes tapeteID: Int){
    Image(painter = painterResource(id = tapeteID),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
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
    Text(text = viewmodel.MostrarNombre(1),
        color = Color.White,
        modifier = Modifier.padding(top = 4.dp, end = 10.dp),
        fontSize = 10.sp)
}
@Composable
fun IndicadorJugador2(viewmodel: Viewmodel){
    Text(text = viewmodel.MostrarNombre(2),
        color = Color.White,
        modifier = Modifier.padding(10.dp),
        fontSize = 10.sp)
}


@Composable
fun IndicardorPuntos1(Viewmodel: Viewmodel) {
    val puntos = Viewmodel.MostrarPuntos(1).toString()
    Text(text = puntos,
        color = Color.White,
        modifier = Modifier.padding(top = 4.dp),
        fontSize = 10.sp)
}

@Composable
fun IndicardorPuntos2(Viewmodel: Viewmodel) {
    val puntos = Viewmodel.MostrarPuntos(2).toString()
    Text(text = puntos,
        color = Color.White,
        modifier = Modifier.padding(top = 10.dp),
        fontSize = 10.sp)
}

@Composable
fun Botones(){
    MyButton(onClick = {  }, enabled = true, texto = "Apostar")
    MyButton(onClick = {  }, enabled = true, texto = "PedirCarta")
    //MyButton(onClick = { Reiniciar() }, enabled =  true, texto = "Reiniciar")
    MyButton(onClick = {  }, enabled = true, texto = "Plantarse")
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
 * @param context Recibe el contexto.
 */
//@Suppress("SpellCheckingInspection")


@Composable
fun MostrarCarta(carta: String, zIndex: Int) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .zIndex(zIndex.toFloat()) // Ajusta el índice Z para superponer las cartas
    ) {
        Image(painter = painterResource(id = context.resources.getIdentifier(carta, "drawable", context.packageName)),
            contentDescription = "Carta mostrada",
            modifier = Modifier
                .height(150.dp)
                .width(75.dp)
        )
    }
}


/*
@Composable
fun MostrarCarta(carta: String
){
    val context = LocalContext.current

    Image(painter = painterResource(id = context.resources.getIdentifier(carta, "drawable", context.packageName)),
        contentDescription = "Carta mostrada",
        modifier = Modifier
            .height(150.dp)
            .width(75.dp)
    )

    //(painter = painterResource(id = carta.idDrawable)
/*
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = carta.idDrawable),
            contentDescription = "Carta mostrada",
            modifier = Modifier
                .height(150.dp)
                .width(75.dp)
        )
    }

 */
}

 */
@Composable
fun MostrarCartasEnMano() {
    val cartasEnMano = listOf<String>("reverso2", "reverso2")

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        item {
            Box() {
                cartasEnMano.forEachIndexed { index, carta ->
                    MostrarCarta(carta, index)
                }
            }
        }
    }
}

/*
@Composable
fun MostrarCartasEnMano() {

    val cartasEnMano = listOf<String>("reverso2", "reverso2")

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        item { for(i in cartasEnMano){
            Spacer(modifier = Modifier.width(32.dp))
            MostrarCarta(i)
        } }
    }
}

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
