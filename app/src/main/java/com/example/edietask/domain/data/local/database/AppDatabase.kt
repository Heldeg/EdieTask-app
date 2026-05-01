package com.example.edietask.domain.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.edietask.domain.data.local.dao.CategoryDao
import com.example.edietask.domain.data.local.dao.ListDao
import com.example.edietask.domain.data.local.dao.TaskDao
import com.example.edietask.domain.data.local.entity.CategoryEntity
import com.example.edietask.domain.data.local.entity.ListEntity
import com.example.edietask.domain.data.local.entity.TaskEntity

@Database(
    entities = [CategoryEntity::class, ListEntity::class, TaskEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun listDao(): ListDao
    abstract fun taskDao(): TaskDao

    //create instance for new DB

    companion object {
        @Volatile
        private var instance: AppDatabase ?= null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "edietask.db"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                    .also { instance = it}
            }
        }
    }
}