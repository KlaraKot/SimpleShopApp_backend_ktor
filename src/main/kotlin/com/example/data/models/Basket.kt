package com.example.data.models

data class Basket(
    val basketId: String,
    val amountInBasket: Int,
    val listOfItems: ArrayList<Order>
)

