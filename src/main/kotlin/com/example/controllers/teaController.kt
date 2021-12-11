package com.example.controllers

import com.example.data.models.Tea
import com.example.data.models.User
import com.example.modelsDB.TeaDB
import com.example.modelsDB.UserDB
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class teaController {


    fun getByName(name: String): ArrayList<Tea>{
        val teas: ArrayList<Tea> = arrayListOf()
        transaction{
            TeaDB.select(TeaDB.name eq name).map{
                teas.add((
                        Tea(
                            teaId = it[TeaDB.teaId],
                            name = it[TeaDB.name],
                            type = it[TeaDB.type],
                            price = it[TeaDB.price],
                            weight = it[TeaDB.weight],
                            amountLeft = it[TeaDB.amountLeft]
                        )
                        ))
            }
        }
        return teas
    }

    fun getByType(type: String): ArrayList<Tea>{
        val teas: ArrayList<Tea> = arrayListOf()
        transaction{
            TeaDB.select(TeaDB.type eq type).map{
                teas.add((
                        Tea(
                            teaId = it[TeaDB.teaId],
                            name = it[TeaDB.name],
                            type = it[TeaDB.type],
                            price = it[TeaDB.price],
                            weight = it[TeaDB.weight],
                            amountLeft = it[TeaDB.amountLeft]
                        )
                        ))
            }
        }
        return teas
    }

    fun getAll(): ArrayList<Tea>{
        val teas: ArrayList<Tea> = arrayListOf()
        transaction{
            TeaDB.selectAll().map{
                teas.add(
                    Tea(
                        teaId = it[TeaDB.teaId],
                        name = it[TeaDB.name],
                        type = it[TeaDB.type],
                        price = it[TeaDB.price],
                        weight = it[TeaDB.weight],
                        amountLeft = it[TeaDB.amountLeft]
                    )
                )
            }
        }
        return teas
    }

}