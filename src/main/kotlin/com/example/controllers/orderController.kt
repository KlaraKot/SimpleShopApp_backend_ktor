package com.example.controllers

import com.example.data.models.Order
import com.example.data.models.User
import com.example.modelsDB.OrderDB
import com.example.modelsDB.UserDB
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.*


class orderController {

    fun update(order: Order, new_amount: Int){
        transaction{
            OrderDB.update({ OrderDB.ItemId eq order.itemId}){
                it[amount] = new_amount
            }
        }
    }


}