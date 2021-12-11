package com.example.modelsDB


import com.example.data.models.Order
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table.Dual.integer
import org.jetbrains.exposed.sql.Table.Dual.uniqueIndex
import org.jetbrains.exposed.sql.Table.Dual.varchar
import org.jetbrains.exposed.sql.Table


object BasketDB: Table() {
    var basketId: Column<String> = varchar("basketId", 50).uniqueIndex()
    var amountInBasket: Column<Int> = integer("amountInBasket")
    //var listOfItems: ArrayList<Order> = arrayListOf<Order>("Orders")


}