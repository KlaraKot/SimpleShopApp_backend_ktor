package com.example.data.models
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table


object ProductDB: Table("Product"){
    var productId: Column<String> = varchar("userId", 50)//unique id
    var name: Column<String> = varchar("type", 50)
    var type: Column<String> = varchar("name", 50)
    var price: Column<Int> = integer("price")
    var weight: Column<Int> = integer("weight")
    var quantity: Column<Int> = integer("quantity")
    var categoryId: Column<String> = varchar("categoryId", 50)//.uniqueIndex().references(CategoryDB.categoryId)
    override val primaryKey = PrimaryKey(productId, name="PK_productId")
}


data class Product(
    val productId: String, //like PRO842
    val name: String, //ceylon tea
    val type: String, //tea or coffee
    val price: Int, //3 zloty
    val weight: Int, //4 grams
    val quantity: Int, //23 left
    val categoryId: String //CAT21

)
{
    companion object {
        fun fromRow(resultRow: ResultRow) = Product(
            productId = resultRow[ProductDB.productId],
            name = resultRow[ProductDB.name],
            type = resultRow[ProductDB.type],
            price = resultRow[ProductDB.price],
            weight = resultRow[ProductDB.weight],
            quantity = resultRow[ProductDB.quantity],
            categoryId = resultRow[ProductDB.categoryId]
        )
    }
}

