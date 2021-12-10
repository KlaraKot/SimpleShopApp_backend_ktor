package com.example.modelsDB

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table.Dual.integer
import org.jetbrains.exposed.sql.Table.Dual.references
import org.jetbrains.exposed.sql.Table.Dual.uniqueIndex
import org.jetbrains.exposed.sql.Table.Dual.varchar

object OrderDB: Object() {
    var ItemId: Column<String> = varchar("itemId", 50).uniqueIndex()
        .references(TeaDB.teaId)
    var amount: Column<Int> = integer("amount")

}