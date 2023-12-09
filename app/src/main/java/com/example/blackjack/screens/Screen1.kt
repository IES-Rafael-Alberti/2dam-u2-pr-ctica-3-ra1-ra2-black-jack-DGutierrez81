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
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.blackjack.R
import com.example.blackjack.data.Routes

@Suppress("SpellCheckingInspection")
@Composable
fun Screen1(navController: NavHostController, viewmodel: Viewmodel){
    Inico()
    BotonesEntrada(navController, viewmodel)
}


@Composable
fun Inico(){
    Caratula(R.drawable.portada)
}



@Composable
fun Caratula(@DrawableRes caratulaID: Int){
    Image(painter = painterResource(id = caratulaID),
        contentDescription = "",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
}
@Composable
fun BotonesEntrada(navController: NavHostController, viewmodel: Viewmodel){
    val show: Boolean by viewmodel.showDialog.observeAsState(false)
    if(show){
        DialogoInicio(show = true, onDismiss = {viewmodel.Confirmar(false)}, viewmodel, navController)
    }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = { }) {
            Text(text = "Un jugador",
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { viewmodel.Confirmar(true) }) {
            Text(text = "Dos jugadores",
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive,
                color = Color.White
            )
        }

    }
}
//navController.navigate(Routes.Screen2.route)


@Composable
fun DialogoInicio(
    show: Boolean,
    onDismiss: () -> Unit,
    viewmodel: Viewmodel,
    navController: NavHostController
){
    val nombre1: String by viewmodel.nombreJugador1.observeAsState(initial = "")
    val nombre2: String by viewmodel.nombreJugador2.observeAsState(initial = "")

    Dialog(onDismissRequest = { onDismiss()}){
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(24.dp)
                .fillMaxWidth()
        ) {
            TituloInscripcion()
            InscripcionJugador(nombre1, 1, viewmodel, drawable = R.drawable.jugador1)
            InscripcionJugador(nombre2, 2, viewmodel, drawable = R.drawable.carita)
            Spacer(modifier = Modifier.height(16.dp))
            Aceptar(navController, viewmodel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InscripcionJugador(nombre: String, id: Int, viewmodel: Viewmodel, @DrawableRes drawable: Int){
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
            onValueChange = { viewmodel.MostrarDialogoInicio(id, it) },
            label = { Text(text = "Introduce tu nombre")})
        //TextField(text = nombre, onValueChange = onValueChange(nombre), fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(8.dp))
    }
}
@Composable
fun TituloInscripcion(){
    Text(
        text = "Escribe tu nombre",
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun Aceptar(navController: NavHostController, viewmodel: Viewmodel){
    val botonInicio: Boolean by viewmodel.botonInicio.observeAsState(false)

    Row(){
        Button(
            enabled = botonInicio,
            onClick = { navController.navigate(Routes.Screen2.route)
                viewmodel.Inicio()}) {
            Text(text = "Enviar")
        }
        Button(
            onClick = { viewmodel.Confirmar(false)}) {
            Text(text = "Cancelar")
        }
    }
}

/*
class view: ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

}

 */