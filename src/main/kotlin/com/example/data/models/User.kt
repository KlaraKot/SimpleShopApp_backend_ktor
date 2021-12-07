package com.example.data.models
//import io.ktor.auth.Principal
import java.io.Serializable

data class User(
    val userId: String,
    val email: String,
    val name: String,
    val surname: String,
    val passwordHash: String
):Serializable

val users = mutableListOf<User>()