package com.example.data.models
import java.io.Serializable

data class Order (
    val orderId: String,
    val userId: String
):Serializable