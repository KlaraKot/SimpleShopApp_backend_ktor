package com.example.modelsDB

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object TeaDB: Table() {
    var teaId: Column<String> = varchar("userIdDB", 50)
    var type: Column<String> = varchar("type", 50)
    var name: Column<String> = varchar("name", 50)
    var price: Column<Int> = integer("price")
    var weight: Column<Int> = integer("weight")
    var amountLeft: Column<Int> = integer("amountLeft")

}