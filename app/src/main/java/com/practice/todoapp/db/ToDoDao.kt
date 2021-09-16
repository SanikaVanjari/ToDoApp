package com.practice.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(todo:ToDo)

    @Delete()
    suspend fun deleteTodo(todo: ToDo)

    @Query("SELECT * from todo")
    suspend fun getAllTodo(): List<ToDo?>
}