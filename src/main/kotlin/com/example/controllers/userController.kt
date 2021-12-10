package com.example.controllers
import com.example.data.models.User
import com.example.modelsDB.UserDB
import org.jetbrains.exposed.sql.*

import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.selectAll
import java.util.*
import kotlin.collections.ArrayList

class userController {

    fun addUser(user: User){
        transaction{
            UserDB.insert {
                it[userIdDB] = user.userId
                it[email] = user.email
                it[name] = user.name
                it[surname] = user.surname
                it[passwordHash] = user.passwordHash
            }
        }
    }

    fun getAll(){
        val users: ArrayList<User> = arrayListOf()
        transaction{
            UserDB.selectAll().map{
                users.add(
                    User(
                        userId = it[UserDB.userIdDB],
                        email = it[UserDB.email],
                        name = it[UserDB.name],
                        surname = it[UserDB.surname],
                        passwordHash = it[UserDB.passwordHash]
                    )
                )
            }
        }

    }

    fun getById(){

    }



}