package com.example.controllers

import com.example.data.models.Coffee
import com.example.modelsDB.CoffeeDB
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class coffeeController {

    fun getByName(name: String): ArrayList<Coffee>{
        val coffees: ArrayList<Coffee> = arrayListOf()
        transaction{
            CoffeeDB.select(CoffeeDB.name eq name).map{
                coffees.add((
                        Coffee(
                            coffeeId = it[CoffeeDB.coffeeId],
                            name = it[CoffeeDB.name],
                            type = it[CoffeeDB.type],
                            price = it[CoffeeDB.price],
                            weight = it[CoffeeDB.weight],
                            amountLeft = it[CoffeeDB.amountLeft]
                        )
                        ))
            }
        }
        return coffees
    }

    fun getByType(type: String): ArrayList<Coffee>{
        val coffees: ArrayList<Coffee> = arrayListOf()
        transaction{
            CoffeeDB.select(CoffeeDB.type eq type).map{
                coffees.add((
                        Coffee(
                            coffeeId = it[CoffeeDB.coffeeId],
                            name = it[CoffeeDB.name],
                            type = it[CoffeeDB.type],
                            price = it[CoffeeDB.price],
                            weight = it[CoffeeDB.weight],
                            amountLeft = it[CoffeeDB.amountLeft]
                        )
                        ))
            }
        }
        return coffees
    }

    fun getAll(): ArrayList<Coffee>{
        val coffees: ArrayList<Coffee> = arrayListOf()
        transaction{
            CoffeeDB.selectAll().map{
                coffees.add(
                    Coffee(
                        coffeeId = it[CoffeeDB.coffeeId],
                        name = it[CoffeeDB.name],
                        type = it[CoffeeDB.type],
                        price = it[CoffeeDB.price],
                        weight = it[CoffeeDB.weight],
                        amountLeft = it[CoffeeDB.amountLeft]
                    )
                )
            }
        }
        return coffees
    }

}