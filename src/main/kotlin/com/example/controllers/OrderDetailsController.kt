package com.example.controllers

import com.example.data.models.OrderDetails
import com.example.data.models.Product
import com.example.modelsDB.OrderDetailsDB
import com.example.modelsDB.ProductDB
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.case
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table.Dual.slice
import org.jetbrains.exposed.sql.transactions.transaction

class OrderDetailsController {

    fun totalSum(id: String){
        OrderDetailsDB.slice(OrderDetailsDB.price.sum()).select{OrderDetailsDB.orderId eq id}

    }

    fun getOrderById(id: String):ArrayList<OrderDetails>{
        val orders: ArrayList<OrderDetails> = arrayListOf()
        transaction{
            ProductDB.select(OrderDetailsDB.orderId eq id).map{
                orders.add((
                        OrderDetails(
                            orderId = it[OrderDetailsDB.orderId],
                            productId = it[OrderDetailsDB.productID],
                            price = it[OrderDetailsDB.price],
                            quantity = it[OrderDetailsDB.quantity]
                        )
                        ))
            }
        }
        return orders
    }
}


