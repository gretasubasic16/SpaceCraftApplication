package org.unizd.rma.peric.bookcase.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import org.unizd.rma.subasic.spacecraft.ui.theme.LightBlue200
import org.unizd.rma.subasic.spacecraft.ui.theme.LightBlue500
import org.unizd.rma.subasic.spacecraft.ui.theme.LightBlue700
import org.unizd.rma.subasic.spacecraft.ui.theme.Shapes
import org.unizd.rma.subasic.spacecraft.ui.theme.Teal200
import org.unizd.rma.subasic.spacecraft.ui.theme.Typography


private val DarkColorPalette = darkColors(
    primary = LightBlue200,
    primaryVariant = LightBlue700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = LightBlue500,
    primaryVariant = LightBlue700,
    secondary = Teal200,


    )

@Composable
fun SpaceCraftTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
