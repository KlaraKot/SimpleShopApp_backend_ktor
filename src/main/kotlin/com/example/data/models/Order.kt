package com.example.data.models
import java.io.Serializable

data class Order (
    val itemId: String,
    val amount: Int,
):Serializable