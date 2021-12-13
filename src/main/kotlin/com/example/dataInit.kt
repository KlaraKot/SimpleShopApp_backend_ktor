package com.example

import com.example.modelsDB.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseInitializer {

    fun eraseSchema() {
        transaction {
            SchemaUtils.drop(UserDB, ProductDB, OrderDetailsDB, OrderDB, CategoryDB)
        }
    }

    fun createSchema() {
        transaction {
            SchemaUtils.create(UserDB, ProductDB, OrderDetailsDB, OrderDB, CategoryDB)
        }
    }

}