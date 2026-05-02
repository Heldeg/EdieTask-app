package com.example.edietask.domain.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.edietask.domain.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    //Task for a list
    @Query(value = "SELECT * FROM tasks WHERE listId = :listId AND parentTaskId IS NULL AND deleted_at IS NULL")
    fun getActiveTasksForList(listId: Int): Flow<List<TaskEntity>>

    //Sub tasks
    @Query(value = "SELECT * FROM tasks WHERE parentTaskId = :parentTaskId AND deleted_at IS NULL")
    fun getActiveSubTasks(parentTaskId: Int): Flow<List<TaskEntity>>

    //Task by id
    @Query(value = "SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id:Int): TaskEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity)

    @Update
    suspend fun updateTask(task: TaskEntity)

    //Delete
    @Query(value = "UPDATE tasks SET deleted_at = :timestamp WHERE id = :taskId")
    suspend fun deleteTask(taskId: Int, timestamp: Long = System.currentTimeMillis())

    //Change task status
    @Query(value = "UPDATE tasks SET isCompleted = NOT isCompleted WHERE id = :taskId")
    suspend fun changeTaskStatus(taskId: Int)
}