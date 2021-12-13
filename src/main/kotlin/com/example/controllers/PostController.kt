package com.example.controllers

import com.example.modelsDB.UserDB
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class PostController {
    fun addSomeUsers(){
        transaction {

        UserDB.insert {
            it[userId] = "USR1"
            it[email] = "supermax@gmail.com"
            it[name] = "Max"
            it[surname] = "Verstappen"
            it[passwordHash] = "WDC2021"
        }
        UserDB.insert {
            it[userId] = "USR2"
            it[email] = "charlec@gmail.com"
            it[name] = "Charles"
            it[surname] = "Leclerc"
            it[passwordHash] = "NW2021"
        }
        UserDB.insert {
            it[userId] = "USR3"
            it[email] = "vettel@gmail.com"
            it[name] = "Sebastian"
            it[surname] = "Vettel"
            it[passwordHash] = "WDC2007"
        }}
    }
}