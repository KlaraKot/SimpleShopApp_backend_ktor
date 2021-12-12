package com.example.data.models

import java.io.Serializable

data class OrderDetails (
    val orderId: String,
    val productID: String,
    val price: Int,
    val quantity: Int
    ): Serializable