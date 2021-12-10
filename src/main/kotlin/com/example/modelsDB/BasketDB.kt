package com.example.modelsDB


import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table.Dual.integer
import org.jetbrains.exposed.sql.Table.Dual.uniqueIndex
import org.jetbrains.exposed.sql.Table.Dual.varchar

object BasketDB: Object() {
    var basketId: Column<String> = varchar("basketId", 50).uniqueIndex()
    var amountInBasket: Column<Int> = integer("amountInBasket")

}