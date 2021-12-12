package com.example.modelsDB

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table.Dual.integer
import org.jetbrains.exposed.sql.Table.Dual.references
import org.jetbrains.exposed.sql.Table.Dual.uniqueIndex
import org.jetbrains.exposed.sql.Table.Dual.varchar
import org.jetbrains.exposed.sql.Table

object OrderDB: Table() {
    var orderId: Column<String> = varchar("itemId", 50).uniqueIndex()
    var userId: Column<String> = varchar("userId", 50)
    override val primaryKey = PrimaryKey(OrderDB.orderId, name="PK_orderId")

}