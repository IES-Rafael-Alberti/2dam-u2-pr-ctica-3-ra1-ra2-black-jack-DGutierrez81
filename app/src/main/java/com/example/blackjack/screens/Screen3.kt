package com.example.blackjack.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.blackjack.R

/**
 * Muestra la pantalla principal de la partida en modo de un jugador.
 * @param navController permite navegar por las distintas pantallas.
 * @param viewmodel componente Viewmodel para poder cargar la función.
 */

@Composable
fun Screen3(navController: NavHostController, viewmodel: Viewmodel){
    val apuesta: Boolean by viewmodel.apuesta.observeAsState(initial = false)
    val show: Boolean by viewmodel.show.observeAsState(initial = true)
    val enableButton: Boolean by viewmodel.enableButton.observeAsState(true)
    val idJuego: Int by viewmodel.idJuego.observeAsState(initial = 0)
    val fin: Boolean by viewmodel.fin.observeAsState(initial = false)

    if(fin){
        Tapete(R.drawable.casiex)
        DialogoSiNo(navController, viewmodel)
    }else{
        Tapete(R.drawable.tapertin4)
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
                    Icono(R.drawable.terminator)
                    IndicadorJugador(viewmodel, 2)
                    IndicardorPuntos(viewmodel, 2)
                    Icono(R.drawable.monedas)
                    Indicardorfichas(viewmodel, 2)
                }
                if(idJuego == 3){
                    //Botones(viewmodel, enableButton2, 2)
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

