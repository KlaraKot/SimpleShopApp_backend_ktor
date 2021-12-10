package com.example.modelsDB

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object UserDB: Table() {
    var userIdDB: Column<String> = varchar("userIdDB", 50)
    var email: Column<String> = varchar("email", 50)
    var name: Column<String> = varchar("name", 50)
    var surname: Column<String> = varchar("surname", 50)
    var passwordHash: Column<String> = varchar("passwordHash", 50)

}