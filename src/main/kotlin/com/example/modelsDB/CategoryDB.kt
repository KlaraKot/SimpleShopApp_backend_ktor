package com.example.modelsDB

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Column

object CategoryDB: Table() {
    var categoryId: Column<String> = varchar("categoryId", 50).uniqueIndex()
    var categoryName: Column<String> = varchar("categoryName", 50)
    override val primaryKey = PrimaryKey(CategoryDB.categoryId, name="PK_categoryId")

}