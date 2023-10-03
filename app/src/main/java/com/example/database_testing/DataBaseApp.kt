package com.example.database_testing

import android.app.Application
import androidx.room.Room

class DataBaseApp : Application() {
    val database: ItemDatabase by lazy {
        Room.databaseBuilder(this, ItemDatabase::class.java, "database_testing.db").build()
    }
}
