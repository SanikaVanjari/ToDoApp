package com.practice.todoapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDo(todo: ToDo)

    @Delete()
    suspend fun deleteTodo(todo: ToDo)

    @Query("SELECT * from todo")
    fun getAllTodo(): LiveData<List<ToDo>?>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateToDo(toDo: ToDo)

    @Query("UPDATE todo SET complete=1 WHERE id=:id")
    suspend fun markCompleteToDo(id: Int)


}