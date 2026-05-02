package com.example.edietask.domain.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.edietask.domain.data.local.entity.ListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao {
    @Query(value = "SELECT * FROM lists")
    fun getAllList(): Flow<List<ListEntity>>

    @Query(value = "SELECT * FROM lists WHERE categoryId = :categoryId")
    fun getListByCategory(categoryId: Int): Flow<List<ListEntity>>

    @Query(value = "SELECT * FROM lists WHERE id = :id")
    suspend fun getListById(id: Int): ListEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: ListEntity)

    @Delete
    suspend fun deleteList(list: ListEntity)

    @Update
    suspend fun updateList(list: ListEntity)
}