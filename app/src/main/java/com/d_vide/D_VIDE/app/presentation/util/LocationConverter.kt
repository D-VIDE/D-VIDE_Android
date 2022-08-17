package com.d_vide.D_VIDE.app.presentation.util

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.LatLng
import java.util.*

@Composable
fun LocationConverter(
    position: LatLng
): String {
    val context = LocalContext.current
    val geoCoder = Geocoder(context, Locale.getDefault())
    var gu = "주소 오류"
    var dong = ""

    var address = geoCoder.getFromLocation(position.latitude, position.longitude, 10)

    try {
        if (address.get(0).subLocality != null && address.get(0).subLocality.isNotEmpty()) {
            gu = address.get(0).subLocality
        }
        if (address.get(0).thoroughfare != null && address.get(0).thoroughfare.isNotEmpty()) {
            dong = address.get(0).thoroughfare
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return gu + " " + dong
}