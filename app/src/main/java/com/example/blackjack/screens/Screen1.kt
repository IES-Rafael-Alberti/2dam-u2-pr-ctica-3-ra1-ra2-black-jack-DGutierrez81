package com.example.blackjack.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.example.blackjack.Baraja
import com.example.blackjack.R

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
    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.tapete),
            contentScale = ContentScale.FillBounds
        )) {

        val context = LocalContext.current
        var baraja by rememberSaveable { mutableStateOf(Baraja.creaBaraja()) }
        val mazo by rememberSaveable { mutableStateOf("vuelta") }
        var carta by rememberSaveable { mutableStateOf("vuelta") }
        val (box1, box2, box3, box4) = createRefs()
        val topGuide = createGuidelineFromTop(0.6f)
        val topGuide2 = createGuidelineFromBottom(0.7f)
        var show  by rememberSaveable { mutableStateOf(true) }

        Box(
            modifier = Modifier
                .constrainAs(box1){
                    bottom.linkTo(topGuide)
                    start.linkTo(parent.start)
                }
        ){
            // Mientras se true nos muestra el mazo y baraja las cartas.
            if(show) {
                MostrarCarta(mazo, context)
                Baraja.barajar(baraja)
            }
        }

        Box(
            modifier = Modifier
                .constrainAs(box2){
                    top.linkTo(topGuide)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ){
            MostrarCarta(carta, context)
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
            .height(200.dp)
            .width(100.dp)
    )
}