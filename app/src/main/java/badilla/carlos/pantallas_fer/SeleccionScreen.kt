package badilla.carlos.pantallas_fer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import badilla.carlos.pantallas_fer.ui.theme.Pantallas_ferTheme

@Composable
fun SeleccionScreen(
    opciones: List<OpcionSeleccion>,
    onContinuar: (Int) -> Unit,
    onRegresar: () -> Unit,
    modifier: Modifier = Modifier
) {
    var seleccionadoId by rememberSaveable { mutableStateOf<Int?>(null) }

    val azulOscuro = Color(0xFF0F172A)
    val grisFondoSeleccion = Color(0xFFE2E8F0)
    val grisBordeTarjeta = Color(0xFFCBD5E1)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Encabezado
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 40.dp)
        ) {
            Text(
                text = "Bienvenido",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = azulOscuro
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Selecciona quién eres",
                fontSize = 16.sp,
                color = azulOscuro
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(3.dp)
                    .background(azulOscuro, shape = RoundedCornerShape(2.dp))
            )
        }

        // Tarjetas
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            opciones.forEach { opcion ->
                val estaSeleccionado = seleccionadoId == opcion.id

                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (estaSeleccionado) grisFondoSeleccion else Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if (estaSeleccionado) azulOscuro else grisBordeTarjeta,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { seleccionadoId = opcion.id }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Image(
                                painter = painterResource(id = opcion.icono),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                            Spacer(modifier = Modifier.width(16.dp))

                            Column {
                                Text(
                                    text = opcion.titulo,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = azulOscuro
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = opcion.descripcion,
                                    fontSize = 11.sp,
                                    color = Color.Gray,
                                    lineHeight = 14.sp
                                )
                            }
                        }

                        RadioButton(
                            selected = estaSeleccionado,
                            onClick = { seleccionadoId = opcion.id },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = azulOscuro,
                                unselectedColor = azulOscuro
                            )
                        )
                    }
                }
            }
        }

        // Botones de Navegación Inferiores
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Flecha Izquierda
            IconButton(
                onClick = onRegresar,
                modifier = Modifier
                    .size(56.dp)
                    .background(azulOscuro, shape = CircleShape)
            ) {
                Icon(
                    imageVector = FlechaIzquierdaIcon,
                    contentDescription = "Regresar",
                    tint = Color.White
                )
            }

            // Flecha Derecha
            IconButton(
                onClick = {
                    if (seleccionadoId != null) {
                        onContinuar(seleccionadoId!!)
                    }
                },
                enabled = seleccionadoId != null,
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = if (seleccionadoId != null) azulOscuro else Color.LightGray,
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = FlechaDerechaIcon,
                    contentDescription = "Continuar",
                    tint = Color.White
                )
            }
        }
    }
}

// Flechas dibujadas por código vectorizado (sin errores de paquetes)
val FlechaIzquierdaIcon: ImageVector
    get() = ImageVector.Builder(
        name = "FlechaIzquierda",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).path(
        fill = SolidColor(Color.White),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(20f, 11f)
        horizontalLineTo(7.83f)
        lineTo(13.42f, 5.41f)
        lineTo(12f, 4f)
        lineTo(4f, 12f)
        lineTo(12f, 20f)
        lineTo(13.41f, 18.59f)
        lineTo(7.83f, 13f)
        horizontalLineTo(20f)
        verticalLineTo(11f)
        close()
    }.build()

val FlechaDerechaIcon: ImageVector
    get() = ImageVector.Builder(
        name = "FlechaDerecha",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 24f,
        viewportHeight = 24f
    ).path(
        fill = SolidColor(Color.White),
        pathFillType = PathFillType.NonZero
    ) {
        moveTo(12f, 4f)
        lineTo(10.59f, 5.41f)
        lineTo(16.17f, 11f)
        horizontalLineTo(4f)
        verticalLineTo(13f)
        horizontalLineTo(16.17f)
        lineTo(10.59f, 18.59f)
        lineTo(12f, 20f)
        lineTo(20f, 12f)
        lineTo(12f, 4f)
        close()
    }.build()

@Preview(showBackground = true)
@Composable
fun SeleccionScreenPreview() {
    Pantallas_ferTheme {
        SeleccionScreen(
            opciones = opcionesTipoUniversidad,
            onContinuar = {},
            onRegresar = {}
        )
    }
}