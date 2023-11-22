package com.example.blackjack.screens

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutBaseScope
import androidx.navigation.NavHostController
import com.example.blackjack.Baraja
import com.example.blackjack.Carta
import com.example.blackjack.R
import com.example.blackjack.ui.theme.Jugador




/**
 * Función principal donde se incia el juego y realiza las acciones principales.
 */
//@Preview(showBackground = true)
@Suppress("SpellCheckingInspection")
@Composable
fun Screen1(navController: NavHostController){
    Juego()
}

@Preview(showBackground = true)
@Composable
fun Juego(){
    val context = LocalContext.current
    var carta by rememberSaveable { mutableStateOf("vuelta") }
    var baraja by rememberSaveable { mutableStateOf(Baraja.creaBaraja()) }
    var cartasEnMano by rememberSaveable { mutableStateOf(mutableListOf(carta, carta))}
    var show  by rememberSaveable { mutableStateOf(true) }
    var contador by rememberSaveable { mutableStateOf(0) }


    Tapete(R.drawable.tapete)
    MostrarCartasEnMano(context, cartasEnMano, contador)
    if(show) {
        MostrarMazo("vuelta", context)
        Baraja.barajar(baraja)
    }

    Botones(
        Apostar = {},
        PedirCarta = {carta = if (baraja.isEmpty()) {
            Toast.makeText( context,"La baraja está vacía ", Toast.LENGTH_SHORT).show()
            show = false
            "vuelta"
        } else {
            val cartaDada = Baraja.dameCarta(baraja)
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
}



@Composable
fun Tapete(@DrawableRes tapeteID: Int){
    Image(painter = painterResource(id = tapeteID),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
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
}


@Composable
fun MostrarMazo(
    carta: String,
    context: Context
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Image(painter = painterResource(id = context.resources.getIdentifier(carta, "drawable", context.packageName) ),
            contentDescription = "Carta mostrada",
            modifier = Modifier
                .height(150.dp)
                .width(75.dp)
        )
    }
}
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
    texto: String
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier.padding(3.dp),
        colors = ButtonDefaults.buttonColors(Color.Yellow)
    ) {
        Text(text = texto)
    }
}
@Composable
fun Botones(
    Apostar: () -> Unit,
    PedirCarta: () -> Unit,
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
            MyButton(onClick = { PedirCarta() }, texto = "PedirCarta")
            MyButton(onClick = { Reiniciar() }, texto = "Plantarse")
            MyButton(onClick = { Plantarse() }, texto = "Reiniciar")
        }
    }

}


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