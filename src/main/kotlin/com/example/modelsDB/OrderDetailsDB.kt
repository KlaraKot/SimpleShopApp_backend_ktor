package com.example.modelsDB

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object OrderDetailsDB: Table() {
    val orderId: Column<String> = varchar("orderId", 50).references(OrderDB.orderId)
    val productID: Column<String> = varchar("productId", 50).references(ProductDB.productId)
    val price: Column<Int> = integer("price")
    val quantity: Column<Int> = integer("quantity")
}