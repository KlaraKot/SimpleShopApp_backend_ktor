package com.example.controllers

import com.example.data.models.Product
import com.example.modelsDB.ProductDB
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class ProductController {


    fun getByName(name: String): ArrayList<Product>{
        val products: ArrayList<Product> = arrayListOf()
        transaction{
            ProductDB.select(ProductDB.name eq name).map{
                products.add((
                        Product(
                            productId = it[ProductDB.productId],
                            name = it[ProductDB.name],
                            type = it[ProductDB.type],
                            price = it[ProductDB.price],
                            weight = it[ProductDB.weight],
                            categoryId = it[ProductDB.categoryId]
                        )
                        ))
            }
        }
        return products
    }

    fun getByType(type: String): ArrayList<Product>{
        val products: ArrayList<Product> = arrayListOf()
        transaction{
            ProductDB.select(ProductDB.type eq type).map{
                products.add((
                        Product(
                            productId = it[ProductDB.productId],
                            name = it[ProductDB.name],
                            type = it[ProductDB.type],
                            price = it[ProductDB.price],
                            weight = it[ProductDB.weight],
                            categoryId = it[ProductDB.categoryId]
                        )
                        ))
            }
        }
        return products
    }

    fun getAll(): ArrayList<Product>{
        val products: ArrayList<Product> = arrayListOf()
        transaction{
            ProductDB.selectAll().map{
                products.add(
                    Product(
                        productId = it[ProductDB.productId],
                        name = it[ProductDB.name],
                        type = it[ProductDB.type],
                        price = it[ProductDB.price],
                        weight = it[ProductDB.weight],
                        categoryId = it[ProductDB.categoryId]
                    )
                )
            }
        }
        return products
    }

}