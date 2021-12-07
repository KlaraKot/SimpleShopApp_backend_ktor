package com.example.data.models
import java.io.Serializable
data class Tea(
    val teaId: String,
    val name: String,
    val type: String,
    val price: Int,
    val weight: Int,
    val amountLeft: Int,

): Serializable

val teas = mutableListOf<Tea>()