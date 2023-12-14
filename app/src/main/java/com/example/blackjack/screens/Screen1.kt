package com.example.blackjack.screens
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.blackjack.R
import com.example.blackjack.data.Routes


/**
 * Recoge el total de la vista de la pantalla principal.
 * @param navController
 * @param viewmodel
 */
@Suppress("SpellCheckingInspection")
@Composable
fun Screen1(navController: NavHostController, viewmodel: Viewmodel){
    val showSimple: Boolean by viewmodel.showSimple.observeAsState(false)
    val showDoble: Boolean by viewmodel.showDoble.observeAsState(false)
    val nombre1: String by viewmodel.nombreJugador1.observeAsState(initial = "")

    Caratula(R.drawable.portada)
    if(showSimple){
        DialogoSimple(nombre1, onDismiss = {viewmodel.confirmar(false, 1)}, viewmodel, navController)
    }
    if(showDoble){
        DialogoInicioDoble(nombre1, onDismiss = {viewmodel.confirmar(false, 2)}, viewmodel, navController)
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = { viewmodel.confirmar(true, 1) }) {
            Text(text = "Un jugador",
                style = TextStyle(fontSize = 40.sp,
                    shadow = Shadow(color = Color.Yellow,
                        offset = Offset(5.0f, 10.0f),
                        blurRadius = 3f),
                    fontFamily = FontFamily.Cursive,
                    color = Color.White)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { viewmodel.confirmar(true, 2) }) {
            Text(text = "Dos jugadores",
                style = TextStyle(fontSize = 40.sp,
                    shadow = Shadow(color = Color.Yellow,
                        offset = Offset(5.0f, 10.0f),
                        blurRadius = 3f),
                    fontFamily = FontFamily.Cursive,
                    color = Color.White)
            )
        }

    }
}


/**
 * Muestra la imagen que se desea mostrar.
 */

@Composable
fun Caratula(@DrawableRes caratulaID: Int){
    Image(painter = painterResource(id = caratulaID),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}

/**
 * Muestra la vista de diálogo para inscribir el nombre en la opción de un jugador.
 * @param nombre1 recibe el nombre del jugador.
 *
 * @param navController permite navegar por las distintas pantallas.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 */
@Composable
fun DialogoSimple(
    nombre1: String,
    onDismiss: () -> Unit,
    viewmodel: Viewmodel,
    navController: NavHostController
){


    Dialog(onDismissRequest = { onDismiss()}){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ){
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment =  Alignment.CenterHorizontally
            ) {
                TituloInscripcion()
                InscripcionJugador(nombre1, 1, viewmodel, drawable = R.drawable.jugador1, 1)
                Spacer(modifier = Modifier.height(16.dp))
                Aceptar(navController, viewmodel, 1)
            }
        }
    }
}

/**
 * Muestra la vista de diálogo para inscribir el nombre en la opción de dos jugadores.
 *
 * @param navController permite navegar por las distintas pantallas.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 */
@Composable
fun DialogoInicioDoble(
    nombre1: String,
    onDismiss: () -> Unit,
    viewmodel: Viewmodel,
    navController: NavHostController
){
    val nombre2: String by viewmodel.nombreJugador2.observeAsState(initial = "")

    Dialog(onDismissRequest = { onDismiss()}){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ){
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment =  Alignment.CenterHorizontally
            ) {
                TituloInscripcion()
                InscripcionJugador(nombre1, 1, viewmodel, drawable = R.drawable.jugador1, 2)
                InscripcionJugador(nombre2, 2, viewmodel, drawable = R.drawable.carita, 2)
                Spacer(modifier = Modifier.height(16.dp))
                Aceptar(navController, viewmodel, 2)
            }
        }
    }
}

/**
 * Muestra el icono del jugador y permite ingresar el nombre.
 * @param nombre recibe el nombre de un jugador.
 * @param id contiene la ID del jugador.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 * @param drawable recibe el numero de la dirección de la imagegn del icono.
 * @param numero indica el número de jugadores de la partida.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InscripcionJugador(nombre: String, id: Int, viewmodel: Viewmodel, @DrawableRes drawable: Int, numero: Int){
    Row(verticalAlignment = Alignment.CenterVertically){
        Image(
            painter= painterResource(id = drawable),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(40.dp)
                .clip(CircleShape)
        )
        OutlinedTextField(value = nombre,
            onValueChange = { viewmodel.jugadorInscribir(id, it, numero) },
            label = { Text(text = "Introduce tu nombre")})
        //TextField(text = nombre, onValueChange = onValueChange(nombre), fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(8.dp))
    }
}

/**
 * Texto que da título al recuadro de diálogo.
 */
@Composable
fun TituloInscripcion(){
    Text(
        text = "Escribe tu nombre",
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

/**
 * Par de botones donde se puede confirmar los nombres o cancelar y salir del cuadro de diálogo.
 * @param navController permite navegar por las distintas pantallas.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 */
@Composable
fun Aceptar(navController: NavHostController, viewmodel: Viewmodel, id: Int){
    val botonInicio: Boolean by viewmodel.botonInicio.observeAsState(false)

    Row{
        Button(
            enabled = botonInicio,
            onClick = { if(id == 1) navController.navigate(Routes.Screen3.route) else navController.navigate(Routes.Screen2.route)
                viewmodel.inicio(id)},colors = ButtonDefaults.buttonColors(Color(0xFF07A717))) {
            Text(text = "Enviar")
        }
        Button(
            onClick = { viewmodel.confirmar(false, id)},colors = ButtonDefaults.buttonColors(Color(0xFF07A717))) {
            Text(text = "Cancelar")
        }
    }
}
