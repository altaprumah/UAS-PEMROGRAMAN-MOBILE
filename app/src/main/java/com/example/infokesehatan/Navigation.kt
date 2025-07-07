package com.example.infokesehatan

import androidx.compose.runtime.Composable
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class Navigation {

    @Composable
    fun Navigation(windowSizeClass: WindowSizeClass) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "homescreen"
        ) {
            composable("homescreen") {
                MyHealthApp(windowSizeClass, navController)
            }
            composable("profilescreen") {
                Profile().ProfileScreen(navController)
            }
        }
    }
}
