package com.d_vide.D_VIDE.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.d_vide.D_VIDE.R



private val SpoqaHanSansNeo = FontFamily(
    Font(R.font.spoqahansansneo_regular, FontWeight.Normal),
    Font(R.font.spoqahansansneo_bold, FontWeight.Bold),
    Font(R.font.spoqahansansneo_light, FontWeight.Light),
    Font(R.font.spoqahansansneo_medium, FontWeight.Medium),
    Font(R.font.spoqahansansneo_thin, FontWeight.Thin),
)


private val SdSamlipHopang = FontFamily(
    Font(R.font.sdsamliphopangchettfbasic, FontWeight.Normal),
    Font(R.font.sdsamliphopangchettfoutline, FontWeight.ExtraBold)
)


object TextStyles{
    val Big1 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        letterSpacing = 0.6.sp
    )

    val Big2 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        letterSpacing = 0.4.sp
    )

    val Point1 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        letterSpacing = 0.6.sp
    )

    val Point2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 13.sp
    )

    val Point3 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.6.sp
    )

    val Point4 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        letterSpacing = 0.4.sp
    )

    val Basics1 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.6.sp
    )
    val Basics2 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.6.sp,
        lineHeight = 16.sp
    )
    val Basics3 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        letterSpacing = 0.6.sp,
        lineHeight = 16.sp
    )
    val Basics4 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        letterSpacing = 0.6.sp
    )
    val Basics5 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    )

    val Small0 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Medium,
        fontSize = 8.sp,
        letterSpacing = 0.6.sp
    )

    val Small1 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        letterSpacing = 0.6.sp
    )

    val Small2 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        letterSpacing = 0.6.sp
    )

    val Small3 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        letterSpacing = 0.6.sp
    )

    val Small4 = TextStyle(
        fontFamily = SpoqaHanSansNeo,
        fontWeight = FontWeight.Medium,
        fontSize = 7.sp,
        letterSpacing = 0.6.sp,
        lineHeight = 16.sp
    )
}

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)