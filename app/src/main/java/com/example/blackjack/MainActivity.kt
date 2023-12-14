package com.example.blackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.blackjack.data.Routes
import com.example.blackjack.screens.Screen1
import com.example.blackjack.screens.Screen2
import com.example.blackjack.screens.Screen3
import com.example.blackjack.screens.Viewmodel
import com.example.blackjack.ui.theme.BlackJackTheme


/**
 * Clase principal donde pone en concordancia todas las pantallas de juego.
 */
class MainActivity : ComponentActivity() {
    private val viewmodel: Viewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlackJackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.Screen1.route){
                        composable(Routes.Screen1.route){Screen1(navController, viewmodel)}
                        composable(Routes.Screen2.route){Screen2(navController, viewmodel = viewmodel) }
                        composable(Routes.Screen3.route){Screen3(navController, viewmodel = viewmodel) }
                    }
                }
            }
        }
    }
}
