package com.example.data.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import java.io.Serializable

object OrderDetailsDB: Table() {
    val orderId: Column<String> = varchar("orderId", 50)
    val productId: Column<String> = varchar("productId", 50).references(ProductDB.productId)
    val price: Column<Int> = integer("price").references(ProductDB.price)
    val quantity: Column<Int> = integer("quantity")
    override val primaryKey = PrimaryKey(OrderDetailsDB.orderId, name="PK_orderId")

}

data class OrderDetails (
    val orderId: String,
    val productId: String,
    val price: Int,
    val quantity: Int
    ): Serializable

{
    companion object {
        fun fromRow(resultRow: ResultRow) = OrderDetails(
            orderId = resultRow[OrderDetailsDB.orderId],
            productId = resultRow[OrderDetailsDB.productId],
            price = resultRow[OrderDetailsDB.price],
            quantity = resultRow[OrderDetailsDB.quantity]
        )
    }
}