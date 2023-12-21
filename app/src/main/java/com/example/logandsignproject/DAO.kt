package com.example.logandsignproject


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAO {

    @Insert
    fun insertPerson(item: Entity)

    @Query("SELECT * FROM table_for_account WHERE login = :login")
    fun qetUserByLogin(login: String): Entity
}