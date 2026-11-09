package badilla.carlos.pantallas_fer

import androidx.annotation.DrawableRes

data class OpcionSeleccion(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    @DrawableRes val icono: Int
)

// TODO: cuando exportes los logos de Figma (PNG @3x o SVG) y los pongas en
// res/drawable, cambia estas líneas por R.drawable.logo_udem, logo_cetys, logo_tec.
private val iconoGenerico = R.drawable.ic_universidad
private val logoUdem = R.drawable.logo_udem
private val logoCetys = R.drawable.logo_cetys
private val logoTec = R.drawable.logo_tec

private const val DESC_FER = "Accede a avisos, convocatorias y beneficios exclusivos para becarios FER."
private const val DESC_FUTUROS = "Convocatorias y avisos para futuros becarios."

val opcionesTipoUniversidad = listOf(
    OpcionSeleccion(1, "Universidad Pública", DESC_FER, iconoGenerico),
    OpcionSeleccion(2, "Universidad Privada", DESC_FUTUROS, iconoGenerico)
)

val opcionesUniversidadesPublicas = listOf(
    OpcionSeleccion(1, "UNAM", "Universidad Nacional Autónoma de México.", iconoGenerico),
    OpcionSeleccion(2, "IPN", "Instituto Politécnico Nacional.", iconoGenerico),
    OpcionSeleccion(3, "UAM", "Universidad Autónoma Metropolitana.", iconoGenerico),
    OpcionSeleccion(99, "Otra", "Escribe el nombre de tu universidad pública.", iconoGenerico)
)

// Igual que en Figma: solo UDEM, CETYS y Tec de Monterrey.
val opcionesUniversidadesPrivadas = listOf(
    OpcionSeleccion(1, "UDEM", DESC_FER, logoUdem),
    OpcionSeleccion(2, "CETYS", DESC_FUTUROS, logoCetys),
    OpcionSeleccion(3, "Tec de Monterrey", DESC_FUTUROS, logoTec)
)