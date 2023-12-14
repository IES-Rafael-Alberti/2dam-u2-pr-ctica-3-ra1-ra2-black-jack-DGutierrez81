
package com.example.blackjack.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.blackjack.R
import com.example.blackjack.model.Routes

@Suppress("SpellCheckingInspection")
@Composable
fun Screen1(navController: NavHostController){
    Inico()
    BotonesEntrada(navController)
}

@Preview(showBackground = true)
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
fun BotonesEntrada(navController: NavHostController){
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextButton(onClick = {navController.navigate(Routes.Screen2.route) }) {
            Text(text = "Un jugador",
                color = Color.White)
        }
    }
}

/*
class view: ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

}

 */