package badilla.carlos.pantallas_fer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import badilla.carlos.pantallas_fer.ui.theme.FerCeleste
import badilla.carlos.pantallas_fer.ui.theme.FerFondoTarjetaSeleccionada
import badilla.carlos.pantallas_fer.ui.theme.FerFontFamily
import badilla.carlos.pantallas_fer.ui.theme.FerNavy
import badilla.carlos.pantallas_fer.ui.theme.Pantallas_ferTheme

@Composable
fun SeleccionScreen(
    opciones: List<OpcionSeleccion>,
    onContinuar: (Int, String) -> Unit,
    onRegresar: () -> Unit,
    modifier: Modifier = Modifier,
    esUltimaPantalla: Boolean = false // true => botón con palomita (✓) como en Frame 11
) {
    var seleccionadoId by rememberSaveable { mutableStateOf<Int?>(null) }
    var otraUniversidadTexto by rememberSaveable { mutableStateOf("") }

    val puedeContinuar = when {
        seleccionadoId == null -> false
        seleccionadoId == 99 -> otraUniversidadTexto.isNotBlank()
        else -> true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 40.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // ---------- Encabezado ----------
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Bienvenido",
            fontFamily = FerFontFamily,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = FerNavy
        )
        Spacer(modifier = Modifier.height(20.dp))
        // La línea celeste va alineada a la IZQUIERDA del subtítulo (como en Figma)
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = "Selecciona quién eres",
                fontFamily = FerFontFamily,
                fontSize = 20.sp,
                color = FerNavy
            )
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .width(38.dp)
                    .height(4.dp)
                    .background(FerCeleste, RoundedCornerShape(2.dp))
            )
        }

        // ---------- Tarjetas (centradas verticalmente) ----------
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            // padding para que la sombra no se corte
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {
            items(opciones) { opcion ->
                val estaSeleccionado = seleccionadoId == opcion.id

                Column {
                    Card(
                        onClick = { seleccionadoId = opcion.id },
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (estaSeleccionado) FerFondoTarjetaSeleccionada else Color.White
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .then(
                                if (estaSeleccionado)
                                    Modifier.border(1.5.dp, FerNavy, RoundedCornerShape(24.dp))
                                else Modifier
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp, vertical = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = opcion.icono),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(44.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = opcion.titulo,
                                    fontFamily = FerFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = FerNavy
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = opcion.descripcion,
                                    fontFamily = FerFontFamily,
                                    fontSize = 11.sp,
                                    lineHeight = 14.sp,
                                    color = FerNavy // en Figma la descripción es azul, no gris
                                )
                            }

                            Spacer(modifier = Modifier.width(4.dp))
                            RadioButton(
                                selected = estaSeleccionado,
                                onClick = { seleccionadoId = opcion.id },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = FerNavy,
                                    unselectedColor = FerNavy
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
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        // ---------- Botones ----------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BotonCircular(
                icono = FlechaIzquierdaIcon,
                descripcion = "Regresar",
                habilitado = true,
                onClick = onRegresar
            )
            BotonCircular(
                icono = if (esUltimaPantalla) PalomitaIcon else FlechaDerechaIcon,
                descripcion = if (esUltimaPantalla) "Confirmar" else "Continuar",
                habilitado = puedeContinuar,
                onClick = {
                    onContinuar(
                        seleccionadoId!!,
                        if (seleccionadoId == 99) otraUniversidadTexto else ""
                    )
                }
            )
        }
    }
}

@Composable
private fun BotonCircular(
    icono: ImageVector,
    descripcion: String,
    habilitado: Boolean,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        enabled = habilitado,
        modifier = Modifier
            .size(56.dp)
            .background(if (habilitado) FerNavy else Color(0xFFD0D5E0), CircleShape)
    ) {
        Icon(imageVector = icono, contentDescription = descripcion, tint = Color.White)
    }
}

val FlechaIzquierdaIcon: ImageVector
    get() = ImageVector.Builder(
        name = "FlechaIzquierda",
        defaultWidth = 24.dp, defaultHeight = 24.dp,
        viewportWidth = 24f, viewportHeight = 24f
    ).path(fill = SolidColor(Color.White), pathFillType = PathFillType.NonZero) {
        moveTo(20f, 11f); horizontalLineTo(7.83f); lineTo(13.42f, 5.41f); lineTo(12f, 4f)
        lineTo(4f, 12f); lineTo(12f, 20f); lineTo(13.41f, 18.59f); lineTo(7.83f, 13f)
        horizontalLineTo(20f); verticalLineTo(11f); close()
    }.build()

val FlechaDerechaIcon: ImageVector
    get() = ImageVector.Builder(
        name = "FlechaDerecha",
        defaultWidth = 24.dp, defaultHeight = 24.dp,
        viewportWidth = 24f, viewportHeight = 24f
    ).path(fill = SolidColor(Color.White), pathFillType = PathFillType.NonZero) {
        moveTo(12f, 4f); lineTo(10.59f, 5.41f); lineTo(16.17f, 11f); horizontalLineTo(4f)
        verticalLineTo(13f); horizontalLineTo(16.17f); lineTo(10.59f, 18.59f); lineTo(12f, 20f)
        lineTo(20f, 12f); lineTo(12f, 4f); close()
    }.build()

val PalomitaIcon: ImageVector
    get() = ImageVector.Builder(
        name = "Palomita",
        defaultWidth = 24.dp, defaultHeight = 24.dp,
        viewportWidth = 24f, viewportHeight = 24f
    ).path(fill = SolidColor(Color.White), pathFillType = PathFillType.NonZero) {
        moveTo(9f, 16.17f); lineTo(4.83f, 12f); lineTo(3.41f, 13.41f); lineTo(9f, 19f)
        lineTo(21f, 7f); lineTo(19.59f, 5.59f); close()
    }.build()

@Preview(showBackground = true, widthDp = 411, heightDp = 891)
@Composable
fun SeleccionScreenPreview() {
    Pantallas_ferTheme {
        SeleccionScreen(
            opciones = opcionesUniversidadesPrivadas.take(3),
            onContinuar = { _, _ -> },
            onRegresar = {},
            esUltimaPantalla = true
        )
    }
}