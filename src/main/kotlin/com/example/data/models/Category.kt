package com.example.data.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object CategoryDB: Table() {
    var categoryId: Column<String> = varchar("categoryId", 50).uniqueIndex()
    var categoryName: Column<String> = varchar("categoryName", 50)
    override val primaryKey = PrimaryKey(CategoryDB.categoryId, name="PK_categoryId")

}

data class Category (
    val categoryId: String,
    val categoryName: String
)

{
    companion object {
        fun fromRow(resultRow: ResultRow) = Category(
            categoryId = resultRow[CategoryDB.categoryId],
            categoryName = resultRow[CategoryDB.categoryName]
        )
    }
}