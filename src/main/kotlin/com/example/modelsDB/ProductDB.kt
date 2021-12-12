package com.example.modelsDB

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object ProductDB: Table() {
    var productId: Column<String> = varchar("userId", 50)//unique id
    var name: Column<String> = varchar("type", 50)
    var type: Column<String> = varchar("name", 50)
    var price: Column<Int> = integer("price")
    var weight: Column<Int> = integer("weight")
    var categoryId: Column<String> = varchar("categoryId", 50).uniqueIndex().references(CategoryDB.categoryId)
    override val primaryKey = PrimaryKey(productId, name="PK_productId")
}