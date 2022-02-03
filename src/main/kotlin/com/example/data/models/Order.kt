package com.example.data.models
import com.example.models.UserDB
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table


object OrderDB: Table() {
    var orderId: Column<String> = varchar("itemId", 50).references(OrderDetailsDB.orderId)
    var userId: Column<String> = varchar("userId", 50).references(UserDB.userId)
}

data class Order (
    val orderId: String,
    val userId: String
)

{
    companion object {
        fun fromRow(resultRow: ResultRow) = Order(
            orderId = resultRow[OrderDB.orderId],
            userId = resultRow[OrderDB.userId]
        )
    }
}