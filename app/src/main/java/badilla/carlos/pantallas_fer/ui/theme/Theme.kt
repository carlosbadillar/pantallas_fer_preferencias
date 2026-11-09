package badilla.carlos.pantallas_fer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = FerNavy,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = FerCeleste,
    background = androidx.compose.ui.graphics.Color.White,
    surface = androidx.compose.ui.graphics.Color.White,
    onSurface = FerNavy
)

@Composable
fun Pantallas_ferTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}