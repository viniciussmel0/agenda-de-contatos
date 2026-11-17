package com.viniciusmelo.agendadecontatos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.viniciusmelo.agendadecontatos.views.AtualizarContato
import com.viniciusmelo.agendadecontatos.views.ListaContatos
import com.viniciusmelo.agendadecontatos.views.SalvarContato

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "listaContatos"
            ) {
                composable("listaContatos") {
                    ListaContatos(navController)
                }
                composable("salvarContato") {
                    SalvarContato()
                }
                composable("atualizarContato") {
                    AtualizarContato()
                }
            }

        }
    }
}
