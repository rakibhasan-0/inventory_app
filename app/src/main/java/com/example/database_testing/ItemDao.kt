package com.example.database_testing

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ItemDao{

    @Query("SELECT * FROM item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>

    @Delete
    suspend fun deleteItem(item: Item)

    @Insert
    suspend fun insertItem(item: Item)

}