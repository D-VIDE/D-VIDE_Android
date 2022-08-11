package com.d_vide.D_VIDE.app.presentation.Recruitings.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun LocationSelector(
    cameraPositionState: CameraPositionState
) {
    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(0.dp, 18.dp, 18.dp, 0.dp),
                clip = true
            ),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = cameraPositionState.position.target),
            title = "PathFinder",
            snippet = "Marker in PathFinder"
        )
    }
}