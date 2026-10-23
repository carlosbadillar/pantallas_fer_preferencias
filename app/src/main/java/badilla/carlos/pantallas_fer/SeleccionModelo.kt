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
        descripcion = "Accede a avisos, convocatorias y beneficios exclusivos para becarios FER.",
        icono = R.drawable.ic_launcher_foreground
    ),
    OpcionSeleccion(
        id = 2,
        titulo = "Universidad Privada",
        descripcion = "Convocatorias y avisos para futuros becarios.",
        icono = R.drawable.ic_launcher_foreground
    )
)

val opcionesUniversidadesPrivadas = listOf(
    OpcionSeleccion(
        id = 1,
        titulo = "UDEM",
        descripcion = "Accede a avisos, convocatorias y beneficios exclusivos para becarios FER.",
        icono = R.drawable.ic_launcher_foreground
    ),
    OpcionSeleccion(
        id = 2,
        titulo = "CETYS",
        descripcion = "Convocatorias y avisos para futuros becarios.",
        icono = R.drawable.ic_launcher_foreground
    ),
    OpcionSeleccion(
        id = 3,
        titulo = "Tec de Monterrey",
        descripcion = "Convocatorias y avisos para futuros becarios.",
        icono = R.drawable.ic_launcher_foreground
    )
)