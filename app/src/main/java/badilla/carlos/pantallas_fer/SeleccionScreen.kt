package badilla.carlos.pantallas_fer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    onContinuar: (Int, String) -> Unit,
    onRegresar: () -> Unit,
    modifier: Modifier = Modifier
) {
    var seleccionadoId by rememberSaveable { mutableStateOf<Int?>(null) }
    var otraUniversidadTexto by rememberSaveable { mutableStateOf("") }

    val azulOscuro = Color(0xFF0F172A)
    val grisFondoSeleccion = Color(0xFFE2E8F0)
    val grisBordeTarjeta = Color(0xFFCBD5E1)

    val puedeContinuar = when {
        seleccionadoId == null -> false
        seleccionadoId == 99 -> otraUniversidadTexto.isNotBlank()
        else -> true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = "Bienvenido",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = azulOscuro
            )
            Spacer(modifier = Modifier.height(4.dp))
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

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(opciones) { opcion ->
                val estaSeleccionado = seleccionadoId == opcion.id

                Column {
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
                                .padding(14.dp),
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
                                        .size(40.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                Spacer(modifier = Modifier.width(12.dp))

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

                    if (estaSeleccionado && opcion.id == 99) {
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = otraUniversidadTexto,
                            onValueChange = { otraUniversidadTexto = it },
                            label = { Text("Nombre de tu universidad") },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
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

            IconButton(
                onClick = {
                    if (puedeContinuar) {
                        onContinuar(
                            seleccionadoId!!,
                            if (seleccionadoId == 99) otraUniversidadTexto else ""
                        )
                    }
                },
                enabled = puedeContinuar,
                modifier = Modifier
                    .size(56.dp)
                    .background(
                        color = if (puedeContinuar) azulOscuro else Color.LightGray,
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
            onContinuar = { _, _ -> },
            onRegresar = {}
        )
    }
}