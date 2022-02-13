package ru.gozerov.domain.entity

import java.io.Serializable

data class Product(
    val id: Long,
    val productName: String,
    val productImage: String,
    val rating: Double,
    val reviews: Int,
    val oldPrice: String,
    val newPrice: String
): Serializable
