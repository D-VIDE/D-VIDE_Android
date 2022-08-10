package com.d_vide.D_VIDE.app._enums

sealed class Category(val value: String) {
    object ALL: Category("")
    object STREET_FOOD: Category("STREET_FOOD")
    object KOREAN_FOOD: Category("KOREAN_FOOD")
    object JAPANESE_FOOD: Category("JAPANESE_FOOD")
    object CHINESE_FOOD: Category("CHINESE_FOOD")
    object DESSERT: Category("DESSERT")
    object WESTERN_FOOD: Category("WESTERN_FOOD")
}
