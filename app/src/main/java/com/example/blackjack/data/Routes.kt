package com.example.blackjack.data


sealed class Routes(val route: String) {
    object Screen1: Routes("Screen1")
    object Screen2: Routes("Screen2")

    object Screen3: Routes("Screen3")
}