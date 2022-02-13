package ru.gozerov.domain.entity

import java.io.Serializable

data class ProductSimple(
    val id: Long,
    val productName: String,
    val price: String,
    val image: String
): Serializable