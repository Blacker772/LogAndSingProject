package com.example.logandsignproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_for_account")
class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo
    val name: String?,

    @ColumnInfo
    val surname: String?,

    @ColumnInfo
    val login: String?,

    @ColumnInfo
    val password: String?,

    @ColumnInfo
    val email: String,

    @ColumnInfo
    val gender: String
)