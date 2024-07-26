package com.jhoglas.pethelthcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jhoglas.pethelthcontrol.navigation.HomeNavigation
import com.jhoglas.pethelthcontrol.navigation.RegisterNavigation
import com.jhoglas.pethelthcontrol.ui.home.HomeScreen
import com.jhoglas.pethelthcontrol.ui.register.RegisterScreen
import com.jhoglas.pethelthcontrol.ui.theme.PetHelthControlTheme
import org.koin.core.context.KoinContext


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun App(){
    PetHelthControlTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = HomeNavigation
        ) {
            composable<HomeNavigation> { HomeScreen(navController) }
            composable<RegisterNavigation> { RegisterScreen(navController) }
        }
    }
}