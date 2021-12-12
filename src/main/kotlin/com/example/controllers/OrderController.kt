package com.example.controllers

import com.example.data.models.Order
import com.example.modelsDB.OrderDB
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.*


class OrderController {

    fun addNewOrder(order: Order){
        transaction {
            OrderDB.insert {
                it[orderId] = order.orderId
                it[userId] = order.userId
            }
        }
    }

    fun getOrderById(id: String){
        val values = OrderDB.select{OrderDB.orderId eq id}
    }


}