package badilla.carlos.pantallas_fer

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import badilla.carlos.pantallas_fer.ui.theme.Pantallas_ferTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pantallas_ferTheme {
                var pantallaActual by remember { mutableStateOf(1) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (pantallaActual) {
                        1 -> {
                            SeleccionScreen(
                                opciones = opcionesTipoUniversidad,
                                onContinuar = { idSeleccionado ->
                                    pantallaActual = 2
                                },
                                onRegresar = {
                                    Toast.makeText(this, "Inicio del flujo", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        2 -> {
                            SeleccionScreen(
                                opciones = opcionesUniversidadesPrivadas,
                                onContinuar = { idSeleccionado ->
                                    Toast.makeText(this, "Seleccionaste universidad ID: $idSeleccionado", Toast.LENGTH_SHORT).show()
                                },
                                onRegresar = {
                                    pantallaActual = 1
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}