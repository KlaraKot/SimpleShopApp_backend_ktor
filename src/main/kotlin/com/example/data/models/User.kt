package com.example.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object UserDB : Table("Users") {
    val userId: Column<String> = varchar("id",66)
    val email: Column<String> = varchar("email", 50)
    val name: Column<String> = varchar("name", 100)
    val surname: Column<String> = varchar("surname", 100)
    val passwordHash: Column<String> = varchar("passwordHash", 52)
    override val primaryKey = PrimaryKey(userId, name = "PK_Customers")
}

data class User(
    val userId: String,
    val email: String,
    val name: String,
    val surname: String,
    val passwordHash: String
)
{
    companion object {
        fun fromRow(resultRow: ResultRow) = User(
            userId = resultRow[UserDB.userId],
            email = resultRow[UserDB.email],
            name = resultRow[UserDB.name],
            surname = resultRow[UserDB.surname],
            passwordHash = resultRow[UserDB.passwordHash]
        )
    }
}
