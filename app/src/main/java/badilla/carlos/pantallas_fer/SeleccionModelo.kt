package badilla.carlos.pantallas_fer

import androidx.annotation.DrawableRes

data class OpcionSeleccion(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    @DrawableRes val icono: Int
)

val opcionesTipoUniversidad = listOf(
    OpcionSeleccion(
        id = 1,
        titulo = "Universidad Pública",
        descripcion = "Accede a avisos, convocatorias y beneficios para becarios FER.",
        icono = R.drawable.ic_launcher_foreground
    ),
    OpcionSeleccion(
        id = 2,
        titulo = "Universidad Privada",
        descripcion = "Convocatorias y avisos para futuros becarios de instituciones privadas.",
        icono = R.drawable.ic_launcher_foreground
    )
)

val opcionesUniversidadesPublicas = listOf(
    OpcionSeleccion(id = 1, titulo = "UNAM", descripcion = "Universidad Nacional Autónoma de México.", icono = R.drawable.ic_launcher_foreground),
    OpcionSeleccion(id = 2, titulo = "IPN", descripcion = "Instituto Politécnico Nacional.", icono = R.drawable.ic_launcher_foreground),
    OpcionSeleccion(id = 3, titulo = "UAM", descripcion = "Universidad Autónoma Metropolitana.", icono = R.drawable.ic_launcher_foreground),
    OpcionSeleccion(id = 4, titulo = "Universidad de Sonora", descripcion = "Unison - Hermosillo.", icono = R.drawable.ic_launcher_foreground),
    OpcionSeleccion(id = 99, titulo = "Otra", descripcion = "Escribe el nombre de tu universidad pública.", icono = R.drawable.ic_launcher_foreground)
)

val opcionesUniversidadesPrivadas = listOf(
    OpcionSeleccion(id = 1, titulo = "UDEM", descripcion = "Universidad de Monterrey.", icono = R.drawable.ic_launcher_foreground),
    OpcionSeleccion(id = 2, titulo = "CETYS", descripcion = "CETYS Universidad.", icono = R.drawable.ic_launcher_foreground),
    OpcionSeleccion(id = 3, titulo = "Tec de Monterrey", descripcion = "Instituto Tecnológico y de Estudios Superiores de Monterrey.", icono = R.drawable.ic_launcher_foreground),
    OpcionSeleccion(id = 4, titulo = "Universidad Anáhuac", descripcion = "Red de Universidades Anáhuac.", icono = R.drawable.ic_launcher_foreground),
    OpcionSeleccion(id = 99, titulo = "Otra", descripcion = "Escribe el nombre de tu universidad privada.", icono = R.drawable.ic_launcher_foreground)
)