package com.practice.todoapp

import androidx.lifecycle.LiveData
import com.practice.todoapp.db.ToDo
import com.practice.todoapp.db.ToDoDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {

    fun getAllTodo(): LiveData<List<ToDo>?> = toDoDao.getAllTodo()

    suspend fun insertToDo(toDo: ToDo) = toDoDao.insertToDo(toDo)

    suspend fun deleteToDo(toDo: ToDo) = toDoDao.deleteTodo(toDo)
    suspend fun updateToDo(toDo: ToDo) = toDoDao.updateToDo(toDo)


}