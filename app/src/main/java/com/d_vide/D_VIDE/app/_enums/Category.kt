package com.d_vide.D_VIDE.app._enums

sealed class Category(val tag: String, val name: String) {
    object ALL: Category("", "전체")
    object STREET_FOOD: Category("STREET_FOOD", "분식")
    object KOREAN_FOOD: Category("KOREAN_FOOD", "한식")
    object JAPANESE_FOOD: Category("JAPANESE_FOOD", "일식")
    object CHINESE_FOOD: Category("CHINESE_FOOD", "중식")
    object DESSERT: Category("DESSERT", "디저트")
    object WESTERN_FOOD: Category("WESTERN_FOOD", "양식")
}
