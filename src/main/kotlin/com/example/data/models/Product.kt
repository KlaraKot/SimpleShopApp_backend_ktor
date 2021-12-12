package com.example.data.models
import java.io.Serializable
data class Product(
    val productId: String, //like PRO842
    val name: String, //ceylon tea
    val type: String, //tea or coffee
    val price: Int, //3 zloty
    val weight: Int, //4 grams
    val categoryId: String //CAT21

): Serializable

