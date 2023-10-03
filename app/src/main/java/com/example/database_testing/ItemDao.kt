package com.example.database_testing

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDao{

    @Query("SELECT * FROM item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>



    @Insert
    suspend fun insertItem(item: Item)

}