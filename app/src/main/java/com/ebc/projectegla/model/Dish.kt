package com.ebc.projectegla.model

data class Dish(
    val name: String,
    val price: Double,
    val isCold: Boolean,
    val isCaldoso: Boolean,
    val isSalty: Boolean,
    val cuisine: String
)