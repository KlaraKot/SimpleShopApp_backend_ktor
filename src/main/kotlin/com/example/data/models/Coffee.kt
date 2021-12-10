package com.example.data.models
import java.io.Serializable
data class Coffee(
    val coffeeId: String,
    val name: String,
    val type: String,
    val price: Int,
    val weight: Int,
    val amountLeft: Int,
): Serializable

