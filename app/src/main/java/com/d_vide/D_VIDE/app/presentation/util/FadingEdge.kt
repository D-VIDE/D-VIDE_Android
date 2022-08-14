package com.d_vide.D_VIDE.app.presentation.util

import android.app.ActionBar
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.Size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.d_vide.D_VIDE.ui.theme.*

@Composable
fun GradientCompponent(modifier: Modifier = Modifier){
    GradientView(
        modifier = modifier.fillMaxWidth().height(235.dp),
        colors = intArrayOf(
            Color.Transparent.toArgb(),
            (GradientDefaults.Color).toArgb(),
        )
    )
}


@Stable
object GradientDefaults {
    @Stable
    val Color = gray6

    @Stable
    val Height = 235.dp
}

@Stable
sealed class Gradient {
    @Immutable
    data class Top(
        val color: Color = GradientDefaults.Color,
        val height: Dp = GradientDefaults.Height,
    ) : Gradient()

    @Immutable
    data class Bottom(
        val color: Color = GradientDefaults.Color,
        val height: Dp = GradientDefaults.Height,
    ) : Gradient()
}

@Composable
fun GradientView(
    modifier: Modifier = Modifier,
    @Size(value = 2) colors: IntArray,
    visible: Boolean = true,
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val gradientBackground = GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM,
                colors
            ).apply {
                cornerRadius = 0f
            }
            View(context).apply {
                layoutParams = ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT
                )
                background = gradientBackground
                visibility = when (visible) {
                    true -> View.VISIBLE
                    else -> View.INVISIBLE
                }
            }
        }
    )
}